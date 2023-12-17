package com.github.corneil.aoc2019.day20

import com.github.corneil.aoc2019.common.Graph
import com.github.corneil.aoc2019.common.Graph.Edge
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.math.abs
import kotlin.math.min

data class Coord(val x: Int, val y: Int) : Comparable<Coord> {
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
    fun top() = copy(y = y - 1)
    fun bottom() = copy(y = y + 1)
    fun surrounds() = listOf(left(), right(), top(), bottom())
    fun distance(target: Coord): Int = abs(target.x - x) + abs(target.y - y)
    override fun compareTo(other: Coord): Int {
        var result = x.compareTo(other.x)
        if (result == 0) {
            result = y.compareTo(other.y)
        }
        return result
    }
}

data class Cell(
    val c: Char,
    val pos: Coord,
    val level: Int = 0,
    val portal: Boolean = false,
    val name: String = c.toString(),
    val outer: Boolean = false
) : Comparable<Cell> {

    fun isFloor() = c == '.'
    fun isWall() = c == '#'
    override fun toString(): String {
        return "Cell($name:${pos.x},${pos.y},level=$level)"
    }

    override fun compareTo(other: Cell): Int {
        var result = level.compareTo(other.level)
        if (result == 0) {
            result = pos.compareTo(other.pos)
        }
        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        other as Cell
        if (level != other.level) return false
        if (pos != other.pos) return false


        return true
    }

    override fun hashCode(): Int {
        var result = pos.hashCode()
        result = 31 * result + level
        return result
    }

}

data class Grid(val cells: MutableMap<Coord, Cell>) {
    fun findByName() = cells.values.filter { !it.isWall() }.groupBy { it.name }
    fun maxY(): Int = cells.keys.maxBy { it.y }?.y ?: 0
    fun maxX(): Int = cells.keys.maxBy { it.x }?.x ?: 0
    fun portals(): Map<String, List<Cell>> = cells.values.filter { it.portal }.groupBy { it.name }
    fun outerPortals(): Map<String, Cell> = cells.values.filter { it.portal && it.outer }.associateBy { it.name }
    fun innerPortals(): Map<String, Cell> = cells.values.filter { it.portal && !it.outer }.associateBy { it.name }
    fun fromEdge(cell: Cell): Int = min(min(cell.pos.x, maxX() - cell.pos.x), min(cell.pos.y, maxY() - cell.pos.y))
    fun printToString(): String {
        val sw = StringWriter()
        sw.use {
            PrintWriter(sw).use { pw ->
                val maxY = maxY()
                val maxX = maxX()
                for (y in 0..maxY) {
                    pw.print(String.format("%02d ", y))
                    for (x in 0..maxX) {
                        val cell = cells[Coord(x, y)]?.c ?: ' '
                        pw.print(cell)
                    }
                    pw.println()
                }
            }
        }
        return sw.toString()
    }
}

fun labelPortals(grid: Grid) {
    val portals = mutableMapOf<Coord, Cell>()
    grid.cells.entries.forEach {
        if (it.value.isFloor()) {
            val top = grid.cells[it.key.top()]
            val left = grid.cells[it.key.left()]
            val right = grid.cells[it.key.right()]
            val bottom = grid.cells[it.key.bottom()]
            when {
                top != null && top.c.isLetter()       -> {
                    val next = grid.cells[top.pos.top()] ?: error("Expected letter at ${top.pos.top()}")
                    val name = "${next.c}${top.c}"
                    portals[it.key] = it.value.copy(portal = true, name = name)
                }
                left != null && left.c.isLetter()     -> {
                    val next = grid.cells[left.pos.left()] ?: error("Expected letter at ${left.pos.left()}")
                    val name = "${next.c}${left.c}"
                    portals[it.key] = it.value.copy(portal = true, name = name)
                }
                right != null && right.c.isLetter()   -> {
                    val next = grid.cells[right.pos.right()] ?: error("Expected letter at ${right.pos.right()}")
                    val name = "${right.c}${next.c}"
                    portals[it.key] = it.value.copy(portal = true, name = name)
                }
                bottom != null && bottom.c.isLetter() -> {
                    val next = grid.cells[bottom.pos.bottom()] ?: error("Expected letter at ${bottom.pos.bottom()}")
                    val name = "${bottom.c}${next.c}"
                    portals[it.key] = it.value.copy(portal = true, name = name)
                }
            }
        }
    }

    val nonPortal = mutableSetOf<Cell>()
    portals.entries.groupBy {
        it.value.name
    }.entries.forEach {
        val cells = it.value.map { it.value }
        if (cells.size == 2) {
            val first = cells.first()
            val last = cells.last()
            if (grid.fromEdge(first) < grid.fromEdge(last)) {
                portals[first.pos] = first.copy(outer = true)
            } else {
                portals[last.pos] = last.copy(outer = true)
            }
        } else {
            nonPortal.addAll(cells)
        }
    }
    nonPortal.forEach {
        portals.remove(it.pos)
        grid.cells[it.pos] = it.copy(portal = false)
    }
    grid.cells.putAll(portals)

}

fun readGrid(input: String): Grid {
    var loc = Coord(0, 0)
    var cells = mutableMapOf<Coord, Cell>()
    input.forEach { c ->
        if (c == '\n') {
            loc = loc.copy(y = loc.y + 1, x = 0)
        } else {
            if (!c.isWhitespace()) {
                cells[loc] = Cell(c, loc)
            }
            loc = loc.copy(x = loc.x + 1)
        }
    }
    val map = Grid(cells)
    labelPortals(map)
    return map
}

fun createEdges(map: Grid): List<Edge<Cell>> {
    val edges = mutableListOf<Edge<Cell>>()
    map.cells.values.forEach { centre ->
        if (!centre.isWall()) {
            centre.pos.surrounds().forEach { neighbour ->
                val cell = map.cells[neighbour]
                if (cell != null && !cell.isWall()) {
                    edges.add(Edge(centre, cell, cell.pos.distance(centre.pos)))
                }
            }
        }
    }

    map.portals().forEach {
        if (it.value.size == 2) {
            edges.add(Edge(it.value[0], it.value[1], 1))
        } else if (it.value.size > 2) {
            error("More than 2 Cells $it")
        }
    }
    return edges
}

fun createEdgesRecursive(outer: Grid, inner: Grid): List<Edge<Cell>> {
    val maxY = outer.maxY()
    val maxX = outer.maxX()
    val edges = mutableListOf<Edge<Cell>>()
    outer.cells.values.forEach { centre ->
        if (!centre.isWall()) {
            centre.pos.surrounds().forEach { neighbour ->
                val cell = outer.cells[neighbour]
                if (cell != null && !cell.isWall() && !cell.outer) {
                    edges.add(Edge(centre, cell, cell.pos.distance(centre.pos)))
                }
            }
        }
    }
    val innerPortals = inner.outerPortals()
    outer.innerPortals().forEach {
        val inner = innerPortals[it.key] ?: error("Expected to find ${it.key}")
        edges.add(Edge(it.value, inner, 1))
    }

    return edges
}

fun findRouteRecursive(grid: Grid, start: String, end: String): List<Pair<Cell, Int>> {
    val levels = mutableListOf<Grid>()
    levels.add(grid)
    val edges = mutableListOf<Edge<Cell>>()
    val named = grid.findByName()
    val startCell = named[start] ?: error("Expected portal named:$start")
    require(startCell.size == 1) { "Expected only one named $start" }
    val endCell = named[end] ?: error("Expected portal name:$end")
    require(endCell.size == 1) { "Expected only one name $end" }
    do {
        val outer = levels.last()
        val inner =
            Grid(outer.cells.values.map { it.copy(level = it.level + 1) }.map { it.pos to it }.toMap().toMutableMap())
        levels.add(inner)
        edges.addAll(createEdgesRecursive(outer, inner))
        println("Levels = ${levels.size}, Edges=${edges.size}")
        val graph = Graph(edges, false)
        val path = graph.findPath(startCell.first(), endCell.first())
        if (path.isNotEmpty()) {
            return path
        }
    } while (true)
}

fun findRoute(grid: Grid, start: String, end: String): List<Pair<Cell, Int>> {
    val named = grid.findByName()
    val startCell = named[start] ?: error("Expected portal named:$start")
    require(startCell.size == 1) { "Expected only one named $start" }
    val endCell = named[end] ?: error("Expected portal name:$end")
    require(endCell.size == 1) { "Expected only one name $end" }
    val graph = Graph(createEdges(grid), false)
    val path = graph.findPath(startCell.first(), endCell.first())
    require(path.isNotEmpty()) { "Could not find path from $start to $end" }
    return path
}

fun main() {
    val grid = readGrid(File("input.txt").readText())
    println(grid.printToString())
    var prevDistance = 0
    var prevName = "AA:0"
    val route = findRoute(grid, "AA", "ZZ")
    val steps = route.map { it.first.name }.joinToString(" , ")
    val count = route.last().second
    route.filter { it.first.portal }.forEach {
        val distance = it.second - prevDistance
        prevDistance = it.second
        val name = "${it.first.name}:${it.first.level}"
        println("$prevName -> $name = $distance")
        prevName = name
    }
    println("$prevName -> ZZ:0 = ${count - prevDistance}")

    println("Count=$count")
    require(count == 522)
    println("Recursive Solition")
    val rRoute = findRouteRecursive(grid, "AA", "ZZ")
    val rCount = rRoute.last().second
    prevDistance = 0
    prevName = "AA:0"
    println("Steps:")
    rRoute.filter { it.first.portal }.forEach {
        val distance = it.second - prevDistance
        prevDistance = it.second
        val name = "${it.first.name}:${it.first.level}"
        println("$prevName -> $name = $distance")
        prevName = name
    }
    println("$prevName -> ZZ:0 = ${rCount - prevDistance}")
    println("Count = $rCount")
    require(rCount == 6300)
}
