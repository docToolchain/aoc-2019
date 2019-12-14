package com.github.corneil.aoc2019.day14

import java.io.File

typealias ChemicalQty = Pair<Long, String>

data class ReactionFormula(val inputs: List<ChemicalQty>, val output: ChemicalQty)

fun readChemicalQty(it: String): ChemicalQty {
    val tokens = it.trim().split(" ")
    require(tokens.size == 2)
    return ChemicalQty(tokens[0].toLong(), tokens[1].trim())
}

fun readReaction(input: String): ReactionFormula {
    val inputs = input.substringBefore("=>").split(",").map {
        readChemicalQty(it)
    }
    return ReactionFormula(inputs, readChemicalQty(input.substringAfter("=>")))
}

fun readReactions(input: List<String>) = input.map { readReaction(it) }

fun add(value: MutableMap<String, Long>, output: ChemicalQty) {
    val qty = value[output.second]
    if (qty != null) {
        value[output.second] = qty + output.first
    } else {
        value[output.second] = output.first
    }
}

fun produce(
    output: ChemicalQty,
    formulae: Map<String, ReactionFormula>,
    sideEffect: MutableMap<String, Long>,
    used: MutableMap<String, Long>
) {
    val remain = sideEffect[output.second]
    if (remain != null && remain > 0L) {
        useRemainingChemicals(remain, output, formulae, sideEffect, used)
    } else {
        val formula = formulae[output.second]
        if (formula != null) {
            applyFormula(output, formula, formulae, sideEffect, used)
        } else {
            // This is usually the ORE
            add(used, output)
        }
    }
}

fun useRemainingChemicals(
    remain: Long,
    output: ChemicalQty,
    formulae: Map<String, ReactionFormula>,
    sideEffect: MutableMap<String, Long>,
    used: MutableMap<String, Long>
) {
    if (remain >= output.first) {
        val qty = remain - output.first
        sideEffect[output.second] = qty
        require(qty >= 0) { "Expected Qty to be zero or greater for ${output.second} not $qty" }
    } else {
        sideEffect.remove(output.second)
        produce(ChemicalQty(output.first - remain, output.second), formulae, sideEffect, used)
    }
}

fun applyFormula(
    output: ChemicalQty,
    formula: ReactionFormula,
    formulae: Map<String, ReactionFormula>,
    sideEffect: MutableMap<String, Long>,
    used: MutableMap<String, Long>
) {
    val repeats = if (output.first < formula.output.first) {
        1L
    } else {
        output.first / formula.output.first +
                if (output.first % formula.output.first == 0L) 0L else 1L
    }
    val produced = apply(formula, repeats, formulae, sideEffect, used)
    if (output.first < produced.first) {
        sideEffect[output.second] = produced.first - output.first
    }
}

fun apply(
    formula: ReactionFormula,
    repeats: Long,
    formulae: Map<String, ReactionFormula>,
    sideEffect: MutableMap<String, Long>,
    used: MutableMap<String, Long>
): ChemicalQty {
    formula.inputs.forEach {
        produce(ChemicalQty(it.first * repeats, it.second), formulae, sideEffect, used)
    }
    return ChemicalQty(formula.output.first * repeats, formula.output.second)
}

fun determineInputForOutput(input: String, output: ChemicalQty, formulae: List<ReactionFormula>): Long {
    val formula = formulae.map { it.output.second to it }.toMap()
    val used = mutableMapOf<String, Long>()
    val sideEffect = mutableMapOf<String, Long>()
    produce(output, formula, sideEffect, used)
    println("$output = $used, Side Effects = $sideEffect")
    return used[input] ?: 0L
}

fun executeReactions(
    increment: Long,
    input: ChemicalQty,
    output: String,
    formula: Map<String, ReactionFormula>,
    used: MutableMap<String, Long>
): Pair<Long, Long> {
    val sideEffect = mutableMapOf<String, Long>()
    var inc = increment
    var counter = 0L
    while (input.first > used[input.second] ?: 0L) {
        produce(ChemicalQty(inc, output), formula, sideEffect, used)
        counter += inc
        if (counter % 10000L == 0L) {
            print("produce $output for $input = $counter\r")
        }
        val usedSoFar = used[input.second] ?: 0L
        if (inc == 100L && (10L * usedSoFar / input.first > 7L)) {
            inc = 10L
        } else if (inc == 10L && (10L * usedSoFar / input.first > 8L)) {
            inc = 1L
        }
    }
    println("Used = $used, Side Effect = $sideEffect")
    return Pair(counter, inc)
}

fun determineOuputForInput(input: ChemicalQty, output: String, formulae: List<ReactionFormula>): Long {
    val formula = formulae.map { it.output.second to it }.toMap()
    val used = mutableMapOf<String, Long>()
    var increment = 1000L // Start with 100 and drop down to save some time
    var counter: Long
    do {
        increment /= 10L
        val pair = executeReactions(increment, input, output, formula, used)
        counter = pair.first
        increment = pair.second
    } while (increment > 1L) // If last increment isn't 1 redo
    val result = if (used[input.second] == input.first) counter else counter - 1L
    println("output $input = $result")
    return result
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val formulae = readReactions(File(fileName).readLines())
    val output = determineInputForOutput("ORE", ChemicalQty(1L, "FUEL"), formulae)
    println("Output = ORE = $output")
    require(output == 612880L) // ensure check for refactoring
    val fuel = determineOuputForInput(ChemicalQty(1000000000000L, "ORE"), "FUEL", formulae)
    println("Fuel = $fuel")
    require(fuel == 2509120L)
}

