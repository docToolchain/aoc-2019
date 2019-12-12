#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque

// tag::createPermutations[]
fun createPermutations(input: Set<Int>, combination: List<Int>): List<List<Int>> {
    val result: MutableList<List<Int>> = mutableListOf()

    for (digit in input) {
        if (input.size == 1) {
            result.add(combination + digit)
        } else {
            val combinations = createPermutations(input - digit, combination + digit)
            result.addAll(combinations)
        }
    }

    return result
}
// end::createPermutations[]

// tag::day05[]
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

class CPU(var memory: List<Int>,
          val inputProvider: () -> Int? = { readLine()?.toInt() },
          val outputConsumer: (Int) -> Unit = { value -> println(value) }
) {
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
        //println("$operation with $parameters")
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
        val input = inputProvider.invoke()

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
        outputConsumer.invoke(value)

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
// end::day05[]

// tag::readProgram[]
val program = File("./input.txt").readText().split(",").map { it.toInt() }
// end::readProgram[]

// tag::firstStar[]
fun runProgramOnAmplifiers(program: List<Int>, phaseSettings: List<Int>): Int {
    val amplifier1In = arrayOf(phaseSettings[0], 0).iterator()
    var amplifier1Out: Int? = null
    val amplifier1 = CPU(program.toList(), { amplifier1In.next() }, { amplifier1Out = it })
    amplifier1.run()

    val amplifier2In = arrayOf(phaseSettings[1], amplifier1Out).iterator()
    var amplifier2Out: Int? = null
    val amplifier2 = CPU(program.toList(), { amplifier2In.next() }, { amplifier2Out = it })
    amplifier2.run()

    val amplifier3In = arrayOf(phaseSettings[2], amplifier2Out).iterator()
    var amplifier3Out: Int? = null
    val amplifier3 = CPU(program.toList(), { amplifier3In.next() }, { amplifier3Out = it })
    amplifier3.run()

    val amplifier4In = arrayOf(phaseSettings[3], amplifier3Out).iterator()
    var amplifier4Out: Int? = null
    val amplifier4 = CPU(program.toList(), { amplifier4In.next() }, { amplifier4Out = it })
    amplifier4.run()

    val amplifier5In = arrayOf(phaseSettings[4], amplifier4Out).iterator()
    var amplifier5Out: Int? = null
    val amplifier5 = CPU(program.toList(), { amplifier5In.next() }, { amplifier5Out = it })
    amplifier5.run()

    return amplifier5Out ?: throw RuntimeException("Result is null")
}

val combinations = createPermutations(setOf(0, 1, 2, 3, 4), emptyList())
val firstStar = combinations.map { runProgramOnAmplifiers(program, it) }.max()

println(firstStar)
// end::firstStar[]

// tag::secondStar[]
class AmplifierRunnable(
    val amplifierId: String,
    val program: List<Int>,
    val inputQueue: BlockingQueue<Int>,
    val outputQueue: BlockingQueue<Int>
) : Runnable {
    override fun run() {
        val amplifier = CPU(program.toList(), { inputQueue.take() }, { outputQueue.put(it) })
        amplifier.run()
        //println("Amplifier $amplifierId: $outputQueue")
    }
}

fun runProgramOnAmplifiersWithFeedback(program: List<Int>, phaseSettings: List<Int>): Int {
    val amp1In = LinkedBlockingDeque<Int>()
    amp1In.put(phaseSettings[0])
    val amp2In = LinkedBlockingDeque<Int>()
    amp2In.put(phaseSettings[1])
    val amp3In = LinkedBlockingDeque<Int>()
    amp3In.put(phaseSettings[2])
    val amp4In = LinkedBlockingDeque<Int>()
    amp4In.put(phaseSettings[3])
    val amp5In = LinkedBlockingDeque<Int>()
    amp5In.put(phaseSettings[4])

    val amplifier1 = Thread(AmplifierRunnable("1", program, amp1In, amp2In))
    val amplifier2 = Thread(AmplifierRunnable("2", program, amp2In, amp3In))
    val amplifier3 = Thread(AmplifierRunnable("3", program, amp3In, amp4In))
    val amplifier4 = Thread(AmplifierRunnable("4", program, amp4In, amp5In))
    val amplifier5 = Thread(AmplifierRunnable("5", program, amp5In, amp1In))

    amplifier1.start()
    amplifier2.start()
    amplifier3.start()
    amplifier4.start()
    amplifier5.start()

    amp1In.put(0)

    amplifier1.join()
    amplifier2.join()
    amplifier3.join()
    amplifier4.join()
    amplifier5.join()

    return amp1In.take()
}

val combinations2 = createPermutations(setOf(5, 6, 7, 8, 9), emptyList())
val secondStar = combinations2.map { runProgramOnAmplifiersWithFeedback(program, it) }.max()

println(secondStar)
// end::secondStar[]