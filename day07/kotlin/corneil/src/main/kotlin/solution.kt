package com.github.corneil.aoc2019.day7

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

fun phaseAmplifierTest(code: List<Long>, sequence: LongArray): Long {
    val program = Program(code)
    var amplifierInput = 0L
    sequence.forEach { phase ->
        val result = program.executeProgram(listOf(phase, amplifierInput))
        amplifierInput = result.outputs().first()
    }
    return amplifierInput
}

fun phaseAmplifierFeedback(code: List<Long>, sequence: LongArray): Long {
    val program = Program(code)
    var lastOutput = 0L
    var amplifierInput = listOf(0L)
    val amplifiers = sequence.map { phase -> program.createProgram(listOf(phase)) }
    do {
        amplifiers.forEach { amp ->
            if (!amp.isHalted()) {
                val result = amp.executeUntilOutput(amplifierInput)
                if (result.isNotEmpty()) {
                    lastOutput = result.last()
                    amplifierInput = result
                }
            }
        }
    } while (amplifiers.count { !it.isHalted() } == amplifiers.size)
    return lastOutput
}

// found on https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}

fun permutationGeneration(start: Long, end: Long): List<LongArray> {
    return permute((start..end).toList()).map { it.toLongArray() }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val code = readProgram(File(fileName))
    val maxOutput = permutationGeneration(0L, 4L).map { seq -> phaseAmplifierTest(code, seq) }.max()
    println("Max Output = $maxOutput")
    val maxOutput2 = permutationGeneration(5L, 9L).map { seq -> phaseAmplifierFeedback(code, seq) }.max()
    println("Max Output = $maxOutput2")
}
