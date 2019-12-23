package com.github.corneil.aoc2019.day22

import java.io.File
import kotlin.math.max
import kotlin.math.min

fun reverseIndex(index: Long, deck: Long): Long {
    return deck - index - 1L
}

fun reverse(deck: Array<Int>): Array<Int> {
    println("deal into new stack")
    return deck.reversedArray()
}

fun cutIndex(index: Long, n: Int, deck: Long): Long {
    return (deck - n.toLong() + index) % deck
}

fun cutIndexReverse(index: Long, n: Int, deck: Long): Long {
    return cutIndex(index, n * -1, deck)
}

fun cut(deck: Array<Int>, n: Int): Array<Int> {
    println("cut $n")
    val result = Array<Int>(deck.size) { -1 }
    for (i in 0 until deck.size) {
        val newIndex = cutIndex(i.toLong(), n, deck.size.toLong())
        result[newIndex.toInt()] = deck[i]
    }
    return result
}

fun incrementIndex(index: Long, inc: Int, deck: Long): Long {
    return (((index.toBigInteger() * inc.toBigInteger()) + deck.toBigInteger()) % deck.toBigInteger()).toLong()
}

fun incrementIndexReverse(index: Long, inc: Int, deck: Long): Long {
    if (index == 0L) {
        return 0L
    }
    val r = index / inc
    if (index % inc == 0L) {
        val test = incrementIndex(r, inc, deck)
        require(test == index) { "Expected index=$index to be $r not $test for inc=$inc, deck=$deck " }
        return r
    } else {
        val maxI = inc.toBigInteger() * deck.toBigInteger()
        val r1 = inc.toBigInteger() * index.toBigInteger()
        val r2 = (maxI - r1)
        val result = (r2 % deck.toBigInteger()).toLong()
        if (incrementIndex(result, inc, deck) != index) {
            val attempt = deck - result
            if (incrementIndex(attempt, inc, deck) == index) {
                return attempt
            } else {
                var searches = 0
                var value: Long? = null
                var start1 = attempt
                var start2 = attempt + 1L
                do {
                    if (start1 > 0L) {
                        value = (start1 downTo max(start1 - (inc * 10), 0L)).find {
                            incrementIndex(it, inc, deck) == index
                        }
                        searches += inc * 10
                    }
                    if (value == null) {
                        if (start2 <= deck) {
                            value = (start2 until min(start2 + (inc * 10), deck)).find {
                                incrementIndex(it, inc, deck) == index
                            }
                        }
                        searches += inc * 10
                    }
                    if (value == null) {
                        if (start1 <= 0L && start2 >= deck) {
                            break
                        }
                        start1 = max(start1 - (inc * 10), 0L)
                        start2 = min(start2 + (inc * 10), deck)
                        if (searches % 1000L == 0L) {
                            print("Searching for $index inc=$inc and deck=$deck from $start1 -> $attempt -> $start2\r")
                        }
                    }
                } while (value == null)
                if (value == null) {
                    error("Could not resolve index=$index for inc=$inc and deck=$deck tried $start1 to $start2")
                }
                println("Solution $index inc=$inc and $deck = $value")
                return value
            }
        }
        return result
    }
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

fun shuffle(instruction: String, deck: Array<Int>): Array<Int> {
    return when {
        instruction == "deal into new stack"          -> reverse(deck)
        instruction.startsWith("deal with increment") -> {
            val n = instruction.substringAfterLast(' ').toInt()
            increment(deck, n)
        }
        instruction.startsWith("cut")                 -> {
            val n = instruction.substringAfterLast(' ').toInt()
            cut(deck, n)
        }
        else                                          -> error("Unknown instruction $instruction")
    }
}

fun shuffleReverse(instruction: String, index: Long, deck: Long): Long {
    return when {
        instruction == "deal into new stack"          -> reverseIndex(index, deck)
        instruction.startsWith("deal with increment") -> {
            val n = instruction.substringAfterLast(' ').toInt()
            incrementIndexReverse(index, n, deck)
        }
        instruction.startsWith("cut")                 -> {
            val n = instruction.substringAfterLast(' ').toInt()
            cutIndexReverse(index, n, deck)
        }
        else                                          -> error("Unknown instruction $instruction")
    }
}

fun applyShuffleReverse(lines: List<String>, index: Long, deck: Long): Long {
    var result = index
    for (i in lines.lastIndex downTo 0) {
        result = shuffleReverse(lines[i], result, deck)
    }
    return result
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

fun main() {
    val input = File("input.txt").readLines().map { it.trim() }.filter { it.length > 0 }
    require(input.size == 100) { "Expected 100 lines not ${input.size}" }
    val deck = (0 until 10007).toList().toTypedArray()
    require(deck.indexOf(2019) == 2019)
    val result = shuffleFrom(input, deck)
    val index = result.indexOf(2019)
    println("Result = $index")
    require(index == 7545)
    val reverse = applyShuffleReverse(input, index.toLong(), deck.size.toLong())
    val reverseValue = deck[reverse.toInt()]
    println("Reverse = $reverse, $reverseValue")

    val largeDeck = 119315717514047L
    val iterations = 101741582076661L
    val gcd = iterations.toBigInteger().gcd(largeDeck.toBigInteger())
    println("GCD=$gcd")
    var largeIndex = 2020L
    for (i in 0..iterations) {
        largeIndex = applyShuffleReverse(input, largeIndex, largeDeck)
        if (largeIndex == 2020L) {
            println("Cycle at $i")
            break
        }
        if (i % 100L == 0L) {
            print("Iteration=$i\r")
        }
    }
    println("Large Number = $largeIndex")
}

