package com.github.corneil.aoc2019.day16

import kotlin.math.abs

val basePattern = arrayOf(0, 1, 0, -1).toIntArray()
val bpLen = basePattern.size
inline fun expand(index: Int, pos: Int): Int {
    val offset = ((index + 1) / pos % bpLen)
    return basePattern[offset]
}

fun calculatePhase(value: String, offset: Int = 0): String {
    val input = value.map { it.toString().toLong() }.toTypedArray().toLongArray()
    var result = StringBuilder()
    val posAdjustment = offset - (offset % basePattern.size)
    val size = input.size
    for (pos in 1..size) {
        var line = 0L
        for (index in 0 until size) {
            val mul = expand(index + offset, pos + offset)
            if (mul != 0) {
                line += input[(index + offset) % input.size] * mul.toLong()
            }
        }
        result.append(abs(line) % 10L)
    }
    return result.toString()
}

fun calculatePhases(value: String, phases: Int, offset: Int = 0): String {
    var input = value
    var phase = 0
    repeat(phases) {
        phase += 1
        print("Phase=$phase:${input.length}\r")
        input = calculatePhase(input, offset)
    }
    println("Phase=$phase:${input.length}")
    return input
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val input =
        "59796332430280528211060657577039744056609636505111313336094865900635343682296702094018432765613019371234483818415934914575717134617783515237300919201989706451524069044921384738930172026234872525254689609787752401342687098918804210494391161531008341329016626922990938854681575317559821067933058630688365067790812341475168200215494963690593873250884807840611487288560291748414160551508979374335267106414602526249401281066501677212002980616711058803845336067123298983258010079178111678678796586176705130938139337603538683803154824502761228184209473344607055926120829751532887810487164482307564712692208157118923502010028250886290873995577102178526942152"
    val result = calculatePhases(input, 100)
    println("Result=${result.substring(0, 8)}")
    requireNotNull(result.substring(0, 8) == "36627552") // ensure not broken
    val output = interpretSignal(input)
    println("Message=$output")

}

fun interpretSignal(input: String): String {
    val offset = input.substring(0, 7).toInt()
    val signal = createSignal(input, 10000)
    println("Offset = $offset")
    val signalStart = signal.substring(offset + 1, offset + 9)
    println("Signal Start $signalStart")
    val message = calculatePhases(signalStart, 100, offset + 1)
    return message
}

fun createSignal(input: String, repeats: Int): String {
    val result = StringBuilder()
    repeat(repeats) {
        result.append(input)
    }
    return result.toString()
}
