package com.github.corneil.aoc2019.day18

import com.github.corneil.aoc2019.common.Graph
import com.github.corneil.aoc2019.common.Graph.Edge
import com.github.corneil.aoc2019.common.permute
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

    fun canAccess(target: Cell, source: Cell, keys: Set<Cell>): Int {
        val unlockedDoors = keys.map { it.c.toUpperCase() }.toSet()
        val key = Pair(target, source)
        val path = pathCache[key] ?: graph.findPath(source, target)
        if (!pathCache.containsKey(key)) {
            pathCache[key] = path
        }
        if (path.isNotEmpty()) {
            val blocked =
                path.subList(1, path.lastIndex).any { it.first.isDoor() && !unlockedDoors.contains(it.first.c) }
            if (blocked) {
                return -1
            }
            return path.last().second
        }
        return -1
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
        // Determine which keys we can access from current location
        val accessableKeys =
            (keys - collectedKeys).filter { world.canAccess(it, start, unlocked) >= 0 }
                .sortedBy { it.pos.distance(start.pos) }
        println("Accessible Keys = ${accessableKeys.map { it.c }.joinToString(",")}")
        if (accessableKeys.isNotEmpty()) {
            val sameVisits = mutableListOf<Pair<List<Cell>, Int>>()
            var bestVisitingOrder: Pair<List<Cell>, Int> = Pair(emptyList(), Int.MAX_VALUE)
            val potentialDoors = accessableKeys.mapNotNull { world.door(it.c) }.toSet()
            val accessibleDoors = (collectedKeys + accessableKeys).mapNotNull {
                world.door(it.c)
            }.filter {
                !unlocked.contains(it)
            }.filter {
                world.canAccess(it, start, unlocked + potentialDoors) >= 0
            }.toSet()
            if (accessibleDoors.isNotEmpty()) {
                println("Accessible Doors=" + accessibleDoors.map { it.c }.joinToString(","))
                permuteInvoke((accessableKeys).toList()) { keyVisit ->
                    accessibleDoors.forEach {checkingDoor ->
                        val extraKeys = (keys - collectedKeys - accessableKeys).filter {
                            world.canAccess(it, checkingDoor, unlocked) >= 0
                        }.toSet()
                        if (extraKeys.isNotEmpty()) {
                            println("Extra Keys = ${extraKeys.map { it.c }.joinToString(",")}")
                        }
                        permuteInvoke(extraKeys.toList()) { adding ->
                            val visit = keyVisit + adding
                            if (extraAfterUnlockedDoors(visit, extraKeys, accessibleDoors)) {
                                var distance = world.path(visit[0], start)
                                for (i in 0 until visit.lastIndex) {
                                    val d = world.path(visit[i + 1]!!, visit[i]!!)
                                    require(d >= 0)
                                    distance += d
                                }
                                if (distance < bestVisitingOrder.second) {
                                    println("Visit ${visit.map { it.c }.joinToString(",")}, distance=$distance")
                                    bestVisitingOrder = Pair(visit, distance)
                                    sameVisits.clear()
                                    sameVisits.add(bestVisitingOrder)
                                } else if (distance == bestVisitingOrder.second) {
                                    sameVisits.add(Pair(visit, distance))
                                    println("Same Visit ${visit.map { it.c }.joinToString(",")}, distance=$distance")
                                }
                            }
                        }
                    }
                }
            } else {
                permuteInvoke(accessableKeys) { visit ->
                    var distance = world.path(visit[0], start)
                    for (i in 0 until visit.lastIndex) {
                        val d = world.path(visit[i + 1]!!, visit[i]!!)
                        require(d >= 0)
                        distance += d
                    }

                    if (distance < bestVisitingOrder.second) {
                        println("Visit ${visit.map { it.c }.joinToString(",")}, distance=$distance")
                        bestVisitingOrder = Pair(visit, distance)
                        sameVisits.clear()
                        sameVisits.add(bestVisitingOrder)
                    } else if (distance == bestVisitingOrder.second) {
                        sameVisits.add(Pair(visit, distance))
                        println("Same Visit ${visit.map { it.c }.joinToString(",")}, distance=$distance")
                    }
                }
            }
            if (sameVisits.size > 1 && bestVisitingOrder.first.size > 1) {
                val bestShort = permute(sameVisits.first().first).map { testVisit ->
                    val shortTest = testVisit.subList(0, testVisit.lastIndex)
                    var distance = world.path(shortTest.first(), start)
                    for (i in 0 until shortTest.lastIndex) {
                        distance += world.path(shortTest[i + 1], shortTest[i])
                    }
                    shortTest to distance
                }.sortedBy { it.second }
                bestVisitingOrder = bestShort.first()
            }
            val visit = bestVisitingOrder
            println("Best Visit = ${visit.first.map { it }.joinToString(",")}")
            visit.first.forEach { key ->
                println("Visit=$key")
                collectedKeys.add(key)
                visitingOrder.add(key)
                val door = world.door(key.c)
                if (door != null) {
                    unlocked.add(door)
                    println("Unlock $door")
                }
                val dist = world.path(key, start)
                println("Step = $dist")
                totalDistance += dist
                start = key
            }
            println("Total Distance = $totalDistance")
        } else {
            error("No more Accessible Keys Unlocked=$unlocked, collected keys=$collectedKeys")
        }
    }
    return Pair(totalDistance, visitingOrder)
}

fun extraAfterUnlockedDoors(visit: List<Cell>, extraKeys: Set<Cell>, accessibleDoors: Set<Cell>): Boolean {
    return visit.filterIndexed { index, cell ->
        if (extraKeys.contains(cell) && visit.subList(index, visit.size).any { accessibleDoors.contains(it) }) {
            false
        } else {
            true
        }
    }.size == visit.size
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

fun createEdges(map: Map): List<Edge<Cell>> {
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

fun main() {

}
