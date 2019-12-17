package com.github.corneil.aoc2019.common

class Combinations(val m: Int, val n: Int) {
    private val combination = IntArray(m)
    val combinations: MutableList<IntArray> = mutableListOf()

    init {
        generate(0)
    }

    private fun generate(k: Int) {
        if (k >= m) {
            combinations.add(combination.toList().toIntArray())
        } else {
            for (j in 0 until n)
                if (k == 0 || j > combination[k - 1]) {
                    combination[k] = j
                    generate(k + 1)
                }
        }
    }
}
