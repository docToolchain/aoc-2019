package com.github.corneil.aoc2019.day18

import com.github.corneil.aoc2019.common.Graph
import com.github.corneil.aoc2019.common.Graph.Edge
import com.github.corneil.aoc2019.common.permuteInvoke
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
    private val edges: MutableList<Edge<Cell>> = mutableListOf()
    private var graph: Graph<Cell>
    private val pathCache: MutableMap<Pair<Cell, Cell>, List<Pair<Cell, Int>>> = mutableMapOf()

    init {
        edges.addAll(createEdges(map))
        graph = Graph(edges, false)
    }

    private fun redoEdges() {
        edges.clear()
        edges.addAll(createEdges(map))
        graph = Graph(edges, false)
        pathCache.clear()
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
    fun collectKey(key: Cell) {
        map.remove(key.pos)
        val door = map.find(key.c.toUpperCase())
        if (door != null) {
            map.remove(door.pos)
        }
        redoEdges()
    }

    fun collectKeys(keys: Collection<Cell>) {
        keys.forEach { key ->
            map.remove(key.pos)
            val door = map.find(key.c.toUpperCase())
            if (door != null) {
                map.remove(door.pos)
            }
        }
        redoEdges()
    }

    fun isValid(route: List<Cell>, keys: Set<Cell>): List<Pair<Cell, Int>> {
        val result = mutableListOf<Pair<Cell, Int>>()
        val unlockedDoors = keys.mapNotNull { door(it.c) }.toMutableSet()
        val collectedKeys = keys.toMutableSet()
        var start = route.first()
        for (i in 1..route.lastIndex) {
            val end = route[i] ?: error("Expected entry $i in $route")
            val path = graph.findPath(start, end)
            if (path.isNotEmpty()) {
                var allowed = path.all {
                    when {
                        it.first.isKey()  -> {
                            collectedKeys.add(it.first)
                            val door = door(it.first.c)
                            if (door != null) {
                                unlockedDoors.add(door)
                            }
                            true
                        }
                        it.first.isDoor() -> {
                            unlockedDoors.contains(it.first)
                        }
                        else              -> true
                    }
                }
                if (!allowed) {
                    return emptyList()
                }
                if (result.isEmpty()) {
                    result.addAll(path)
                } else {
                    val last = result.last()
                    path.forEach {
                        result.add(it.copy(second = it.second + last.second))
                    }
                }
            }
        }
        return result
    }

    fun canAccess(target: Cell, source: Cell, keys: Set<Cell>): List<Pair<Cell, Int>> {
        val unlockedDoors = keys.mapNotNull { door(it.c) }.toMutableSet()
        val collectedKeys = keys.toMutableSet()
        val path = graph.findPath(source, target)
        if (path.isNotEmpty()) {
            var allowed = path.all {
                when {
                    it.first.isKey()  -> {
                        collectedKeys.add(it.first)
                        val door = door(it.first.c)
                        if (door != null) {
                            unlockedDoors.add(door)
                        }
                        true
                    }
                    it.first.isDoor() -> {
                        unlockedDoors.contains(it.first)
                    }
                    else              -> true
                }
            }
            if (!allowed) {
                return emptyList()
            }
            return path
        }
        return emptyList()
    }

    fun path(target: Cell, source: Cell): Int {
        val key = Pair(target, source)
        val path = pathCache[key] ?: graph.findPath(source, target)
        if (!pathCache.containsKey(key)) {
            pathCache[key] = path
        }
        return if (path.isNotEmpty()) {
            path.last().second
        } else {
            -1
        }
    }
}
typealias KeyPath = List<Pair<Cell, Int>>

fun visitPath(
    visit: List<Cell>,
    collectedKeys: MutableSet<Cell>,
    visitingOrder: MutableList<Cell>,
    world: World,
    unlocked: MutableSet<Cell>,
    start: Cell,
    totalDistance: Int
): Pair<Cell, Int> {
    var start1 = start
    var totalDistance1 = totalDistance
    visit.forEach { key ->
        println("Visit=$key")
        collectedKeys.add(key)
        visitingOrder.add(key)
        val door = world.door(key.c)
        if (door != null) {
            unlocked.add(door)
            println("Unlock $door")
        }
        val dist = world.path(key, start1)
        println("Step = $dist")
        totalDistance1 += dist
        start1 = key
    }
    return Pair(start1, totalDistance1)
}

fun findKeys(map: Map): Pair<Int, List<Cell>> {
    val world = World(map)
    val keys = world.keys()
    val doors = world.doors()
    val unlocked = mutableSetOf<Cell>()
    var start = world.entrance()
    val collectedKeys = mutableSetOf<Cell>()
    var totalDistance = 0
    val visitingOrder = mutableListOf<Cell>()
    while (doors != unlocked || keys != collectedKeys) {
        println("Start=$start")
        val candidates = mutableMapOf<List<Cell>, Int>()
        var tests = 0
        permuteInvoke((keys - collectedKeys).toList()) { visit ->
            if (visit.isNotEmpty()) {
                val path = world.isValid(visit, unlocked.toSet().toMutableSet())
                tests += 1
                if (path.isNotEmpty()) {
                    val visit = extractVisit(path, start)
                    val current = candidates[visit]
                    if (current == null) {
                        val distance = calculateSteps(visit, start, world)
                        candidates[visit] = distance
                        println("Tests=$tests, Candidates=${candidates.size}\r")
                    }
                }
                if (tests % 1000 == 0) {
                    println("Tests=$tests, Candidates=${candidates.size}\r")
                }
            }
        }
        val sorted = candidates.entries.map { it.key to it.value }.sortedBy { it.second }
        println("\nAll Candidates:${sorted.size}")
        sorted.forEach {
            println(it)
        }
        val allGoodCanditates = sorted.filter { it.second == sorted.first().second }
        if (allGoodCanditates.isNotEmpty()) {
            println("Best Candidates:${allGoodCanditates.size}/${sorted.size}")
            allGoodCanditates.forEach {
                println(it)
            }
        }
        return Pair(sorted.first().second, sorted.first().first)
    }
    return Pair(totalDistance, visitingOrder)
}

fun extractVisit(path: KeyPath, start: Cell): List<Cell> {
    val visited = mutableSetOf<Cell>()
    val result = mutableListOf<Cell>()
    path.filter { it.first.isKey() }.filter { it.first != start }.forEach {
        if (!visited.contains(it.first)) {
            visited.add(it.first)
            result.add(it.first)
        }
    }
    return result
}

fun calculateSteps(visit: List<Cell>, start: Cell, world: World): Int {
    var distance = world.path(visit.first(), start)
    for (i in 0 until visit.lastIndex) {
        distance += world.path(visit[i + 1], visit[i])
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

fun createManyEdges(map: Map): List<Edge<Cell>> {
    val maxY = map.maxY()
    val maxX = map.maxX()
    val edges = mutableListOf<Edge<Cell>>()
    var prev: Cell? = null
    var prevLoc = Coord(0, 0)
    for (y in 1 until maxY) {
        for (x in 1 until maxX) {
            val loc = Coord(x, y)
            val centre = map.cells[loc]
            if (centre != null && !centre.isWall()) {
                loc.surrounds().forEach { neighbour ->
                    val cell = map.cells[neighbour]
                    if (cell != null && !cell.isWall()) {
                        edges.add(Edge(centre, cell, cell.pos.distance(centre.pos)))
                    }
                }
            }
        }
    }
    return edges
}

fun createEdges(map: Map): List<Edge<Cell>> {
    val edges = createManyEdges(map).toMutableList()
    val result = mutableListOf<Edge<Cell>>()
    val keys = map.cells.values.filter { it.isKey() }.toSet()
    val doors = map.cells.values.filter { it.isDoor() }.toSet()
    val entrance = map.cells.values.find { it.isEntrance() } ?: error("Expected an entrance")
    val combinations = mutableSetOf<Set<Cell>>()
    val graph = Graph(edges, false)
    doors.forEach { door ->
        val path = graph.findPath(entrance, door)
        if (path.isNotEmpty()) {
            result.addAll(makeShorter(path, combinations))
        }
    }
    keys.forEach { key ->
        val path = graph.findPath(entrance, key)
        if (path.isNotEmpty()) {
            result.addAll(makeShorter(path, combinations))
        }
    }
    keys.forEach { key ->
        val neighbours = graph.findNeighbours(key, 2) { cell ->
            cell.isKey() || cell.isDoor()
        }.map {
            it.first
        }.filter { !combinations.contains(setOf(key, it)) }
        if (neighbours.isNotEmpty()) {
            neighbours.forEach { end ->
                val path = graph.findPath(key, end)
                if (path.isNotEmpty()) {
                    result.addAll(makeShorter(path, combinations))
                }
            }
        }
    }
    return result
}

fun makeShorter(path: List<Pair<Cell, Int>>, combinations: MutableSet<Set<Cell>>): List<Edge<Cell>> {
    val edges = mutableListOf<Edge<Cell>>()
    val filtered = path.filter { it.first.isDoor() || it.first.isKey() || it.first.isEntrance() }
    for (i in 0..filtered.lastIndex - 1) {
        val c1 = path[i]
        val c2 = path[i + 1]
        val combo = setOf(c1.first, c2.first)
        if (!combinations.contains(combo)) {
            combinations.add(combo)
            edges.add(Edge(c1.first, c2.first, c2.second - c1.second))
        }
    }
    return edges
}

fun main() {

}
