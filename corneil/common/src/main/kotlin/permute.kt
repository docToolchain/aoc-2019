package com.github.corneil.aoc2019.common

import java.math.BigInteger

// found on https://rosettacode.org/wiki/Permutations#Kotlin
fun <T> permute(input: List<T>): List<List<T>> {
    if (input.isEmpty()) return emptyList()
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

fun permutationsBI(n: BigInteger): BigInteger =
    n * if (n > BigInteger.ONE) permutationsBI(n - BigInteger.ONE) else BigInteger.ONE

fun permutations(n: Int): BigInteger = permutationsBI(n.toBigInteger())

fun <T> permuteInvoke(input: List<T>, handlePermuation: (List<T>) -> Unit) {
    if (input.size == 1) {
        handlePermuation(input)
    } else {
        val toInsert = input[0]
        permuteInvoke(input.drop(1)) { perm ->
            for (i in 0..perm.size) {
                val newPerm = perm.toMutableList()
                newPerm.add(i, toInsert)
                handlePermuation(newPerm)
            }
        }
    }
}

fun <T> permuteArray(input: Array<T>, makeArray: (Collection<T>) -> Array<T>): List<Array<T>> {
    if (input.isEmpty()) return emptyList()
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<Array<T>>()
    val toInsert = input[0]
    for (perm in permuteArray(makeArray(input.drop(1)), makeArray)) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(makeArray(newPerm))
        }
    }
    return perms
}
