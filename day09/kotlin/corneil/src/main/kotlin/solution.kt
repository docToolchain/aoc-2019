package com.github.corneil.aoc2019.day9

import com.github.corneil.aoc2019.day9.ParameterMode.IMMEDIATE
import com.github.corneil.aoc2019.day9.ParameterMode.POSITION
import com.github.corneil.aoc2019.day9.ParameterMode.RELATIVE
import java.io.File

enum class ParameterMode(val mode: Int) {
    POSITION(0),
    IMMEDIATE(1),
    RELATIVE(2)
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

fun paramModesFrom(mode: Long): ParameterModes {
    var remainingMode = mode
    val result = mutableListOf<ParameterMode>()
    while (remainingMode > 0) {
        result.add(parameterMode((remainingMode % 10).toInt()))
        remainingMode = remainingMode / 10
    }
    return ParameterModes(result)
}

fun readProgram(input: String) = input.split(",").map { it.toLong() }

fun readProgram(input: File) = readProgram(input.readText().trim())

data class ProgramCounter(val pc: Int, val run: Boolean) {
    fun add(value: Int): ProgramCounter = copy(pc + value)
    fun jump(target: Int): ProgramCounter = copy(pc = target)
    fun halt(): ProgramCounter = copy(run = false)
}

class ProgramState(val memory: MutableList<Long>, val inputs: MutableList<Long> = mutableListOf()) {
    var counter = ProgramCounter(0, true)
    var base = 0L
    val output = mutableListOf<Long>()
    fun outputs() = output.toList()

    fun paramModes() = paramModesFrom(read(counter.pc.toLong()) / 100L)

    fun deref(parameterMode: ParameterMode, address: Long): Long {
        val result = when (parameterMode) {
            IMMEDIATE -> address
            POSITION  -> read(address)
            RELATIVE  -> read(base + address)
        }
        return result
    }

    fun read(address: Long): Long = if (address < memory.size) memory[address.toInt()] else 0L

    fun assign(address: Long, value: Long) {
        require(address >= 0) { "Memory address cannot be negative" }
        while (memory.size < address + 1) {
            memory.add(0L)
        }
        memory[address.toInt()] = value
    }

    fun applyOperation(operation: (Long, Long) -> Long): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = operation(value1, value2)
        assign(address(3), result)
        return counter.add(4)
    }

    fun saveInput(): ProgramCounter {
        assert(inputs.isNotEmpty())
        assign(address(1), inputs.removeAt(0))
        return counter.add(2)
    }

    fun param(index: Int): Long {
        val parameterModes = paramModes()
        return deref(parameterModes[index - 1], read((counter.pc + index).toLong()))
    }

    fun address(index: Int): Long {
        val parameterModes = paramModes()
        val address = read((counter.pc + index).toLong())
        return if (parameterModes[index - 1] == RELATIVE) {
            base + address
        } else {
            address
        }
    }

    fun operationLessThan(): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = if (value1 < value2) 1 else 0
        assign(address(3), result.toLong())
        return counter.add(4)
    }

    fun operationEquals(): ProgramCounter {
        val value1 = param(1)
        val value2 = param(2)
        val result = if (value1 == value2) 1 else 0
        assign(address(3), result.toLong())
        return counter.add(4)
    }

    fun jumpIfTrue(): ProgramCounter {
        val value = param(1)
        val target = param(2)
        return if (value != 0L) counter.jump(target.toInt()) else counter.add(3)
    }

    fun jumpIfFalse(): ProgramCounter {
        val value = param(1)
        val target = param(2)
        return if (value == 0L) counter.jump(target.toInt()) else counter.add(3)
    }

    fun outputValue(): ProgramCounter {
        val value = param(1)
        output.add(value)
        return counter.add(2)
    }

    fun halt() = counter.halt()
    fun adjustBase(): ProgramCounter {
        base += param(1).toInt()
        return counter.add(2)
    }

    fun readAndExecute(): ProgramCounter {
        val opcode = (memory[counter.pc] % 100L).toInt()
        return when (opcode) {
            1    -> applyOperation() { a, b -> a + b }
            2    -> applyOperation() { a, b -> a * b }
            3    -> saveInput()
            4    -> outputValue()
            5    -> jumpIfTrue()
            6    -> jumpIfFalse()
            7    -> operationLessThan()
            8    -> operationEquals()
            9    -> adjustBase()
            99   -> halt()
            else -> throw Exception("Invalid opcode $opcode")
        }
    }

    fun executeProgram(): ProgramState {
        counter = ProgramCounter(0, true)
        while (counter.run && counter.pc < memory.size) {
            counter = readAndExecute()
        }
        return this
    }

    // Not that this removes the output from program state and returns the output
    fun executeUntilOutput(input: List<Long>): List<Long> {
        inputs.addAll(input)
        while (counter.run && counter.pc < memory.size && output.isEmpty()) {
            counter = readAndExecute()
        }
        val result = output.toList()
        output.clear()
        return result
    }
}

class Program(private val code: List<Long>) {

    fun executeProgram(input: List<Long>): ProgramState {
        val state = ProgramState(code.toMutableList(), input.toMutableList())
        state.executeProgram()
        return state
    }

    fun createProgram(): ProgramState {
        return ProgramState(code.toMutableList(), mutableListOf())
    }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val code = readProgram(File(fileName))
    val outut = Program(code).executeProgram(listOf(1L))

    println("Output = ${outut.outputs()}")
    val output2 = Program(code).executeProgram(listOf(2L))
    println("Output = ${output2.outputs()}")
}
