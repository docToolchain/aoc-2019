package com.github.corneil.aoc2019.day3

import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

data class Coord(val x: Int, val y: Int) {
    fun distance(target: Coord): Int {
        return abs(x - target.x) + abs(y - target.y)
    }
}

data class Cell(val coord: Coord, val value: Int, val steps: MutableMap<Int, Int> = mutableMapOf())

fun translateInstruction(instruction: String): Pair<Char, Int> {
    val direction = instruction[0]
    val amount = instruction.substring(1).toInt()
    return Pair(direction, amount)
}

class Grid {
    val cells = mutableMapOf(Coord(0, 0) to Cell(Coord(0, 0), 0))
    private fun addCell(x: Int, y: Int, value: Int, steps: Int) {
        if (!(x == 0 && y == 0)) {
            val key = Coord(x, y)
            val existing = cells[key]
            if (existing != null) {
                if (existing.value != value) {
                    cells[key] = existing.copy(value = value + existing.value)
                }
                val existingSteps = cells[key]!!.steps[value]
                if (existingSteps == null) {
                    cells[key]!!.steps[value] = steps
                }
            } else {
                cells[key] = Cell(key, value, mutableMapOf(value to steps))
            }
        }
    }

    private fun addEntriesX(startX: Int, endX: Int, y: Int, value: Int, steps: Int): Coord {
        var stepsCounted = steps
        if (startX < endX) {
            for (x in startX..endX) {
                addCell(x, y, value, stepsCounted)
                stepsCounted += 1
            }
        } else {

            for (x in startX downTo endX) {
                addCell(x, y, value, stepsCounted)
                stepsCounted += 1
            }
        }
        return Coord(endX, y)
    }

    private fun addEntriesY(startY: Int, endY: Int, x: Int, value: Int, steps: Int): Coord {
        var stepsCounted = steps
        if (startY < endY) {
            for (y in startY..endY) {
                addCell(x, y, value, stepsCounted)
                stepsCounted += 1
            }
        } else {
            for (y in startY downTo endY) {
                addCell(x, y, value, stepsCounted)
                stepsCounted += 1
            }
        }
        return Coord(x, endY)
    }

    fun updateGrid(trace: List<String>, line: Int) {
        var position = Coord(0, 0)
        var stepsCounted = 0
        trace.forEach { instruction ->
            val direction = translateInstruction(instruction)
            position = when (direction.first) {
                'R'  -> addEntriesX(position.x, position.x + direction.second, position.y, line, stepsCounted)
                'L'  -> addEntriesX(position.x, position.x - direction.second, position.y, line, stepsCounted)
                'U'  -> addEntriesY(position.y, position.y + direction.second, position.x, line, stepsCounted)
                'D'  -> addEntriesY(position.y, position.y - direction.second, position.x, line, stepsCounted)
                else -> error("Unknown instruction $instruction")
            }
            stepsCounted += direction.second
        }
    }

    fun findNearest(coord: Coord, minValue: Int): Cell {
        val closest = cells.values.filter { cell ->
            cell.value > minValue
        }.minBy { cell ->
            coord.distance(cell.coord)
        }
        requireNotNull(closest) { "Could not find any cells!!!" }
        return closest
    }

    fun findLowestStepsIntersection(minValue: Int): Int {
        val best = cells.values.filter { cell ->
            cell.value > minValue
        }.minBy { cell ->
            cell.steps.values.sum()
        }
        requireNotNull(best) { "Expected a minimum value" }
        return best.steps.values.sum()
    }

    fun findClosestManhattenDistance(coord: Coord, minValue: Int): Int {
        return coord.distance(findNearest(coord, minValue).coord)
    }

    fun printGrid(): List<String> {
        val minX = min(cells.values.map { it.coord.x }.min() ?: 0, 0) - 1
        val maxX = max(cells.values.map { it.coord.x }.max() ?: 0, 0) + 1
        val lines = cells.values.groupBy { it.coord.y }.toSortedMap()
        return lines.map { entry ->
            val lineOut = StringBuilder()
            val lineCells = entry.value.map { it.coord.x to it }.toMap()
            for (x in minX..maxX) {
                val cellX = lineCells[x]
                if (cellX != null) {
                    lineOut.append(cellX.value.toString())
                } else {
                    lineOut.append('.')
                }
            }
            lineOut.toString()
        }.reversed()
    }
}

fun stringToInstructions(input: String): List<String> {
    return input.split(',').map { it.trim() }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val lines = BufferedReader(FileReader(File(fileName))).readLines().mapNotNull {
        val line = it.trim()
        if (line.isNotEmpty()) line else null
    }
    val grid = Grid()
    lines.forEachIndexed { index, line ->
        grid.updateGrid(stringToInstructions(line), index + 1)
    }
    val start = Coord(0, 0)
    val distance = grid.findClosestManhattenDistance(start, lines.size)
    println("Distance=$distance")
    val best = grid.findLowestStepsIntersection(lines.size)
    // then
    println("Best=$best")
}
