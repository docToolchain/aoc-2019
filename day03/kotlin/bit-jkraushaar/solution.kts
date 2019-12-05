#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File
import java.lang.RuntimeException
import kotlin.math.absoluteValue

// tag::point[]
typealias Point = Pair<Int, Int>
fun Point.manhattanDistance(): Int = this.first.absoluteValue + this.second.absoluteValue
// end::point[]

// tag::line[]
typealias Line = Triple<Point, Point, String>
fun Line.expandPoints(): List<Point> {
    val points = mutableListOf<Point>()

    val (startingPoint, endingPoint, direction) = this
    val (startX, startY) = startingPoint
    val (endX, endY) = endingPoint

    when (direction) {
        "R" -> {
            for (x in startX + 1..endX) {
                points.add(Point(x, startY))
            }
        }
        "L" -> {
            for (x in startX - 1 downTo endX) {
                points.add(Point(x, startY))
            }
        }
        "U" -> {
            for (y in startY + 1..endY) {
                points.add(Point(startX, y))
            }
        }
        "D" -> {
            for (y in startY - 1 downTo endY) {
                points.add(Point(startX, y))
            }
        }
        else -> throw RuntimeException("Unknown direction: $direction")
    }

    return points
}
// end::line[]

// tag::pointConversion[]
fun List<String>.toListOfPoints(): List<Point> {
    val points = mutableListOf<Point>()

    var currentPoint = Point(0, 0)

    for (vector in this) {
        val line = createLine(currentPoint, vector)
        points.addAll(line.expandPoints())
        currentPoint = line.second
    }

    return points
}

fun createLine(startingPoint: Point, vector: String): Line {
    val partition = vector.partition { it.isLetter() }
    val direction = partition.first
    val distance = partition.second.toInt()

    val endingPoint = when (direction) {
        "R" -> Point(startingPoint.first + distance, startingPoint.second)
        "L" -> Point(startingPoint.first - distance, startingPoint.second)
        "U" -> Point(startingPoint.first, startingPoint.second + distance)
        "D" -> Point(startingPoint.first, startingPoint.second - distance)
        else -> throw RuntimeException("Unknown direction: $direction")
    }

    return Line(startingPoint, endingPoint, direction)
}
// end::pointConversion[]

// tag::utilityFunction[]
fun List<Point>.asDistanceMap(): Map<Point, Int> = this.mapIndexed { index, point -> point to index + 1 }.toMap()
// end::utilityFunction[]

// tag::firstStar[]
val wires = File("./input.txt")
    .readLines()
    .map { it.split(",") }
    .map { it.toListOfPoints() }

val intersections = wires[0].intersect(wires[1]) - Point(0, 0)

val lowestManhattanDistance = intersections
    .map { it.manhattanDistance() }
    .min()

println(lowestManhattanDistance)
// end::firstStar[]

// tag::secondStar[]
val wire1DistanceMap = wires[0].asDistanceMap().filterKeys { intersections.contains(it) }
val wire2DistanceMap = wires[1].asDistanceMap().filterKeys { intersections.contains(it) }

val minimumDistance: Int? = wire1DistanceMap.map { it.value + (wire2DistanceMap[it.key] ?: throw RuntimeException()) }.min()

println(minimumDistance ?: -1)
// end::secondStar[]