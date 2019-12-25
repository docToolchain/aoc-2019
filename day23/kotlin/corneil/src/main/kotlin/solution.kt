package com.github.corneil.aoc2019.day23

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.ProgramState
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

data class Packet(val x: Long, val y: Long)

class NetWorkController(val address: Long) {
    val inputQueue: MutableList<Long> = mutableListOf()
    val outputQueue: MutableList<Long> = mutableListOf()
    fun provideInput(): List<Long> {
        return if (inputQueue.isNotEmpty()) {
            val result = inputQueue.toList()
            inputQueue.clear()
            result
        } else {
            listOf(-1L)
        }
    }

    fun hasPacket() = outputQueue.size >= 3
    fun readAddress() = outputQueue.removeAt(0)
    fun readPacket(): Packet {
        val x = outputQueue.removeAt(0)
        val y = outputQueue.removeAt(0)
        return Packet(x, y)
    }

    fun idle() = inputQueue.isEmpty()
    fun send(packet: Packet) {
        inputQueue.add(packet.x)
        inputQueue.add(packet.y)
    }
}
typealias Network = Map<Long, Pair<NetWorkController, ProgramState>>

fun createComputers(code: List<Long>, addresses: IntRange): Network {
    return addresses.map {
        val netWorkController = NetWorkController(it.toLong())
        val state = Program(code).createProgram(listOf(it.toLong()))
        Pair(netWorkController, state)
    }.associate { it.first.address to it }
}

fun runNetwork(network: Network, terminate: (Long, Packet) -> Boolean) {
    var nat: Packet? = null
    do {
        for (item in network.values) {
            val nic = item.first
            val programState = item.second
            programState.executeUntilInput(nic.provideInput())
            nic.outputQueue.addAll(programState.extractOutput())
            while (nic.hasPacket()) {
                val address = nic.readAddress()
                val packet = nic.readPacket()
                if (terminate(address, packet)) {
                    return
                }
                if (address == 255L) {
                    nat = packet
                    println("NAT:SAVE:$packet")
                } else {
                    val destination = network[address] ?: error("Cannot find NIC $address")
                    destination.first.send(packet)
                    println("Send:$address:$packet")
                }
            }
        }
        if (network.values.all { it.first.idle() }) {
            requireNotNull(nat) { "Expected NAT packet waiting when idle" }
            network[0]?.first?.send(nat) ?: error("Cannot find NIC:0")
            println("NAT:SEND:$nat")
            if (terminate(0, nat)) {
                return
            }
        }
    } while (network.values.all { !it.second.isHalted() })
}

fun main() {
    val code = readProgram(File("input.txt"))
    val a1 = solution1(code)
    println("First Packet Y = $a1")
    require(a1 == 22877L)
    val a2 = solution2(code)
    println("NAT Y twice = $a2")
    require(a2 == 15210L)
}

fun solution2(code: List<Long>): Long {
    var lastNAT: Packet? = null
    val network = createComputers(code, 0..49)
    var answer = -1L
    runNetwork(network) { address, packet ->
        var terminate = false
        if (address == 0L) {
            if (lastNAT == null) {
                lastNAT = packet
            } else {
                if (packet.y == lastNAT?.y) {
                    answer = packet.y
                    terminate = true
                }
                lastNAT = packet
            }
        }
        terminate
    }
    return answer
}

fun solution1(code: List<Long>): Long {
    val network = createComputers(code, 0..49)
    var answer = -1L
    runNetwork(network) { address, packet ->
        var terminate = false
        if (address == 255L) {
            answer = packet.y
            terminate = true
        }
        terminate
    }
    return answer
}


