package com.github.corneil.aoc2019.day19

import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

data class Coord(val x: Int, val y: Int)

class Grid(val cells: MutableMap<Coord, Char> = mutableMapOf()) {
    fun printToString(): String {
        val sw = StringWriter()
        sw.use {
            PrintWriter(sw).use { pw ->
                val maxY = cells.keys.maxBy { it.y }?.y ?: 0
                val maxX = cells.keys.maxBy { it.x }?.x ?: 0
                for (y in 0..maxY) {
                    pw.print(String.format("%02d ", y))
                    for (x in 0..maxX) {
                        val cell = cells[Coord(x, y)] ?: ' '
                        pw.print(cell)
                    }
                    pw.println()
                }
            }
        }
        return sw.toString()
    }

    fun tractorActiveLine(y: Int): Pair<Coord, Coord> {
        val line = cells.entries.filter { it.key.y == y }.filter { it.value == '#' }.map { it.key }
        if (line.isEmpty()) {
            return Pair(Coord(0, 0), Coord(0, 0))
        }
        val start = line.minBy { it.x } ?: error("Expected values for $y")
        val end = line.maxBy { it.x } ?: error("Expected values for $y")
        return Pair(start, end)
    }
}

fun readGrid(input: String): Grid {
    val grid = Grid()
    var x = 0
    var y = 0
    input.forEach { c ->
        when (c) {
            '\n'     -> {
                if (grid.cells.isNotEmpty()) {
                    y += 1
                }
                x = 0
            }
            '.', '#' -> {
                grid.cells[Coord(x, y)] = c
                x += 1
            }
        }
    }
    return grid
}

fun scanGrid(code: List<Long>, width: Int, height: Int): Grid {
    val grid = Grid()
    scanGrid(code, grid, 0, width - 1, 0, height - 1)
    return grid
}

fun scanGrid(code: List<Long>, grid: Grid, left: Int, right: Int, top: Int, bottom: Int) {
    for (x in left..right) {
        for (y in top..bottom) {
            scanCoord(code, x, y, grid)
        }
    }
}

fun scanCoord(code: List<Long>, x: Int, y: Int, grid: Grid): Char {
    val program = Program(code).createProgram()
    val output = program.executeUntilOutput(listOf(x.toLong(), y.toLong()))
    require(output.size == 1)
    val out = output.first()
    val cell = when (out) {
        0L   -> '.'
        1L   -> '#'
        else -> ('0' + out.toInt())
    }
    grid.cells[Coord(x, y)] = cell
    return cell
}

fun scanStanta(code: List<Long>, size: Int): Long {
    val grid = scanGrid(code, size + 2, size + 2)
    var top = size + 1
    do {
        print("Scan=${top}\r")
        scanLine(2, code, top, grid)
        val result = checkSolution(grid, size, top)
        if (result != 0L) {
            return result
        }
        top += 1
    } while (true)
}

fun checkSolution(grid: Grid, size: Int, start: Int = size + 1): Long {
    val exit = Pair(Coord(0, 0), Coord(0, 0))
    var maxY = grid.cells.keys.maxBy { it.y }?.y ?: error("Expected values")
    for (top in (start)..maxY) {
        val last = grid.tractorActiveLine(top)
        if (last == exit) {
            error("No solution")
        }
        val first = grid.tractorActiveLine(top - size + 1)
        if (last.first.x == (first.second.x - size) + 1) {
            println("\nFirst = $first, Last = $last")
            return (last.first.x.toLong() * 10000L) + first.first.y.toLong()
        }
    }
    return 0
}

fun scanLine(left: Int, code: List<Long>, top: Int, grid: Grid) {
    var x = left - 2
    do {
        val c = scanCoord(code, x, top, grid)
        x += 1
    } while (c == '.')
    do {
        val c = scanCoord(code, x, top, grid)
        x += 1
    } while (c == '#')
}

fun main() {
    val code = readProgram(File("input.txt"))

    val grid = scanGrid(code, 50, 50)
    println(grid.printToString())
    val affected = grid.cells.values.count { it == '#' }
    println("Affected = $affected")
    require(affected == 126)
    val loc = scanStanta(code, 100)
    println("Answer = $loc")
    require(loc == 11351625L)
}

