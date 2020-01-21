package com.github.corneil.aoc2019.common

inline fun <T> filterUnique(input: List<T>): List<T> {
    val visiting = mutableSetOf<T>()
    return input.filter { node ->
        if (visiting.contains(node)) {
            false
        } else {
            visiting.add(node)
            true
        }
    }
}
