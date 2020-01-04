package com.github.corneil.aoc2019.day22

import com.github.corneil.aoc2019.day22.Instruction.Cut
import com.github.corneil.aoc2019.day22.Instruction.Increment
import com.github.corneil.aoc2019.day22.Instruction.Reverse
import java.io.File
import java.math.BigInteger

infix fun BigInteger.`%%`(b: BigInteger): BigInteger {
    val r = this % b
    return if (r < BigInteger.ZERO) r + b else r
}

infix fun Long.`%%`(b: Long): Long {
    return (this.toBigInteger() `%%` b.toBigInteger()).toLong()
}

sealed class Instruction {
    data class Increment(val inc: Int) : Instruction()
    data class Cut(val n: Int) : Instruction()
    object Reverse : Instruction()
}

fun parse(input: String): Instruction {
    val r = mapOf<String, (MatchResult) -> Instruction>(
        "deal into new stack" to { _ -> Reverse },
        "deal with increment (\\d+)" to { s -> Increment(s.groupValues.get(1).toInt()) },
        "cut (-?\\d+)" to { s -> Cut(s.groupValues.get(1).toInt()) }
    )
    return r.entries.find {
        it.key.toRegex().matches(input)
    }?.let {
        it.value(it.key.toRegex().matchEntire(input)!!)
    } ?: error("Could not determine $input")
}

fun reverseIndex(index: Long, deck: Long, iterations: Long = 1L): Long {
    return if (iterations % 2L == 0L)
        index
    else
        deck - (index + 1)
}

fun reverse(deck: Array<Int>): Array<Int> {
    println("deal into new stack")
    val result = Array<Int>(deck.size) { -1 }
    for (i in 0 until deck.size) {
        val newIndex = reverseIndex(i.toLong(), deck.size.toLong())
        result[newIndex.toInt()] = deck[i]
    }
    return result

}

fun cutIndex(index: Long, n: Int, deck: Long, iterations: Long = 1L): Long {
    val i = index.toBigInteger()
    val cn = n.toBigInteger()
    val d = deck.toBigInteger()
    val N = iterations.toBigInteger()
    return ((N * (i - cn)) `%%` d).toLong()
}

fun cut(deck: Array<Int>, n: Int): Array<Int> {
    println("cut $n")
    val result = Array<Int>(deck.size) { -1 }
    for (i in 0 until deck.size) {
        val newIndex = cutIndex(i.toLong(), n, deck.size.toLong(), 1)
        result[newIndex.toInt()] = deck[i]
    }
    return result
}

fun incrementIndex(index: Long, inc: Int, deck: Long, iterations: Long = 1L): Long {
    if (index == 0L) {
        return 0L
    }
    val N = iterations.toBigInteger()
    val i = index.toBigInteger()
    val c = inc.toBigInteger()
    val d = deck.toBigInteger()
    return (N * i * c `%%` d).toLong()
}

fun increment(deck: Array<Int>, inc: Int): Array<Int> {
    println("deal with increment $inc")
    val result = Array<Int>(deck.size) { -1 }
    for (i in 0 until deck.size) {
        val newIndex = incrementIndex(i.toLong(), inc, deck.size.toLong())
        result[newIndex.toInt()] = deck[i]
    }
    return result
}

fun shuffle(input: String, deck: Array<Int>): Array<Int> {
    val instruction = parse(input)
    return when (instruction) {
        is Instruction.Reverse   -> reverse(deck)
        is Instruction.Increment -> increment(deck, instruction.inc)
        is Instruction.Cut       -> cut(deck, instruction.n)
        else                     -> error("Unknown instruction $instruction")
    }
}

fun shuffleFrom(lines: List<String>, deck: Array<Int>, printIntermediate: Boolean = false): Array<Int> {
    var result = deck
    lines.forEach {
        val before = result.toList().toSet()
        result = shuffle(it, result)
        if (printIntermediate) {
            println(result.joinToString(","))
        }
        val after = result.toList().toSet()
        if (before != after) {
            val diff = before - after
            println("Before $before")
            println("After $after")
            error("A card is missing after $it $diff")
        }
    }
    return result
}

data class Operation(
    val deck: BigInteger,
    val increment: BigInteger = BigInteger.ONE,
    val offset: BigInteger = BigInteger.ZERO
) {
    constructor(
        deck: Long,
        increment: Long = 1L,
        offset: Long = 0L
    ) : this(deck.toBigInteger(), increment.toBigInteger(), offset.toBigInteger())

    operator fun times(right: Operation): Operation {
        return copy(
            increment = increment * right.increment `%%` deck,
            offset = ((offset * right.increment `%%` deck) + right.offset) `%%` deck
        )
    }

    fun pow(x: Long): Operation {
        require(x >= 0L)
        return when {
            x == 0L      -> Operation(this.deck)
            x % 2L == 0L -> (this * this).pow(x / 2L)
            else         -> this * pow(x - 1L)
        }
    }
}

fun shuffleOperations(
    lines: List<String>,
    deck: Long
): Operation {
    var r = Operation(deck)
    for (line in lines) {
        println("Reversing $line")
        val s = parse(line)
        r = when (s) {
            is Reverse   -> {
                r.copy(increment = -r.increment `%%` r.deck, offset = (-r.offset - BigInteger.ONE) `%%` r.deck)
            }
            is Cut       -> {
                val n = s.n.toBigInteger()
                r.copy(offset = (r.offset - n) `%%` r.deck)
            }
            is Increment -> {
                val inc = s.inc.toBigInteger()
                r.copy(
                    offset = r.offset * inc `%%` r.deck,
                    increment = r.increment * inc `%%` r.deck
                )
            }
        }
    }
    return r
}

fun getIndex(s: Operation, index: Long): Long {
    val x = index.toBigInteger()
    return (((s.increment * x `%%` s.deck) + s.offset) `%%` s.deck).toLong()
}

fun getReverseIndex(s: Operation, index: Long): Long {
    require(s.deck.isProbablePrime(10))
    return getIndex(s.pow(s.deck.toLong() - 2L), index)
}

fun applyShuffleReverse(lines: List<String>, index: List<Long>, deck: Long, iterations: Long = 1L): List<Long> {
    require(deck > 0L) { "Deck must have a positive length not $deck" }
    require(iterations > 0L) { "Iteration must be positive not $iterations" }
    require(index.all { it >= 0L }) { "Index must be 0 or greater not $index" }

    val s = shuffleOperations(lines, deck)
    val x = s.pow(iterations)

    return index.map {
        getReverseIndex(x, it)
    }
}

fun applyShuffle(lines: List<String>, index: List<Long>, deck: Long, iterations: Long = 1L): List<Long> {
    require(deck > 0L) { "Deck must have a positive length not $deck" }
    require(iterations > 0L) { "Iteration must be positive not $iterations" }
    require(index.all { it >= 0L }) { "Index must be 0 or greater not $index" }

    val s = shuffleOperations(lines, deck)

    return index.map {
        getIndex(s, it)
    }
}

fun main() {
    val input = File("input.txt").readLines().map { it.trim() }.filter { it.length > 0 }
    require(input.size == 100) { "Expected 100 lines not ${input.size}" }
    val deck = (0 until 10007).toList().toTypedArray()
    require(deck.indexOf(2019) == 2019)
    val result = shuffleFrom(input, deck)
    val index = result.indexOf(2019)
    val index2 = result.indexOf(2020)
    println("Result[2019] = $index, Result[2020] = $index2")
    require(index == 7545)
    require(index2 == 5078)
    // Testing index calculations on same data
    val index3 = applyShuffle(input, listOf(2019L, 2020L), deck.size.toLong(), 1L)
    require(index3[0] == 7545L) { "Expected ${index3[0]} to be 7545" }
    require(index3[1] == 5078L) { "Expected ${index3[1]} to be 5078" }
    // Testing reverse on same data
    val reverse = applyShuffleReverse(input, listOf(7545L, 5078), deck.size.toLong(), 1L)
    val reverseValue = deck[reverse[0]!!.toInt()]
    val reverseValue2 = deck[reverse[1]!!.toInt()]
    require(reverse.first() == 2019L) { "Expected ${reverse.first()} to be 2019" }

    // Large inputs
    val largeDeck = 119_315_717_514_047L
    val iterations = 101_741_582_076_661L
    val largeIndex = applyShuffleReverse(input, listOf(2020L), largeDeck, iterations)
    println("Large Number = ${largeIndex.first()}")
    require(largeIndex.first() == 12_706_692_375_144L)
}

