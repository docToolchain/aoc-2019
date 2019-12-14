package com.github.corneil.aoc2019.intcode

import com.github.corneil.aoc2019.intcode.ParameterMode.IMMEDIATE
import com.github.corneil.aoc2019.intcode.ParameterMode.POSITION
import com.github.corneil.aoc2019.intcode.ParameterMode.RELATIVE
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
} ?: error("Invalid mode $mode")

fun paramModesFrom(mode: Long): ParameterModes {
    var remainingMode = mode
    val result = mutableListOf<ParameterMode>()
    while (remainingMode > 0) {
        result.add(parameterMode((remainingMode % 10).toInt()))
        remainingMode /= 10
    }
    return ParameterModes(result)
}

fun readProgram(input: String) = input.split(",").map { it.toLong() }

fun readProgram(input: File) = readProgram(input.readText().trim())

data class ProgramCounter(val pc: Int, val run: Boolean) {
    fun add(value: Int): ProgramCounter = copy(pc = pc + value)
    fun jump(target: Int): ProgramCounter = copy(pc = target)
    fun halt(): ProgramCounter = copy(run = false)
}

class ProgramState(
    private val memory: MutableList<Long>,
    private val inputs: MutableList<Long> = mutableListOf(),
    private val fetchInput: (() -> Long)? = null,
    private val outputHandler: ((Long) -> Unit)? = null
) {
    var counter = ProgramCounter(0, true)
        private set
    var waitingInput = false
        private set
    private var base = 0L
    private val output = mutableListOf<Long>()
    fun memory() = memory.toList()
    fun memory(index: Int): Long = memory[index]
    fun outputs() = output.toList()
    fun extractOutput(): List<Long> {
        val result = output.toList()
        output.clear()
        return result
    }

    fun isHalted() = !counter.run

    private fun paramModes() = paramModesFrom(read(counter.pc.toLong()) / 100L)

    private fun deref(parameterMode: ParameterMode, address: Long): Long {
        return when (parameterMode) {
            IMMEDIATE -> address
            POSITION  -> read(address)
            RELATIVE  -> read(base + address)
        }
    }

    private fun read(address: Long): Long = if (address < memory.size) memory[address.toInt()] else 0L

    private fun assign(address: Long, value: Long) {
        require(address >= 0) { "Memory address cannot be negative" }
        while (memory.size < address + 1) {
            memory.add(0L)
        }
        memory[address.toInt()] = value
    }

    private fun paramValue(index: Int): Long {
        val parameterModes = paramModes()
        return deref(parameterModes[index - 1], read((counter.pc + index).toLong()))
    }

    private fun paramAddress(index: Int): Long {
        val parameterModes = paramModes()
        val address = read((counter.pc + index).toLong())
        return if (parameterModes[index - 1] == RELATIVE) {
            base + address
        } else {
            address
        }
    }

    private fun applyOperation(operation: (Long, Long) -> Long): ProgramCounter {
        val value1 = paramValue(1)
        val value2 = paramValue(2)
        val result = operation(value1, value2)
        assign(paramAddress(3), result)
        return counter.add(4)
    }

    private fun saveInput(): ProgramCounter {
        val value = if (inputs.isEmpty() && fetchInput != null) {
            fetchInput.invoke()
        } else {
            if (inputs.isEmpty()) {
                waitingInput = true
                return counter
            } else {
                inputs.removeAt(0)
            }
        }
        assign(paramAddress(1), value)
        return counter.add(2)
    }

    private fun operationLessThan(): ProgramCounter {
        val value1 = paramValue(1)
        val value2 = paramValue(2)
        val result = if (value1 < value2) 1 else 0
        assign(paramAddress(3), result.toLong())
        return counter.add(4)
    }

    private fun operationEquals(): ProgramCounter {
        val value1 = paramValue(1)
        val value2 = paramValue(2)
        val result = if (value1 == value2) 1 else 0
        assign(paramAddress(3), result.toLong())
        return counter.add(4)
    }

    private fun jumpIfTrue(): ProgramCounter {
        val value = paramValue(1)
        val target = paramValue(2)
        return if (value != 0L) counter.jump(target.toInt()) else counter.add(3)
    }

    private fun jumpIfFalse(): ProgramCounter {
        val value = paramValue(1)
        val target = paramValue(2)
        return if (value == 0L) counter.jump(target.toInt()) else counter.add(3)
    }

    private fun outputValue(): ProgramCounter {
        val value = paramValue(1)
        if (outputHandler != null) {
            outputHandler.invoke(value)
        } else {
            output.add(value)
        }
        return counter.add(2)
    }

    private fun halt() = counter.halt()
    private fun adjustBase(): ProgramCounter {
        base += paramValue(1).toInt()
        return counter.add(2)
    }

    private fun readAndExecute(): ProgramCounter {
        return when (val opcode = (memory[counter.pc] % 100L).toInt()) {
            1    -> applyOperation { a, b -> a + b }
            2    -> applyOperation { a, b -> a * b }
            3    -> saveInput()
            4    -> outputValue()
            5    -> jumpIfTrue()
            6    -> jumpIfFalse()
            7    -> operationLessThan()
            8    -> operationEquals()
            9    -> adjustBase()
            99   -> halt()
            else -> error("Invalid opcode $opcode")
        }
    }

    fun execute(): ProgramState {
        waitingInput = false
        counter = ProgramCounter(0, true)
        while (counter.run && counter.pc < memory.size && !waitingInput) {
            counter = readAndExecute()
        }
        return this
    }

    // Not that this removes the output from program state and returns the output
    fun executeUntilOutput(input: List<Long>): List<Long> {
        waitingInput = false
        inputs.addAll(input)
        while (counter.run && counter.pc < memory.size && output.isEmpty() && !waitingInput) {
            counter = readAndExecute()
        }
        return extractOutput()
    }

    fun executeUntilInput(input: List<Long>) {
        waitingInput = false
        inputs.addAll(input)
        while (counter.run && counter.pc < memory.size && !waitingInput) {
            counter = readAndExecute()
        }
    }
}

class Program(
    private val code: List<Long>,
    private val globalFetchInput: (() -> Long)? = null,
    private val globalOutputHandler: ((Long) -> Unit)? = null
) {

    fun executeProgram(
        input: List<Long>,
        fetchInput: (() -> Long)? = null,
        outputHandler: ((Long) -> Unit)? = null
    ): ProgramState {
        val state = ProgramState(
            code.toMutableList(),
            input.toMutableList(),
            fetchInput ?: globalFetchInput,
            outputHandler ?: globalOutputHandler
        )
        state.execute()
        return state
    }

    fun createProgram(
        input: List<Long> = emptyList(),
        fetchInput: (() -> Long)? = null,
        outputHandler: ((Long) -> Unit)? = null
    ): ProgramState {
        return ProgramState(
            code.toMutableList(),
            input.toMutableList(),
            fetchInput ?: globalFetchInput,
            outputHandler ?: globalOutputHandler
        )
    }
}
