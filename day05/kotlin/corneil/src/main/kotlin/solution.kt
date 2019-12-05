package com.github.corneil.aoc2019.day5

import com.github.corneil.aoc2019.day5.ParameterMode.IMMEDIATE
import com.github.corneil.aoc2019.day5.ParameterMode.POSITION
import java.io.File

enum class ParameterMode(val mode: Int) {
    POSITION(0),
    IMMEDIATE(1)
}

data class ParameterModes(private val modes: List<ParameterMode>) {
    fun get(index: Int) = if (modes.size > index) {
        modes[index]
    } else {
        POSITION
    }
}

fun parameterMode(mode: Int): ParameterMode {
    for (m in ParameterMode.values()) {
        if (m.mode == mode) {
            return m
        }
    }
    throw Exception("Invalid mode $mode")
}

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

class ProgramState(val memory: MutableList<Int>, val inputs: MutableList<Int>) {
    var counter = ProgramCounter(0, true)
    val output = mutableListOf<Int>()
    fun outputs() = output.toList()

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

    fun applyOperation(parameterModes: ParameterModes, operation: (Int, Int) -> Int): ProgramCounter {
        val value1 = deref(parameterModes.get(0), memory[counter.pc + 1])
        val value2 = deref(parameterModes.get(1), memory[counter.pc + 2])
        val result = operation(value1, value2)
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun saveInput(): ProgramCounter {
        assert(inputs.isNotEmpty())
        assign(memory[counter.pc + 1], inputs.removeAt(0))
        return counter.add(2)
    }

    fun operationLessThan(paramMode: ParameterModes): ProgramCounter {
        val value1 = deref(paramMode.get(0), memory[counter.pc + 1])
        val value2 = deref(paramMode.get(1), memory[counter.pc + 2])
        val result = if (value1 < value2) 1 else 0
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun operationEquals(paramMode: ParameterModes): ProgramCounter {
        val value1 = deref(paramMode.get(0), memory[counter.pc + 1])
        val value2 = deref(paramMode.get(1), memory[counter.pc + 2])
        val result = if (value1 == value2) 1 else 0
        assign(memory[counter.pc + 3], result)
        return counter.add(4)
    }

    fun jumpIfTrue(paramMode: ParameterModes): ProgramCounter {
        val value = deref(paramMode.get(0), memory[counter.pc + 1])
        val target = deref(paramMode.get(1), memory[counter.pc + 2])
        return if (value != 0) counter.jump(target) else counter.add(3)
    }

    fun jumpIfFalse(paramMode: ParameterModes): ProgramCounter {
        val value = deref(paramMode.get(0), memory[counter.pc + 1])
        val target = deref(paramMode.get(1), memory[counter.pc + 2])
        return if (value == 0) counter.jump(target) else counter.add(3)
    }

    fun outputValue(paramMode: ParameterModes): ProgramCounter {
        val value = deref(paramMode.get(0), memory[counter.pc + 1])
        output.add(value)
        return counter.add(2)
    }

    fun readAndExecute(): ProgramCounter {
        val IP = memory[counter.pc]
        val opcode = IP % 100
        val paramMode = paramModesFrom(IP / 100)
        return when (opcode % 100) {
            1    -> {
                applyOperation(paramMode) { a, b -> a + b }
            }
            2    -> {
                applyOperation(paramMode) { a, b -> a * b }
            }
            3    -> {
                saveInput()
            }
            4    -> {
                outputValue(paramMode)
            }
            5    -> {
                jumpIfTrue(paramMode)
            }
            6    -> {
                jumpIfFalse(paramMode)
            }
            7    -> {
                operationLessThan(paramMode)
            }
            8    -> {
                operationEquals(paramMode)
            }
            99   -> {
                counter.halt()
            }
            else -> {
                throw Exception("Invalid opcode $opcode")
            }
        }
    }

    fun executeProgram(): List<Int> {
        counter = ProgramCounter(0, true)
        while (counter.run && counter.pc < memory.size) {
            counter = readAndExecute()
        }
        return memory.toList()
    }
}

class Program(private val code: List<Int>) {

    fun executeProgram(input: List<Int>): Pair<List<Int>, List<Int>> {
        val state = ProgramState(code.toMutableList(), input.toMutableList())
        return Pair(state.executeProgram(), state.outputs())
    }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val program = Program(readProgram(File(fileName)))

    val result1 = program.executeProgram(mutableListOf(1))
    println("Outputs:${result1.second}")

    val result2 = program.executeProgram(mutableListOf(5))
    println("Outputs:${result2.second}")

}
