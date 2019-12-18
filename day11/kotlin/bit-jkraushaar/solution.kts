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

// tag::panel[]
data class Panel(val coordinates: Pair<Int, Int>, var color: Long)
// end::panel[]

// tag::paintingRobot[]
enum class CommandState() {
    PAINT,
    ROTATE
}

enum class Direction() {
    UP,
    RIGHT,
    DOWN,
    LEFT
}

class PaintingRobot(initialColor: Long) {
    private var cpu: CPU? = null
    private var coordinates = Pair(0, 0)
    var visitedPanelSet: Set<Panel> = setOf(Panel(Pair(0, 0), initialColor))
    private var commandState = CommandState.PAINT
    private var direction = Direction.UP

    fun loadProgram(pathname: String) {
        val program = File(pathname).readText().split(",").map { it.toLong() }
        cpu = CPU(program, 1024, this::readCamera, this::next)
    }

    fun startRobot() = cpu?.run() ?: throw RuntimeException("Load program first")

    private fun readCamera() = findOrCreatePanelAt(coordinates).color

    private fun next(command: Long) {
        when (commandState) {
            CommandState.PAINT -> {
                findOrCreatePanelAt(coordinates).color = command
                commandState = CommandState.ROTATE
            }
            CommandState.ROTATE -> {
                rotate(command)
                move()
                commandState = CommandState.PAINT
            }
        }
    }

    private fun findOrCreatePanelAt(theCoordinates: Pair<Int, Int>): Panel {
        val panel = this.visitedPanelSet.find { it.coordinates == theCoordinates } ?: Panel(theCoordinates, 0)
        this.visitedPanelSet = this.visitedPanelSet + panel
        return panel
    }

    private fun rotate(command: Long) {
        direction = when (command) {
            0L -> when (direction) {
                Direction.UP -> Direction.LEFT
                Direction.LEFT -> Direction.DOWN
                Direction.DOWN -> Direction.RIGHT
                Direction.RIGHT -> Direction.UP
            }
            1L -> when (direction) {
                Direction.UP -> Direction.RIGHT
                Direction.RIGHT -> Direction.DOWN
                Direction.DOWN -> Direction.LEFT
                Direction.LEFT -> Direction.UP
            }
            else -> throw RuntimeException("Unknown rotation command: $command")
        }
    }

    private fun move() {
        coordinates = when (direction) {
            Direction.UP -> Pair(coordinates.first, coordinates.second + 1)
            Direction.RIGHT -> Pair(coordinates.first + 1, coordinates.second)
            Direction.DOWN -> Pair(coordinates.first, coordinates.second - 1)
            Direction.LEFT -> Pair(coordinates.first - 1, coordinates.second)
        }
    }
}
// end::paintingRobot[]

// tag::firstStar[]
val firstRobot = PaintingRobot(0)
firstRobot.loadProgram("./input.txt")
firstRobot.startRobot()
println("First star: ${firstRobot.visitedPanelSet.size}")
// end::firstStar[]

// tag::secondStar[]
val secondRobot = PaintingRobot(1)
secondRobot.loadProgram("./input.txt")
secondRobot.startRobot()

val visitedCoordinates = secondRobot.visitedPanelSet.map { it.coordinates }
val visitedX = visitedCoordinates.map { it.first }
val visitedY = visitedCoordinates.map { it.second }

for (y in visitedY.max()!! downTo visitedY.min()!!) {
    for (x in visitedX.min()!!..visitedX.max()!!) {
        val color = secondRobot.visitedPanelSet.find { it.coordinates == Pair(x, y) }?.color ?: 0L
        if (color == 0L) {
            print(" ")
        } else {
            print("#")
        }
    }
    println()
}
// end::secondStar[]
