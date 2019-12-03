#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File
import kotlin.system.exitProcess

// tag::operation[]
typealias OperatorFunction = (Int, List<Int>) -> Pair<Int, MutableList<Int>>

fun determineOperation(position: Int, intcode: List<Int>): OperatorFunction {
    val operation = intcode[position]
    return when (operation) {
        1 -> ::add
        2 -> ::multiply
        99 -> ::stop
        else -> ::error
    }
}
// end::operation[]

// tag::operatorFunctions[]
fun add(position: Int, intcode: List<Int>): Pair<Int, MutableList<Int>> {
    val firstPosition = intcode[position + 1]
    val secondPosition = intcode[position + 2]

    val outPosition = intcode[position + 3]
    val outValue = intcode[firstPosition] + intcode[secondPosition]

    val newIntcode = intcode.toMutableList()
    newIntcode[outPosition] = outValue

    return Pair(position + 4, newIntcode)
}

fun multiply(position: Int, intcode: List<Int>): Pair<Int, MutableList<Int>> {
    val firstPosition = intcode[position + 1]
    val secondPosition = intcode[position + 2]

    val outPosition = intcode[position + 3]
    val outValue = intcode[firstPosition] * intcode[secondPosition]

    val newIntcode = intcode.toMutableList()
    newIntcode[outPosition] = outValue

    return Pair(position + 4, newIntcode)
}

fun stop(position: Int, intcode: List<Int>): Pair<Int, MutableList<Int>> {
    return Pair(-1, intcode.toMutableList())
}

fun error(position: Int, intcode: List<Int>): Pair<Int, MutableList<Int>> {
    println("Unknown op-code ${intcode[position]}")
    return Pair(-1, intcode.toMutableList())
}
// end::operatorFunctions[]

// tag::program[]
fun runProgram(noun: Int, verb: Int): Int {
    var intcode = File("./input.txt").readText().split(",").map { it.toInt() }.toMutableList()
    intcode[1] = noun
    intcode[2] = verb
    var position = 0

    while (position != -1) {
        val operation = determineOperation(position, intcode)
        val result = operation.invoke(position, intcode)
        position = result.first
        intcode = result.second
    }

    return intcode[0]
}
// end::program[]

// tag::firstStar[]
println(runProgram(12, 2))
// end::firstStar[]

// tag::secondStar[]
for (noun in 0..99) {
    for (verb in 0..99) {
        val result = runProgram(noun, verb)
        if (result == 19690720) {
            println("Getting $result for noun $noun and verb $verb -> answer is ${100 * noun + verb}")
            exitProcess(0)
        }
    }
}
// end::secondStar[]
