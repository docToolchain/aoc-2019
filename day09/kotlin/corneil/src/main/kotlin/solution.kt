package com.github.corneil.aoc2019.day9

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val code = readProgram(File(fileName))

    val output = Program(code).executeProgram(listOf(1L))
    println("Output = ${output.outputs()}")

    val output2 = Program(code).executeProgram(listOf(2L))
    println("Output = ${output2.outputs()}")
}
