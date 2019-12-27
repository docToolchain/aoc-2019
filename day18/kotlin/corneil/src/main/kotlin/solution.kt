package com.github.corneil.aoc2019.day18

import com.github.corneil.aoc2019.common.filterUnique
import com.github.corneil.aoc2019.common.permutations
import org.jgrapht.Graph
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths
import org.jgrapht.graph.DefaultEdge
import org.jgrapht.graph.DefaultUndirectedGraph
import java.io.File
import java.lang.ref.WeakReference
import kotlin.math.abs
import kotlin.math.max
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

data class Step(val distance: Int, val node: Cell) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Step

        if (node != other.node) return false

        return true
    }

    override fun hashCode(): Int {
        return node.hashCode()
    }
}

data class Cell(val c: Char, val pos: Coord) : Comparable<Cell> {
    override fun compareTo(other: Cell): Int {
        var result = c.compareTo(other.c)
        if (result == 0) {
            result = pos.compareTo(other.pos)
        }
        return result
    }

    fun isDoor() = c.isLetter() && c.isUpperCase()
    fun isKey() = c.isLetter() && c.isLowerCase()
    fun isEntrance() = c == '@'
    fun isWall() = c == '#'
    override fun toString(): String {
        return "Cell($c:${pos.x},${pos.y})"
    }

    fun isFloor(): Boolean {
        return c == '.'
    }

}

data class Grid(val cells: MutableMap<Coord, Cell>) {
    private val lookup: MutableMap<Char, Cell> = mutableMapOf()

    init {
        cells.values.filter { it.c != ' ' }.forEach { c ->
            lookup[c.c] = c
        }
    }

    fun maxY(): Int = cells.keys.maxBy { it.y }?.y ?: 0
    fun maxX(): Int = cells.keys.maxBy { it.x }?.x ?: 0
    fun remove(loc: Coord) {
        val cell = cells[loc]
        if (cell != null) {
            lookup.remove(cell.c)
        }
        cells[loc] = Cell(' ', loc)
    }

    fun find(c: Char): Cell? = lookup[c]
}

class World(val grid: Grid) {
    val graph: Graph<Cell, DefaultEdge>
    private val pathCache = mutableMapOf<Pair<Cell, Cell>, WeakReference<List<Step>>>()
    private val shortestPath: ShortestPathAlgorithm<Cell, DefaultEdge>
    private val keys: Set<Cell> = grid.cells.values.filter { it.isKey() }.toSet()
    private val doors: Set<Cell> = grid.cells.values.filter { it.isDoor() }.toSet()
    private val entrance: Cell = grid.cells.values.find { it.isEntrance() }!!

    init {
        graph = DefaultUndirectedGraph<Cell, DefaultEdge>(DefaultEdge::class.java)
        redoEdges()
        shortestPath = JohnsonShortestPaths(graph)
    }

    private fun redoEdges() {
        graph.removeAllEdges(graph.edgeSet())
        grid.cells.values.filter { !it.isWall() }.forEach {
            graph.addVertex(it)
        }
        grid.cells.values.filter { !it.isWall() }.forEach { centre ->
            val loc = centre.pos
            loc.surrounds().forEach { neighbour ->
                val cell = grid.cells[neighbour]
                if (cell != null && !cell.isWall()) {
                    graph.addEdge(centre, cell)
                }
            }
        }
    }

    fun keys(): Set<Cell> = keys
    fun doors(): Set<Cell> = grid.cells.values.filter { it.isDoor() }.toSet()
    fun key(c: Char): Cell? {
        return grid.find(c.toLowerCase())
    }

    fun door(c: Char): Cell? {
        return grid.find(c.toUpperCase())
    }

    fun entrance(): Cell = entrance
    fun findPath(start: Cell, end: Cell): List<Step> {
        val key = Pair(start, end)
        var item = pathCache[key]
        if (item != null && item.get() != null) {
            return item.get()!!
        }
        val shortest = shortestPath.getPath(start, end)
        var prev = start
        val path = shortest.vertexList.map { cell ->
            val stepPath = shortestPath.getPath(prev, cell)
            prev = cell
            Step(stepPath.length, cell)
        }.toList()
        pathCache[key] = WeakReference(path)
        return path
    }

    fun isValid(route: List<Cell>, keys: Set<Cell>): List<Step> {
        val validPath = findValid(route, keys)
        return if (validPath.first) {
            validPath.second
        } else {
            emptyList()
        }
    }

    fun findValid(route: List<Cell>, keys: Set<Cell>): Pair<Boolean, List<Step>> {
        val result = mutableListOf<Step>()
        val unlockedDoors = keys.mapNotNull { door(it.c) }.toSet().toMutableSet()
        val collectedKeys = keys.toMutableSet()
        var start = route.first()
        for (i in 1 until route.size) {
            val end = route[i] ?: error("Expected entry $i in $route")
            val path = findPath(start, end)
            if (path.isNotEmpty()) {
                var allowed = path.filter {
                    when {
                        it.node.isKey()  -> {
                            collectedKeys.add(it.node)
                            val door = door(it.node.c)
                            if (door != null) {
                                unlockedDoors.add(door)
                            }
                            true
                        }
                        it.node.isDoor() -> {
                            unlockedDoors.contains(it.node)
                        }
                        else             -> true
                    }
                }.size == path.size
                if (!allowed) {
                    return Pair(false, result)
                }
                if (result.isNotEmpty()) {
                    if (result.last().node == path.first().node) {
                        result.addAll(path.subList(1, path.size))
                    } else {
                        result.addAll(path)
                    }
                } else {
                    result.addAll(path)
                }
            }
            start = end
        }
        return Pair(true, result)
    }

}

var solutions = 0
fun addSolution(progress: Boolean) {
    solutions += 1
    if (solutions % 100 == 0) {
        print("Solutions:$solutions\r")
    }
}

fun routeToString(route: List<Cell>): String {
    return route.filter { !it.isFloor() }.map { it.c }.joinToString(",")
}

fun visitToRoute(visit: List<Step>): List<Cell> {
    val visited = mutableSetOf<Cell>()
    return visit.map { it.node }.filter { it.isKey() }.filter {
        if (visited.contains(it)) {
            false
        } else {
            visited.add(it)
            true
        }
    }
}

fun startsWith(list: List<Cell>, compare: List<Cell>): Boolean {
    return if (list.size >= compare.size) {
        compare.filterIndexed { index, cell -> cell == list[index] }.size == compare.size
    } else false
}

fun findPathsToNextRemaining(
    start: Cell,
    remainingKeys: Set<Cell>,
    visitedKeys: Set<Cell>,
    world: World
): List<List<Step>> {
    val potentialPaths = remainingKeys.map {
        world.findValid(listOf(start, it), visitedKeys)
    }.filter { it.first }.map { it.second to visitToRoute(it.second) - (visitedKeys) }
        .sortedBy { it.first.sumBy { it.distance } }
    val keyPaths = if (potentialPaths.isNotEmpty()) {
        val shortest = potentialPaths.minBy { it.second.size }?.second?.size ?: error("Expected entries")
        potentialPaths.map {
            val last = it.second[shortest - 1]
            it.first.subList(0, it.first.indexOfFirst { it.node == last } + 1)
        }
    } else {
        emptyList()
    }
    return filterUnique(keyPaths)
}

var explored = 0
var maxDepthReached = 0
var bestSolution = Int.MAX_VALUE
var skipped = 0

fun findPathRecursive(
    progress: Boolean,
    graph: Graph<Cell, DefaultEdge>,
    visited: List<Step>,
    world: World,
    depth: Int = 1
): Pair<Int, List<Cell>> {
    if (progress) {
        val visitedStr = visitToRoute(visited).filter { !it.isFloor() }.map { it.c }
        println("findPathRecursive:Depth=$depth,$visitedStr")
    }
    val maxDepth = world.keys().size
    if (depth >= maxDepth) {
        if (progress) {
            println("Reached max depth $maxDepth")
        }
        return Pair(Int.MAX_VALUE, emptyList())
    }
    maxDepthReached = max(maxDepthReached, depth)
    val start = visited.last().node
    val visitedKeys = visited.map { it.node }.filter { it.isKey() }.toSet()
    val remainingKeys = world.keys() - visitedKeys
    if (progress) {
        println("Remaining Keys: ${remainingKeys.map { it.c }}")
    }
    val keyPaths = findPathsToNextRemaining(start, remainingKeys, visitedKeys, world)
    if (progress) {
        if (remainingKeys.size > 1) {
            val combinations = permutations(remainingKeys.size)
            val keysRemain = remainingKeys - keyPaths.flatMap { it.map { it.node } }.filter { it.isKey() }.toSet()
            val remainingCombindations = permutations(keysRemain.size) + keyPaths.size.toBigInteger()
            println("Combinations:$combinations reduced to $remainingCombindations")
        }
        println("Starting Paths:${keyPaths.size}")
        keyPaths.forEach {
            println(routeToString(it.filter { !it.node.isFloor() }.map { it.node }))
        }
    }
    val visitsLengths = mutableListOf<Pair<Int, List<Cell>>>()
    for (keyPath in keyPaths) {
        explored += 1
        val visit = visited + keyPath.subList(1, keyPath.size)
        val route = visitToRoute(visit)
        val visitDistance = visit.sumBy { it.distance }
        if (route.toSet() == world.keys()) {
            visitsLengths.add(Pair(visitDistance, route))
            addSolution(progress)
            continue
        }
        if (bestSolution < visitDistance) {
            skipped += 1
            continue
        }
        val path = findPathRecursive(progress, graph, visit, world, depth + 1)
        if (path.second.isNotEmpty()) {
            visitsLengths.add(path)
            addSolution(progress)
        }
    }
    return if (visitsLengths.isNotEmpty()) {
        visitsLengths.sortBy { it.first }
        val minDistance = visitsLengths.minBy { it.first }?.first ?: error("Expected a minimun")
        if (progress) {
            val minCount = visitsLengths.count { it.first == minDistance }
            println("Solutions:${visitsLengths.size}, Best=$minDistance, Matching=$minCount")
            visitsLengths.filter { it.first == minDistance }.forEach {
                println(routeToString(it.second))
            }
        }
        bestSolution = min(bestSolution, minDistance)
        visitsLengths.first()
    } else {
        if (progress) {
            println("No Solutions")
        }
        Pair(Int.MAX_VALUE, emptyList())
    }
}

fun findPath(progress: Boolean, world: World, start: Cell): Pair<String, Int> {
    val targets = world.keys() + world.doors()

    val keyPaths = targets.map {
        val path = world.findValid(listOf(start, it), emptySet())
        it to path
    }.filter { it.second.first }.map { it.second.second }.toSet().toList().sortedByDescending { it.size }
    val deadEnds = mutableSetOf<String>()
    println("Starting Paths:${keyPaths.size}")
    keyPaths.forEach {
        println(routeToString(it.filter { !it.node.isFloor() }.map { it.node }))
    }
    val visitsLengths = mutableListOf<Pair<Int, List<Cell>>>()

    keyPaths.forEach {
        val path = findPathRecursive(progress, world.graph, it, world)
        if (path.second.isNotEmpty()) {
            visitsLengths.add(path)
        }
        println("Solutions:${solutions}")
    }
    return if (visitsLengths.isNotEmpty()) {
        visitsLengths.sortBy { it.first }
        val best = visitsLengths.first()
        val minDistance = best.first
        val solutions = visitsLengths.count { it.first == minDistance }
        println("Best:$minDistance:Solutions:$solutions")
        visitsLengths.filter { it.first == minDistance }.forEach {
            println(routeToString(it.second))
        }
        Pair(routeToString(best.second), minDistance)
    } else {
        Pair("", -1)
    }
}

fun findKeys(progress: Boolean, grid: Grid): Pair<Int, String> {
    val world = World(grid)

    bestSolution = Int.MAX_VALUE
    explored = 0
    maxDepthReached = 0
    skipped = 0

    var start = world.entrance()
    println("Entrance:${world.entrance()}")
    val combinations = permutations(world.keys().size)
    println("Combinations:$combinations")
    val keys = world.keys()
    println("Keys:${keys.size}:${keys.map { it.c }.joinToString(", ")}")
    val doors = world.doors()
    println("Doors:${doors.size}:${doors.map { it.c }.joinToString(", ")}")
    val result = findPath(progress, world, start)
    println("Max Depth Reached = $maxDepthReached")
    println("Combinations explored:$explored")
    println("Skipped:$skipped")
    println("Route=${result.first}")
    println("Steps=${result.second}")
    return Pair(result.second, result.first)
}

fun readMap(input: String): Grid {
    var loc = Coord(0, 0)
    var cells = mutableMapOf<Coord, Cell>()
    input.forEach { c ->
        if (c == '\n') {
            loc = loc.copy(y = loc.y + 1, x = 0)
        } else {
            cells[loc] = Cell(c, loc)
            loc = loc.copy(x = loc.x + 1)
        }
    }
    return Grid(cells)
}

fun main(args: Array<String>) {
    val input = File("input.txt").readText()
    val map = readMap(input)
    val progress = args.toSet().contains("-p")
    val distance = findKeys(progress, map)
    println("Distance = ${distance.first}")
}
