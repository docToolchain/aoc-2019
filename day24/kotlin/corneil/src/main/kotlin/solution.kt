package com.github.corneil.aoc2019.day24

import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

data class Coord(val x: Int, val y: Int, val level: Int = 0) {
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
    fun top() = copy(y = y - 1)
    fun bottom() = copy(y = y + 1)
    fun surrounds() = listOf(left(), right(), top(), bottom())
    fun pos(width: Int) = (x + 1) + (y * (width))
    fun inGrid(width: Int, height: Int) = x >= 0 && x < width && y >= 0 && y < height
}

open class Grid(val width: Int = 5, val height: Int = 5) {
    val levels = mutableMapOf<Int, BitSet>()
    open fun levels(): List<Int> {
        return levels.keys.sorted()
    }

    open fun setBug(coord: Coord, live: Boolean) {
        if (coord.inGrid(width, height)) {
            val pos = coord.pos(width)
            if (pos >= 0) {
                val cells = levels[coord.level]
                if (cells == null) {
                    val newCells = BitSet()
                    newCells[pos] = live
                    levels[coord.level] = newCells
                } else {
                    cells[pos] = live
                }
            }
        }
    }

    open fun getBug(coord: Coord): Boolean {
        return if (coord.inGrid(width, height)) {
            val pos = coord.pos(width)
            val cells = levels[coord.level]
            if (cells != null && pos >= 0) {
                cells[pos]
            } else false
        } else false
    }

    open fun surrounds(loc: Coord): List<Coord> {
        return loc.surrounds().filter { it.inGrid(width, height) }
    }

    fun totalBugs(): Int {
        return levels().map { countBugs(it) }.sum()
    }

    fun countBugs(level: Int): Int {
        val cells = levels[level] ?: BitSet()
        return (0 until cells.size()).map { cells[it] }.filter { it }.count()
    }

    fun stepLife() {
        val allLevels = levels()
        val bugCount = mutableMapOf<Coord, Int>()
        for (level in allLevels) {
            getBugCount(level, bugCount)
        }
        for (level in allLevels) {
            changeLife(level, bugCount)
        }
    }

    open fun ignore(coord: Coord): Boolean = false
    fun changeLife(level: Int, bugCount: MutableMap<Coord, Int>) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                val pos = Coord(x, y, level)
                if (!ignore(pos)) {
                    val bugs = bugCount[pos] ?: 0
                    if (getBug(pos)) {
                        if (bugs != 1) {
                            setBug(pos, false)
                        }
                    } else {
                        if (bugs >= 1 && bugs <= 2) {
                            setBug(pos, true)
                        }
                    }
                }
            }
        }
    }

    fun getBugCount(level: Int, bugCount: MutableMap<Coord, Int>) {
        for (y in 0 until height) {
            for (x in 0 until width) {
                val loc = Coord(x, y, level)
                if (!ignore(loc)) {
                    bugCount[loc] = surrounds(loc).filter { getBug(it) }.count()
                }
            }
        }
    }

    open fun getSymbol(loc: Coord): Char {
        return if (getBug(loc)) '#' else '.'
    }

    fun printToString(level: Int = 0): String {
        val sw = StringWriter()
        sw.use {
            PrintWriter(sw).use { pw ->
                for (y in 0 until height) {
                    for (x in 0 until width) {
                        val loc = Coord(x, y, level)
                        val cell = getSymbol(loc)
                        pw.print(cell)
                    }
                    pw.println()
                }
            }
        }
        return sw.toString()
    }

    fun bioDiversity(level: Int = 0): Long {
        var bit = 0
        var bd = 0L
        val cells = levels[level] ?: BitSet()
        do {
            val index = cells.nextSetBit(bit)
            if (index >= 0) {
                bd += 2.toBigInteger().pow(index - 1).toLong()
                bit = index + 1
            } else {
                break;
            }
        } while (true)
        return bd
    }

    fun bioDiversity(coord: Coord): Long {
        val pos = coord.pos(width)
        return 2.toBigInteger().pow(pos - 1).toLong()
    }
}

class RecursiveGrid : Grid() {
    override fun ignore(coord: Coord): Boolean {
        return coord.x == width / 2 && coord.y == height / 2
    }

    override fun surrounds(loc: Coord): List<Coord> {
        val result = mutableListOf<Coord>()
        val cells = loc.surrounds()
        val halfWidth = width / 2
        val halfHeight = height / 2
        cells.forEach { cell ->
            when {
                cell.x < 0                                  -> {
                    result.add(Coord(x = halfWidth - 1, y = halfHeight, level = cell.level - 1))
                }
                cell.x >= width                             -> {
                    result.add(Coord(x = halfWidth + 1, y = halfHeight, level = cell.level - 1))
                }
                cell.y < 0                                  -> {
                    result.add(Coord(x = halfWidth, y = halfHeight - 1, level = cell.level - 1))
                }
                cell.y >= height                            -> {
                    result.add(Coord(x = halfWidth, y = halfHeight + 1, level = cell.level - 1))
                }
                cell.x == halfHeight && cell.y == halfWidth -> {
                    when {
                        loc.left() == cell   -> (0 until height).forEach {
                            result.add(Coord(width - 1, it, cell.level + 1))
                        }
                        loc.right() == cell  -> (0 until height).forEach {
                            result.add(Coord(0, it, cell.level + 1))
                        }
                        loc.top() == cell    -> (0 until width).forEach {
                            result.add(Coord(it, height - 1, cell.level + 1))
                        }
                        loc.bottom() == cell -> (0 until width).forEach {
                            result.add(Coord(it, 0, cell.level + 1))
                        }
                        else                 -> error("Expected $cell to be around $loc")
                    }
                }
                else                                        -> result.add(cell)
            }
        }
        return result.toList()
    }

    override fun setBug(coord: Coord, live: Boolean) {
        require(coord.inGrid(width, height)) { "setBug invoked with location outside grid $coord" }
        if (!ignore(coord)) {
            super.setBug(coord, live)
        }
    }

    override fun getBug(coord: Coord): Boolean {
        require(coord.inGrid(width, height)) { "getBug invoked with location outside grid $coord" }
        return super.getBug(coord)
    }

    override fun getSymbol(loc: Coord): Char {
        return when {
            loc.x == width / 2 && loc.y == width / 2 -> '?'
            else                                     -> super.getSymbol(loc)
        }
    }

    override fun levels(): List<Int> {
        val allLevels = super.levels().toMutableList()
        val minLevel = allLevels.min()!!
        val maxLevel = allLevels.max()!!
        if (countBugs(minLevel) > 0) {
            allLevels.add(minLevel - 1)
        }
        if (countBugs(maxLevel) > 0) {
            allLevels.add(maxLevel + 1)
        }
        return allLevels.toList().sorted()
    }
}

fun readGrid(input: String, grid: Grid): Grid {
    val cells = mutableMapOf<Coord, Char>()
    var x = 0
    var y = 0
    input.forEach { c ->
        when (c) {
            '\n' -> {
                y += 1
                x = 0
            }
            '.'  -> {
                x += 1
            }
            '#'  -> {
                val loc = Coord(x, y)
                grid.setBug(loc, true)
                x += 1
            }

        }
    }
    return grid
}

fun readGrid(input: String) = readGrid(input, Grid())
fun readRecursiveGrid(input: String) = readGrid(input, RecursiveGrid())

fun main() {
    val input = File("input.txt").readText()
    val grid = readGrid(input)
    val bioSet = mutableSetOf<Long>()
    var lastBD = 0L
    do {
        grid.stepLife()
        val bd = grid.bioDiversity()
        lastBD = bd
        if (bioSet.contains(bd)) {
            println(grid.printToString())
            break
        } else {
            bioSet.add(bd)
        }
    } while (true)
    println("BD = $lastBD")
    require(lastBD == 28717468L)
    val recursiveGrid = readRecursiveGrid(input)
    repeat(200) {
        recursiveGrid.stepLife()
    }
    val totalBugs = recursiveGrid.totalBugs()
    println("Total = $totalBugs")
}

