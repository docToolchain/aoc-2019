package com.github.corneil.aoc2019

import java.io.File

fun readProgram(input: String) = input.split(",").map { it.toInt() }

fun readProgram(input: File) = readProgram(input.readText().trim())

fun applyOperation(input: MutableList<Int>, pc: Int, operation: (Int, Int) -> Int): Int {
    input[input[pc + 3]] = operation(input[input[pc + 1]], input[input[pc + 2]])
    return pc + 4
}

fun readAndExecute(pc: Int, input: MutableList<Int>): Int {
    val opcode = input[pc]
    return when (opcode) {
        1    -> {
            applyOperation(input, pc) { a, b -> a + b }
        }
        2    -> {
            applyOperation(input, pc) { a, b -> a * b }
        }
        99   -> {
            input.size
        }
        else -> {
            throw Exception("Invalid opcode $opcode")
        }
    }
}

fun executeProgram(input: MutableList<Int>): List<Int> {
    var pc = 0;
    while (pc < input.size) {
        pc = readAndExecute(pc, input)
    }
    return input.toList()
}

// We make a copy of the original for testing an iteration.
fun executeIteration(program: MutableList<Int>, i: Int, j: Int): List<Int> {
    val copyX = mutableListOf<Int>()
    copyX.addAll(program)
    copyX[1] = i
    copyX[2] = j
    return executeProgram(copyX)
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val program = readProgram(File(fileName)).toMutableList()

    val output = executeIteration(program, 12, 2)
    println("Result1=${output[0]}")
    assert(output[0] == 5866714)
    // This is a brute force but with no more than 1000 iteration it isn't a problem. Solvers can wait
    mainLoop@ for (i in 0..99) {
        for (j in 0..99) {
            try {
                val result = executeIteration(program, i, j)
                if (result[0] == 19690720) {
                    val answer = 100 * i + j
                    println("Result=$answer")
                    break@mainLoop;
                }
            } catch (x: Throwable) {
                println("Iteration failed:$x")
            }
        }
    }
}
