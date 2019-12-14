package com.github.corneil.aoc2019.day10

import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN
import kotlin.math.atan2
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

data class Coord(val x: Int, val y: Int)

data class AsteroidMap(val width: Int, val height: Int, val asteroidLocations: List<Coord>) {
    fun contains(point: Coord): Boolean {
        return point.y >= 0 && point.y < height && point.x in 0 until width
    }
}

fun readMap(input: List<String>): AsteroidMap {
    var width = 0
    val result = mutableListOf<Coord>()
    input.forEachIndexed { y, line ->
        line.trim().forEachIndexed { x, c ->
            width = max(width, x + 1)
            if (c == '#') {
                result.add(Coord(x, y))
            }
        }
    }
    return AsteroidMap(width, input.size, result)
}

// I had to look at other solutions to do the 2nd part. Tx Michael Simons
fun direction(start: Coord, end: Coord) = Coord(end.x - start.x, end.y - start.y)

fun distance(pt: Coord): Double = sqrt(pt.x.toDouble().pow(2.0) + pt.y.toDouble().pow(2.0))
typealias Normalized = Pair<BigDecimal, BigDecimal>

fun normalized(pt: Coord): Normalized {
    val dist = distance(pt)
    return Pair(
        BigDecimal(pt.x.toDouble() / dist).setScale(4, HALF_EVEN),
        BigDecimal(pt.y / dist).setScale(4, HALF_EVEN)
    )
}

fun prepareOtherAsteroids(map: AsteroidMap, coord: Coord): Map<Normalized, MutableList<Pair<Coord, Double>>> {
    val result = mutableMapOf<Normalized, MutableList<Pair<Coord, Double>>>()
    map.asteroidLocations.filter { it != coord }.forEach {
        val dir = direction(coord, it)
        val dist = distance(dir)
        val norm = normalized(dir)
        val target = Pair(it, dist)
        if (result.containsKey(norm)) {
            result[norm]!!.add(target)
        } else {
            result[norm] = mutableListOf(target)
        }
    }
    result.forEach { entry ->
        entry.value.sortBy { it.second }
    }
    return result
}

fun findLineOfSightCount(map: AsteroidMap, coord: Coord): Int {
    return prepareOtherAsteroids(map, coord).size
}

fun shootInOrder(map: AsteroidMap, laser: Coord): List<Coord> {
    val list = prepareOtherAsteroids(map, laser)
    val visiting = list.keys.sortedBy { atan2(it.first.toDouble(), it.second.toDouble()) }.reversed()
    val shot = mutableListOf<Coord>()
    while (shot.size < map.asteroidLocations.size - 1) {
        visiting.forEach { direction ->
            val targets = list[direction] ?: mutableListOf()
            if (targets.isNotEmpty()) {
                shot.add(targets.first().first)
                targets.removeAt(0)
            }
        }
    }
    return shot
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val map = readMap(File(fileName).readLines().map { it.trim() })
    val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
    val best = counts.maxBy { it.second }
    println("Best = $best")
    val shot = shootInOrder(map, best!!.first)
    val shot200 = shot[199]
    val answer = shot200.x * 100 + shot200.y
    println("Answer=$answer")
}
