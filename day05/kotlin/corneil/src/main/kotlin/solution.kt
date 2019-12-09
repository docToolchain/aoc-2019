package com.github.corneil.aoc2019.day5

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val program = Program(readProgram(File(fileName)))

    val result1 = program.executeProgram(mutableListOf(1L))
    println("Outputs:${result1.outputs()}")

    val result2 = program.executeProgram(mutableListOf(5L))
    println("Outputs:${result2.outputs()}")

}
