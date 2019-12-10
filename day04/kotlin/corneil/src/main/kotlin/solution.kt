package com.github.corneil.aoc2019.day4

fun passwordCriteriaLength(input: String): Boolean {
    return input.length == 6
}

fun passwordCriteriaDoubleDigits(input: String): Boolean {
    var prevChar = input[0]
    for (i in 1 until input.length) {
        val c = input[i]
        if (c == prevChar) {
            return true
        }
        prevChar = c
    }
    return false
}

fun passwordCriteriaMustHaveAtLeastOneOnlyDoubleDigitsAndNoMore(input: String): Boolean {
    var prevChar = input[0]
    var matchCount = 0
    for (i in 1 until input.length) {
        val c = input[i]
        if (c == prevChar) {
            matchCount += 1
        } else {
            if (matchCount == 1) {
                return true
            }
            matchCount = 0
        }
        prevChar = c
    }
    return matchCount == 1
}

fun passwordCriteriaIncrease(input: String): Boolean {
    var prevChar = input[0]
    for (i in 1 until input.length) {
        val c = input[i]
        if (c < prevChar) {
            return false
        }
        prevChar = c
    }
    return true
}

val functions1 = arrayOf(
    ::passwordCriteriaLength,
    ::passwordCriteriaDoubleDigits,
    ::passwordCriteriaIncrease
)

val functions2 = arrayOf(
    ::passwordCriteriaLength,
    ::passwordCriteriaMustHaveAtLeastOneOnlyDoubleDigitsAndNoMore,
    ::passwordCriteriaIncrease
)

fun validPassword(input: String): Boolean {
    for (validationFunction in functions1) {
        if (!validationFunction(input)) {
            return false
        }
    }
    return true
}

fun validPassword2(input: String): Boolean {
    for (validationFunction in functions2) {
        if (!validationFunction(input)) {
            return false
        }
    }
    return true
}

fun main() {

    val valid = (256310..732736).count { validPassword(it.toString()) }

    println("Valid in range=$valid")

    val valid2 = (256310..732736).count { validPassword2(it.toString()) }

    println("Valid2 in range=$valid2")
}
