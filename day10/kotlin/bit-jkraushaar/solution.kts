#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File
import kotlin.math.atan2

// tag::asteroids[]
typealias Asteroid = Pair<Int, Int>

fun convertToAsteroidSet(map: List<CharArray>): Set<Asteroid> {
    val asteroidSet = mutableSetOf<Asteroid>()
    for (row in map.withIndex()) {
        for (column in row.value.withIndex()) {
            if (column.value == '#') {
                asteroidSet.add(Asteroid(column.index, row.index))
            }
        }
    }
    return asteroidSet
}
// end::asteroids[]

// tag::lineOfSight[]
typealias LineOfSight = Pair<Asteroid, Asteroid>

fun LineOfSight.asteroids(asteroidSet: Set<Asteroid>): List<Asteroid> {
    val resultList = mutableListOf(first)

    val xDiff = second.first - first.first
    val yDiff = second.second - first.second

    val otherAsteroids = (asteroidSet - first) - second

    if (xDiff == 0) {
        val yRange = if (yDiff > 0) (first.second..second.second) else (second.second..first.second)
        for (asteroid in otherAsteroids) {
            if (asteroid.first == first.first && yRange.contains(asteroid.second)) {
                resultList.add(asteroid)
            }
        }
    } else {
        val gradient = yDiff.toDouble() / xDiff.toDouble()
        val xRange = if (xDiff > 0) (first.first..second.first) else (second.first..first.first)
        for (asteroid in otherAsteroids) {
            val expectedY = ((asteroid.first - first.first) * gradient) + first.second
            if (xRange.contains(asteroid.first) && expectedY == asteroid.second.toDouble()) {
                resultList.add(asteroid)
            }
        }
    }

    resultList.add(second)

    return resultList
}

fun LineOfSight.obstructed(asteroidSet: Set<Asteroid>) = this.asteroids(asteroidSet).size > 2
// end::lineOfSight[]

// tag::firstStar[]
fun firstStar(asteroids: Set<Asteroid>): Asteroid {
    val asteroidLosCount = mutableListOf<Pair<Asteroid, Int>>()
    for (asteroid in asteroids) {
        val otherAsteroids = asteroids - asteroid
        var losCount = 0
        for (otherAsteroid in otherAsteroids) {
            val los = LineOfSight(asteroid, otherAsteroid)
            if (!los.obstructed(asteroids)) {
                losCount++
            }
        }
        asteroidLosCount.add(Pair(asteroid, losCount))
    }

    val bestMatch: Pair<Asteroid, Int>? = asteroidLosCount.maxBy { pair -> pair.second }
    println("${bestMatch?.first} with ${bestMatch?.second}")
    return bestMatch!!.first
}

val asteroids = convertToAsteroidSet(File("./input.txt").readLines().map { it.toCharArray() })
val bestMatch = firstStar(asteroids)
// end::firstStar[]

// tag::secondStar[]
fun secondStar(bestMatch: Asteroid, otherAsteroids: Set<Asteroid>) {
    var clockwiseSortedAsteroids = otherAsteroids.toList().sortedBy {
        val xDiff = it.first - bestMatch.first
        val yDiff = it.second - bestMatch.second
        atan2(xDiff.toDouble(), yDiff.toDouble())
    }.reversed()

    var survivingAsteroids = clockwiseSortedAsteroids.toList()
    var destroyCounter = 0
    while (clockwiseSortedAsteroids.size > 0) {
        for (asteroid in clockwiseSortedAsteroids) {
            if (!LineOfSight(bestMatch, asteroid).obstructed(clockwiseSortedAsteroids.toSet())) {
                destroyCounter++
                survivingAsteroids = survivingAsteroids - asteroid
                println("PENG! ($destroyCounter) $asteroid")
            }
        }
        clockwiseSortedAsteroids = survivingAsteroids.toList()
    }
}

secondStar(bestMatch, asteroids - bestMatch)
// end::secondStar[]
