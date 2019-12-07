#!/usr/bin/env -S kotlinc-jvm -script

// tag::firstStarFilters[]
fun CharArray.shouldHaveAtLeastTwoAdjacentSameDigits() = this.toList().windowed(2).any { it[0] == it[1]}
fun CharArray.shouldNeverDecreaseFromLeftToRight() = this.toList().windowed(2).all { it[0] <= it[1] }
// end::firstStarFilters[]

// tag::firstStar[]
val input = (245318..765747).asSequence()

val firstResult = input.map { it.toString().toCharArray() }
    .filter { it.shouldHaveAtLeastTwoAdjacentSameDigits() }
    .filter { it.shouldNeverDecreaseFromLeftToRight() }
    .count()

println(firstResult)
// end::firstStar[]

// tag::secondStarFilters[]
fun CharArray.shouldHaveTwoLonelyAdjacentSameDigits(): Boolean {
    val listOfTwoDigits = this.toList().withIndex().zipWithNext()
    val listOfPairs = listOfTwoDigits.filter { it.first.value == it.second.value }
    val listOfLonelyPairs = listOfPairs.filter {
        val leftNeighborDifferent = if (it.first.index > 0) this[it.first.index - 1] != this[it.first.index] else true
        val rightNeighborDifferent = if (it.second.index < 5) this[it.second.index + 1] != this[it.second.index] else true
        leftNeighborDifferent && rightNeighborDifferent
    }
    return listOfLonelyPairs.isNotEmpty()
}
// end::secondStarFilters[]

// tag::secondStar[]
val secondResult = input.map { it.toString().toCharArray() }
    .filter { it.shouldHaveTwoLonelyAdjacentSameDigits() }
    .filter { it.shouldNeverDecreaseFromLeftToRight() }
    .count()

println(secondResult)
// end::secondStar[]