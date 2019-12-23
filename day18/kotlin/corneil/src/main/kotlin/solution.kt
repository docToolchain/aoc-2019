package com.github.corneil.aoc2019.day18

import org.jgrapht.Graph
import org.jgrapht.alg.shortestpath.DijkstraShortestPath
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths
import org.jgrapht.graph.DefaultUndirectedWeightedGraph
import org.jgrapht.graph.DefaultWeightedEdge
import org.jgrapht.traverse.BreadthFirstIterator
import java.io.File
import kotlin.math.abs

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

data class Key(val name: String, val pos: Coord)

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

}

data class Map(val cells: MutableMap<Coord, Cell>) {
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

class World(val map: Map) {
    val graph: Graph<Cell, DefaultWeightedEdge>
    private val dsp: DijkstraShortestPath<Cell, DefaultWeightedEdge>

    init {
        graph = DefaultUndirectedWeightedGraph<Cell, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
        redoEdges()
        dsp = DijkstraShortestPath(graph)
    }

    private fun redoEdges() {
        graph.removeAllEdges(graph.edgeSet())
        map.cells.values.filter { !it.isWall() }.forEach {
            graph.addVertex(it)
        }
        map.cells.values.filter { !it.isWall() }.forEach { centre ->
            val loc = centre.pos
            loc.surrounds().forEach { neighbour ->
                val cell = map.cells[neighbour]
                if (cell != null && !cell.isWall()) {
                    graph.addEdge(centre, cell)
                }
            }
        }
    }

    fun keys(): Set<Cell> = map.cells.values.filter { it.isKey() }.toSet()
    fun doors(): Set<Cell> = map.cells.values.filter { it.isDoor() }.toSet()
    fun key(c: Char): Cell? {
        return map.find(c.toLowerCase())
    }

    fun door(c: Char): Cell? {
        return map.find(c.toUpperCase())
    }

    fun entrance(): Cell = map.cells.values.find { it.c == '@' } ?: error("Expected entrance")
    fun findPath(start: Cell, end: Cell): List<Cell> {
        val path = dsp.getPath(start, end)
        return path.vertexList
    }

    fun isValid(route: List<Cell>, keys: Set<Cell>): List<Cell> {
        val result = mutableListOf<Cell>()
        val unlockedDoors = keys.mapNotNull { door(it.c) }.toSet().toMutableSet()
        val collectedKeys = keys.toMutableSet()
        var start = route.first()
        for (i in 1..route.lastIndex) {
            val end = route[i] ?: error("Expected entry $i in $route")
            val path = findPath(start, end)
            if (path.isNotEmpty()) {
                var allowed = path.filter {
                    when {
                        it.isKey()  -> {
                            collectedKeys.add(it)
                            val door = door(it.c)
                            if (door != null) {
                                unlockedDoors.add(door)
                            }
                            true
                        }
                        it.isDoor() -> {
                            unlockedDoors.contains(it)
                        }
                        else        -> true
                    }
                }.size == path.size
                if (!allowed) {
                    return emptyList()
                }
                result.addAll(path)
            }
        }
        return result
    }

    fun canAccess(target: Cell, source: Cell, keys: Set<Cell>): List<Cell> {
        val unlockedDoors = keys.mapNotNull { door(it.c) }.toMutableSet()
        val collectedKeys = keys.toMutableSet()
        val path = findPath(source, target)
        val result = if (path.isNotEmpty()) {
            var allowed = path.filter {
                when {
                    it == target -> true
                    it == source -> true
                    it.isKey()   -> {
                        collectedKeys.add(it)
                        val door = door(it.c)
                        if (door != null) {
                            unlockedDoors.add(door)
                        }
                        true
                    }
                    it.isDoor()  -> {
                        unlockedDoors.contains(it)
                    }
                    else         -> true
                }
            }.size == path.size
            if (!allowed) emptyList() else path
        } else emptyList()
        println("Testing ${source.c} -> ${target.c} = $result")
        return result
    }

    fun path(target: Cell, source: Cell): Int {
        val path = findPath(source, target)
        return if (path.isNotEmpty()) {
            path.size - 1
        } else {
            -1
        }
    }
}

typealias Solution = Pair<Int, List<Cell>>

fun addSolution(solution: Solution, solutions: MutableMap<Int, MutableSet<Solution>>) {
    val current = solutions[solution.first] ?: mutableSetOf<Solution>()
    current.add(solution)
    solutions[solution.first] = current
}

fun findPathRecursive(
    graph: Graph<Cell, DefaultWeightedEdge>,
    visited: List<Cell>,
    solutions: MutableMap<Int, MutableSet<Solution>>,
    world: World
): List<Cell> {
    val jsp = JohnsonShortestPaths(graph)
    // println("From ${visited.map { it.c }.joinToString(", ")}")
    val start = visited.last()
    val visitedKeys = visited.filter { it.isKey() }.toSet()
    val keys = world.keys() - visitedKeys
    val validVisits = mutableSetOf<Cell>()
    val shortestPaths = jsp.getPaths(start)
    shortestPaths.graph.vertexSet().filter { it.isKey() }.filter {
        !visitedKeys.contains(it)
    }.forEach { cell ->
        val testRoute = visited + cell
        if (world.isValid(testRoute, visited.toSet()).isNotEmpty()) {
            validVisits.add(cell)
        }
    }
    if (validVisits.isEmpty()) {
        keys.forEach { cell ->
            val path = jsp.getPath(start, cell)
            if (path != null && path.length > 0) {
                val testRoute = visited + cell
                if (world.isValid(testRoute, visited.toSet()).isNotEmpty()) {
                    validVisits.add(cell)
                }
            } else {
                println("Expected to find a path from $start to $cell in \n\t${graph.edgeSet().joinToString("\n\t")}")
                require(graph.vertexSet().contains(start)) { "Expected to find $start in graph ${graph.edgeSet()}" }
                require(graph.vertexSet().contains(cell)) { "Expected to find $cell in graph ${graph.edgeSet()}" }
            }
        }
        println("Checking Visits:${validVisits.map { it.c }.joinToString(", ")}")
    }
    // TODO eliminate combinations where keys are not on the shortest path. If a visit to a includes b then b don't need to be tested?
    if (validVisits.size == 1) {
        val totalVisit = visited + validVisits.first()
        val route = totalVisit.filter { it.isKey() }
        val keys = route.toSet()
        val allKeys = world.keys()
        if (allKeys == keys) {
            val path = world.isValid(route, visited.toSet())
            return if (path.isNotEmpty()) {
                totalVisit
            } else {
                emptyList()
            }
        }
        return findPathRecursive(graph, totalVisit, solutions, world)
    }
    val visitLengths = mutableListOf<Solution>()
    validVisits.forEach {
        val path = findPathRecursive(graph, visited + it, solutions, world)
        if (path.isNotEmpty()) {
            val distance = calculateSteps(path.filter { it.isKey() }, visited.first(), world)
            val solution = Solution(distance, path)
            visitLengths.add(solution)
            addSolution(solution, solutions)
        }
    }
    visitLengths.sortBy { it.first }
    if (visitLengths.isNotEmpty()) {
        return visitLengths.first().second
    }
    return emptyList()
}

fun findPath(world: World, start: Cell): Pair<List<Cell>, Int> {
    val jsp = JohnsonShortestPaths(world.graph)
    val iterator = BreadthFirstIterator<Cell, DefaultWeightedEdge>(world.graph, start)
    val edges = mutableMapOf<Pair<Cell, Cell>, Int>()
    var prevCell = start
    while (iterator.hasNext()) {
        val cell = iterator.next()
        if (cell != prevCell && (cell.isKey() || cell.isEntrance() || cell.isDoor())) {
            val path = jsp.getPath(prevCell, cell)
            if (path != null && path.length > 0) {
                val nodes = path.vertexList.filter { it.isKey() || it.isDoor() || it.isEntrance() }.filter {
                    it != prevCell && it != cell
                }
                if (nodes.isEmpty()) {
                    val key = Pair(prevCell, cell)
                    edges[key] = path.length
                } else {
                    var first = prevCell
                    for (node in nodes) {
                        val local = jsp.getPath(first, node)
                        val key = Pair(first, node)
                        edges[key] = local.length
                        first = node
                    }
                    val last = jsp.getPath(first, cell)
                    val key = Pair(first, cell)
                    edges[key] = last.length
                }
                prevCell = cell
            } else {
                println("Building KeysAndDoors:Expected to find a path from $prevCell to $cell in \n\t${world.graph.edgeSet().joinToString("\n\t")}")
                require(world.graph.vertexSet().contains(prevCell)) { "Expected to find $prevCell in graph ${world.graph.edgeSet()}" }
                require(world.graph.vertexSet().contains(cell)) { "Expected to find $cell in graph ${world.graph.edgeSet()}" }
            }
        }
    }

    val keysDoors = DefaultUndirectedWeightedGraph<Cell, DefaultWeightedEdge>(DefaultWeightedEdge::class.java)
    keysDoors.addVertex(world.entrance())
    world.keys().forEach { keysDoors.addVertex(it) }
    world.doors().forEach { keysDoors.addVertex(it) }
    edges.forEach {
        println("Edge: ${it.key.first.c} -> ${it.key.second.c} = ${it.value}")
        if (keysDoors.getEdge(it.key.first, it.key.second) == null) {
            keysDoors.addEdge(it.key.first, it.key.second)
        }
        val edge = keysDoors.getEdge(it.key.first, it.key.second)
        keysDoors.setEdgeWeight(edge, it.value.toDouble())
    }
    val solutions = mutableMapOf<Int, MutableSet<Solution>>()
    val path = findPathRecursive(keysDoors, listOf(start), solutions, world).filter { it != start }
    if (path.isEmpty()) {
        require(solutions.isEmpty()) { "Didn't expect solutions:$solutions" }
        return Pair(emptyList(), 0)
    }
    val winning = solutions.keys.min()
    val winningSolutions = solutions[winning] ?: mutableSetOf()
    if (winningSolutions.size > 1) {
        println("Multiple Solutions:${winningSolutions.size}")
        winningSolutions.forEach {
            println("\t${it.second.filter { it.isKey() }.map { it.c }.joinToString(", ")}")
        }
    }
    val distance = calculateSteps(path, start, world)
    return Pair(path, distance)
}

fun findKeys(map: Map): Pair<Int, List<Cell>> {
    val world = World(map)
    var start = world.entrance()
    println("World:${world.entrance()}")
    println("Keys:${world.keys().map { it.c }.joinToString(", ")}")
    println("Doors:${world.doors().map { it.c }.joinToString(", ")}")
    val result = findPath(world, start)
    println("Route=${result.first.map { it.c }.joinToString(", ")}")
    println("Steps=${result.second}")
    return Pair(result.second, result.first)
}

fun calculateSteps(visit: List<Cell>, start: Cell, world: World): Int {
    val jsp = JohnsonShortestPaths(world.graph)
    val path = jsp.getPath(start, visit.first())
    // println("From ${start.c} -> ${visit.first().c} = ${path.length}")
    var distance = path.length
    for (i in 0 until visit.lastIndex) {
        val steps = jsp.getPath(visit[i], visit[i + 1])
        // println("From ${visit[i].c} -> ${visit[i + 1].c} = ${steps.length}")
        distance += steps.length
    }
    return distance
}

fun readMap(input: String): Map {
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
    return Map(cells)
}

fun main() {
    val input = File("input.txt").readText()
    val map = readMap(input)
    val distance = findKeys(map)
    println(distance)

}
