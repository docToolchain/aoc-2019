#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

// tag::intcodeComputer[]
typealias Instruction = Long

fun Instruction.opCode(): Long = this.toString().padStart(5, '0').drop(3).toLong()
fun Instruction.parameterModes(): List<ParameterMode> {
    val parameterModeCodes = this.toString().padStart(5, '0').reversed().drop(2).toCharArray()
    return parameterModeCodes
        .map { code ->
            ParameterMode.values().find { mode -> mode.code == code } ?: ParameterMode.ERROR
        }
}

typealias Parameter = Pair<Long, ParameterMode>

enum class Operation(val code: Long, val numberOfParameters: Int) {
    ADD(1, 3),
    MULTIPLY(2, 3),
    INPUT(3, 1),
    OUTPUT(4, 1),
    JUMP_IF_TRUE(5, 2),
    JUMP_IF_FALSE(6, 2),
    LESS_THAN(7, 3),
    EQUALS(8, 3),
    RELATIVE_BASE_OFFSET(9, 1),
    STOP(99, 0),
    ERROR(-1, 0)
}

enum class ParameterMode(val code: Char) {
    POSITION('0'),
    IMMEDIATE('1'),
    RELATIVE('2'),
    ERROR('?')
}

class CPU(
    val program: List<Long>,
    memorySize: Int,
    val inputProvider: () -> Long? = {
        print("Input: ")
        readLine()?.toLong()
    },
    val outputConsumer: (Long) -> Unit = { value -> println(value) }
) {
    private var instructionPointer = 0
    private var relativeBase = 0
    private var memory = Array(memorySize) { index -> if (index < program.size) program[index] else 0L }

    fun run() {
        instructionPointer = 0;

        while (instructionPointer != -1) {
            val instruction: Instruction = memory[instructionPointer]
            val operation = loadOperation(instruction.opCode())
            val parameters = loadParameters(operation, instruction.parameterModes())
            process(operation, parameters)
        }
    }

    private fun loadOperation(opCode: Long) = Operation.values().find { it.code == opCode } ?: Operation.ERROR

    private fun loadParameters(operation: Operation, parameterModes: List<ParameterMode>): List<Parameter> {
        val parameters = mutableListOf<Parameter>()

        for ((index, parameterPointer) in ((instructionPointer + 1)..(instructionPointer + operation.numberOfParameters)).withIndex()) {
            parameters.add(Parameter(memory[parameterPointer], parameterModes[index]))
        }

        return parameters
    }

    private fun process(operation: Operation, parameters: List<Parameter>) {
        // activate for debugging: println("$operation with $parameters")
        when (operation) {
            Operation.ADD -> add(parameters)
            Operation.MULTIPLY -> multiply(parameters)
            Operation.INPUT -> input(parameters)
            Operation.OUTPUT -> output(parameters)
            Operation.JUMP_IF_TRUE -> jumpIfTrue(parameters)
            Operation.JUMP_IF_FALSE -> jumpIfFalse(parameters)
            Operation.LESS_THAN -> lessThan(parameters)
            Operation.EQUALS -> equals(parameters)
            Operation.RELATIVE_BASE_OFFSET -> relativeBaseOffset(parameters)
            Operation.STOP -> stop()
            else -> error()
        }
    }

    private fun add(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])

        write(parameters[2], first + second)
        instructionPointer += 4
    }

    private fun multiply(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])

        write(parameters[2], first * second)
        instructionPointer += 4
    }

    private fun input(parameters: List<Parameter>) {
        val input = inputProvider.invoke()

        if (input != null) {
            write(parameters[0], input)
            instructionPointer += 2
        } else {
            throw RuntimeException("Error while reading from stdin")
        }
    }

    private fun output(parameters: List<Parameter>) {
        val value = load(parameters[0])
        outputConsumer.invoke(value)

        instructionPointer += 2
    }

    private fun jumpIfTrue(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        if (first != 0L) {
            instructionPointer = second.toInt()
        } else {
            instructionPointer += 3
        }
    }

    private fun jumpIfFalse(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        if (first == 0L) {
            instructionPointer = second.toInt()
        } else {
            instructionPointer += 3
        }
    }

    private fun lessThan(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])

        write(parameters[2], if (first < second) 1 else 0)
        instructionPointer += 4
    }

    private fun equals(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])

        write(parameters[2], if (first == second) 1 else 0)
        instructionPointer += 4
    }

    private fun relativeBaseOffset(parameters: List<Parameter>) {
        val offset = load(parameters[0])
        relativeBase += offset.toInt()
        instructionPointer += 2
    }

    private fun stop() {
        instructionPointer = -1
    }

    private fun error() {
        throw RuntimeException("Unknown operation")
    }

    private fun load(parameter: Parameter) =
        when (parameter.second) {
            ParameterMode.POSITION -> memory[parameter.first.toInt()]
            ParameterMode.IMMEDIATE -> parameter.first
            ParameterMode.RELATIVE -> memory[(relativeBase + parameter.first).toInt()]
            else -> throw RuntimeException("Unknown parameter found")
        }

    private fun write(parameter: Parameter, value: Long) {
        val index = when (parameter.second) {
            ParameterMode.POSITION -> parameter.first.toInt()
            ParameterMode.RELATIVE -> (relativeBase + parameter.first).toInt()
            else -> throw RuntimeException("Illegal parameter mode for write operation found")
        }

        val updatedMemory = memory.copyOf()
        updatedMemory[index] = value
        memory = updatedMemory
    }

}
// end::intcodeComputer[]

// tag::arcadeCabinet[]
enum class InputState {
    READ_X,
    READ_Y,
    READ_TILE_ID
}

class ArcadeCabinet {

    val screen: MutableSet<Triple<Long, Long, Long>> = mutableSetOf()

    private var inputState = InputState.READ_X
    private var currentInput = Triple(-1L, -1L, -1L)
    private var paddleX = -1L
    private var ballX = -1L

    var score = 0L
        private set

    fun readNext(input: Long) {
        when (inputState) {
            InputState.READ_X -> {
                currentInput = Triple(input, -1L, -1L)
                inputState = InputState.READ_Y
            }
            InputState.READ_Y -> {
                currentInput = currentInput.copy(second = input)
                inputState = InputState.READ_TILE_ID
            }
            InputState.READ_TILE_ID -> {
                currentInput = currentInput.copy(third = input)
                inputState = InputState.READ_X
                draw(currentInput)
                currentInput = Triple(-1L, -1L, -1L)
            }
        }
    }

    private fun draw(input: Triple<Long, Long, Long>) {
        if (input.first == -1L && input.second == 0L) {
            score = input.third
        } else {
            val previousTile = screen.find { it.first == input.first && it.second == input.second }
            if (previousTile != null) {
                screen.remove(previousTile)
            }
            screen.add(input)

            if (input.third == 3L) {
                paddleX = input.first
            }
            if (input.third == 4L) {
                ballX = input.first
            }
        }
    }

    fun joystick() =
        when {
            paddleX < ballX -> 1L
            paddleX > ballX -> -1L
            else -> 0L
        }

}
// end::arcadeCabinet[]

// tag::firstStar[]
val program = File("./input.txt").readText().split(",").map { it.toLong() }

fun firstStar(program: List<Long>) {
    val arcadeCabinet = ArcadeCabinet()
    CPU(program.toList(), 10000, { throw RuntimeException("Should not happen") }, arcadeCabinet::readNext).run()

    println("First star: ${arcadeCabinet.screen.filter { it.third == 2L }.count()}")
}

firstStar(program)
// end::firstStar[]

// tag::secondStar[]
fun secondStar(program: List<Long>) {
    val arcadeCabinet = ArcadeCabinet()
    CPU(program.toList(), 10000, arcadeCabinet::joystick, arcadeCabinet::readNext).run()

    println("Second star: ${arcadeCabinet.score}")
}

val hackedProgram = program.toMutableList()
hackedProgram[0] = 2

secondStar(hackedProgram)
// end::secondStar[]