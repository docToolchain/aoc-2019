package com.github.corneil.aoc2019.day21

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

fun makeInput(input: String): List<Long> {
    val lines = input.trim().split('\n')
    return lines.map { it.trim().map { it.toLong() } + 10L }.flatten()
}

fun printOutput(output: List<Long>): String {
    return output.map { if (it.toInt() < 256) it.toChar().toString() else it.toString() }.joinToString("")
}

fun reportHullDamage(code: List<Long>, springCode:String): Int {
    val program = Program(code)
    val state = program.createProgram()
    val input = mutableListOf<Long>()
    input.addAll(makeInput(springCode))
    var result = 0
    do {
        state.executeUntilInput(input)
        input.clear()
        val output = state.extractOutput()
        result += output.filter { it > 256 }.sum().toInt()
        println(output)
        val str = printOutput(output)
        println(str)

    } while (!state.isHalted())
    return result;
}

fun main() {
    val code = readProgram(File("input.txt"))
    val springCode1 = """
        NOT A J
        NOT B T
        AND D T
        OR T J
        NOT C T
        OR T J
        AND D J
        WALK
    """.trimIndent()
    val damage1 = reportHullDamage(code, springCode1)
    println("Damage:$damage1")
    val springCode2 = """
        NOT A J
        NOT B T
        AND D T
        OR T J
        NOT C T
        OR T J
        NOT A T
        OR T J
        AND H J
        OR E J
        AND D J
        RUN
    """.trimIndent()
    val damage2 = reportHullDamage(code, springCode2)
    println("Damage:$damage2")
}

