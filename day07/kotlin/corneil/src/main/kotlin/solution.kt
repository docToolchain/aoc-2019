package com.github.corneil.aoc2019.day7

import com.github.corneil.aoc2019.day7.ParameterMode.IMMEDIATE
import com.github.corneil.aoc2019.day7.ParameterMode.POSITION
import java.io.File

enum class ParameterMode(val mode: Int) {
    POSITION(0),
    IMMEDIATE(1)
}

data class ParameterModes(private val modes: List<ParameterMode>) {
    operator fun get(index: Int) = if (modes.size > index) {
        modes[index]
    } else {
        POSITION
    }
}

fun parameterMode(mode: Int) = ParameterMode.values().find {
    it.mode == mode
} ?: throw Exception("Invalid mode $mode")

fun paramModesFrom(mode: Int): ParameterModes {
    var remainingMode = mode
    val result = mutableListOf<ParameterMode>()
    while (remainingMode > 0) {
        result.add(parameterMode(remainingMode % 10))
        remainingMode = remainingMode / 10
    }
    return ParameterModes(result)
}

fun readProgram(input: String) = input.split(",").map { it.toInt() }

fun readProgram(input: File) = readProgram(input.readText().trim())

data class ProgramCounter(val pc: Int, val run: Boolean) {
    fun add(value: Int): ProgramCounter = copy(pc + value)
    fun jump(target: Int): ProgramCounter = copy(pc = target)
    fun halt(): ProgramCounter = copy(run = false)
}

class ProgramState(val memory: MutableList<Int>, val inputs: MutableList<Int> = mutableListOf()) {
    var counter = ProgramCounter(0, true)
    val output = mutableListOf<Int>()
    fun outputs() = output.toList()
    fun paramModes(ip: Int) = paramModesFrom(ip / 100)
    fun deref(parameterMode: ParameterMode, address: Int): Int {
        val result = when (parameterMode) {
            IMMEDIATE -> address
            POSITION  -> memory[address]
        }
        return result
    }

    fun assign(address: Int, value: Int) {
        memory[address] = value
    }

    fun applyOperation(operation: (Int, Int) -> Int): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = operation(value1, value2)
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun saveInput(): ProgramCounter {
        assert(inputs.isNotEmpty())
        assign(memory[counter.pc + 1], inputs.removeAt(0))
        return counter.add(2)
    }

    fun param(index: Int) = deref(paramModes(memory[counter.pc])[index - 1], memory[counter.pc + index])

    fun operationLessThan(): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = if (value1 < value2) 1 else 0
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun operationEquals(): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = if (value1 == value2) 1 else 0
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun jumpIfTrue(): ProgramCounter {
        val value = param(1)
        val target = param(2)
        return if (value != 0) counter.jump(target) else counter.add(3)
    }

    fun jumpIfFalse(): ProgramCounter {
        val value = param(1)
        val target = param(2)
        return if (value == 0) counter.jump(target) else counter.add(3)
    }

    fun outputValue(): ProgramCounter {
        val value = param(1)
        output.add(value)
        return counter.add(2)
    }

    fun halt() = counter.halt()

    fun readAndExecute(): ProgramCounter {
        val opcode = memory[counter.pc] % 100
        return when (opcode) {
            1    -> applyOperation() { a, b -> a + b }
            2    -> applyOperation() { a, b -> a * b }
            3    -> saveInput()
            4    -> outputValue()
            5    -> jumpIfTrue()
            6    -> jumpIfFalse()
            7    -> operationLessThan()
            8    -> operationEquals()
            99   -> halt()
            else -> throw Exception("Invalid opcode $opcode")
        }
    }

    fun executeProgram(): List<Int> {
        counter = ProgramCounter(0, true)
        while (counter.run && counter.pc < memory.size) {
            counter = readAndExecute()
        }
        return memory.toList()
    }

    fun executeUntilOutput(input: List<Int>): List<Int> {
        inputs.addAll(input)
        while (counter.run && counter.pc < memory.size && output.isEmpty()) {
            counter = readAndExecute()
        }
        val result = output.toList()
        output.clear()
        return result
    }
}

class Program(private val code: List<Int>) {

    fun executeProgram(input: List<Int>): Pair<List<Int>, List<Int>> {
        val state = ProgramState(code.toMutableList(), input.toMutableList())
        val results = state.executeProgram()
        return Pair(results, state.outputs())
    }

    fun createProgram(input: List<Int>): ProgramState {
        return ProgramState(code.toMutableList(), input.toMutableList())
    }
}

fun phaseAmplifierTest(code: List<Int>, sequence: IntArray): Int {
    val program = Program(code)
    var amplifierInput = 0
    sequence.forEach { phase ->
        val result = program.executeProgram(listOf(phase, amplifierInput))
        amplifierInput = result.second.first()
    }
    return amplifierInput
}

fun phaseAmplifierFeedback(code: List<Int>, sequence: IntArray): Int {
    val program = Program(code)
    var lastOutput = 0
    var amplifierInput = listOf(0)
    val amplifiers = sequence.map { phase -> program.createProgram(listOf(phase)) }
    do {
        amplifiers.forEach { amp ->
            if (amp.counter.run) {
                val result = amp.executeUntilOutput(amplifierInput)
                if (result.isNotEmpty()) {
                    lastOutput = result.last()
                    amplifierInput = result
                }
            }
        }
    } while (amplifiers.count { it.counter.run } == amplifiers.size)
    return lastOutput
}

// Ugly but it works
fun uglyPermutationGeneration(start: Int, end: Int): List<IntArray> {
    val result = mutableSetOf<IntArray>()
    for (i in start..end) {
        for (j in start..end) {
            for (k in start..end) {
                for (l in start..end) {
                    for (m in start..end) {
                        val permutation = arrayOf(i, j, k, l, m)
                        if (permutation.toSet().size == 5) {
                            result.add(permutation.toIntArray())
                        }
                    }
                }
            }
        }
    }
    return result.toList()
}

// found on https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}

fun permutationGeneration(start: Int, end: Int): List<IntArray> {
    return permute((start..end).toList()).map { it.toIntArray() }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val code = readProgram(File(fileName))
    val maxOutput = permutationGeneration(0, 4).map { seq -> phaseAmplifierTest(code, seq) }.max()
    println("Max Output = $maxOutput")
    val maxOutput2 = permutationGeneration(5, 9).map { seq -> phaseAmplifierFeedback(code, seq) }.max()
    println("Max Output = $maxOutput2")
}
