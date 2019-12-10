#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

// tag::instruction[]
typealias Instruction = Int
fun Instruction.opCode() : Int = this.toString().padStart(5, '0').drop(3).toInt()
fun Instruction.parameterModes() : List<ParameterMode> {
    val parameterModeCodes = this.toString().padStart(5, '0').reversed().drop(2).toCharArray()
    return parameterModeCodes
        .map { code ->
            ParameterMode.values().find { mode -> mode.code == code } ?: ParameterMode.ERROR
        }
}

typealias Parameter = Pair<Int, ParameterMode>
// end::instruction[]

// tag::enums[]
enum class Operation(val code: Int, val numberOfParameters: Int) {
    ADD(1, 3),
    MULTIPLY(2, 3),
    INPUT(3, 1),
    OUTPUT(4, 1),
    JUMP_IF_TRUE(5, 2),
    JUMP_IF_FALSE(6, 2),
    LESS_THAN(7, 3),
    EQUALS(8, 3),
    STOP(99, 0),
    ERROR(-1, 0)
}

enum class ParameterMode(val code: Char) {
    POSITION('0'),
    IMMEDIATE('1'),
    ERROR('?')
}
// end::enums[]

// tag::cpu[]
class CPU(var memory: List<Int>) {
    private var instructionPointer = 0

    fun run() {
        instructionPointer = 0;

        while (instructionPointer != -1) {
            val instruction: Instruction = memory[instructionPointer]
            val operation = loadOperation(instruction.opCode())
            val parameters = loadParameters(operation, instruction.parameterModes())
            process(operation, parameters)
        }
    }

    private fun loadOperation(opCode: Int) = Operation.values().find { it.code == opCode } ?: Operation.ERROR

    private fun loadParameters(operation: Operation, parameterModes: List<ParameterMode>): List<Parameter> {
        val parameters = mutableListOf<Parameter>()

        for ((index, parameterPointer) in ((instructionPointer + 1)..(instructionPointer + operation.numberOfParameters)).withIndex()) {
            parameters.add(Parameter(memory[parameterPointer], parameterModes[index]))
        }

        return parameters
    }

    private fun process(operation: Operation, parameters: List<Parameter>) {
        println("$operation with $parameters")
        when(operation) {
            Operation.ADD -> add(parameters)
            Operation.MULTIPLY -> multiply(parameters)
            Operation.INPUT -> input(parameters)
            Operation.OUTPUT -> output(parameters)
            Operation.JUMP_IF_TRUE -> jumpIfTrue(parameters)
            Operation.JUMP_IF_FALSE -> jumpIfFalse(parameters)
            Operation.LESS_THAN -> lessThan(parameters)
            Operation.EQUALS -> equals(parameters)
            Operation.STOP -> stop()
            else -> error()
        }
    }

    private fun add(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        val outPointer = parameters[2].first

        val updatedMemory = memory.toMutableList()
        updatedMemory[outPointer] = first + second

        memory = updatedMemory
        instructionPointer += 4
    }

    private fun multiply(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        val outPointer = parameters[2].first

        val updatedMemory = memory.toMutableList()
        updatedMemory[outPointer] = first * second

        memory = updatedMemory
        instructionPointer += 4
    }

    private fun input(parameters: List<Parameter>) {
        print("Input: ")
        val input = readLine()?.toInt()

        if (input != null) {
            val updatedMemory = memory.toMutableList()
            updatedMemory[parameters[0].first] = input

            memory = updatedMemory
            instructionPointer += 2
        } else {
            throw RuntimeException("Error while reading from stdin")
        }
    }

    private fun output(parameters: List<Parameter>) {
        val value = load(parameters[0])
        println(value)

        instructionPointer += 2
    }

    private fun jumpIfTrue(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        if (first != 0) {
            instructionPointer = second
        } else {
            instructionPointer += 3
        }
    }

    private fun jumpIfFalse(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        if (first == 0) {
            instructionPointer = second
        } else {
            instructionPointer += 3
        }
    }

    private fun lessThan(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        val outPointer = parameters[2].first

        val updatedMemory = memory.toMutableList()
        if (first < second) {
            updatedMemory[outPointer] = 1
        } else {
            updatedMemory[outPointer] = 0
        }

        memory = updatedMemory
        instructionPointer += 4
    }

    private fun equals(parameters: List<Parameter>) {
        val first = load(parameters[0])
        val second = load(parameters[1])
        val outPointer = parameters[2].first

        val updatedMemory = memory.toMutableList()
        if (first == second) {
            updatedMemory[outPointer] = 1
        } else {
            updatedMemory[outPointer] = 0
        }

        memory = updatedMemory
        instructionPointer += 4
    }

    private fun stop() {
        instructionPointer = -1
    }

    private fun error() {
        throw RuntimeException("Unknown operation")
    }

    private fun load(parameter: Parameter) =
        when (parameter.second) {
            ParameterMode.POSITION -> memory[parameter.first]
            ParameterMode.IMMEDIATE -> parameter.first
            else -> throw RuntimeException("Unknown parameter found")
        }
}
// end::cpu[]

// tag::firstAndSecondStar[]
val program = File("./input.txt").readText().split(",").map { it.toInt() }
CPU(program).run()
// end::firstAndSecondStar[]
