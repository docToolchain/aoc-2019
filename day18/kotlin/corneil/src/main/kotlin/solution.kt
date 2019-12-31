package com.github.corneil.aoc2019.day18

import com.github.corneil.aoc2019.common.permutations
import java.io.File
import java.util.*

fun BitSet.copy(extra: BitSet? = null): BitSet {
    val result = BitSet(this.size())
    result.or(this)
    if (extra != null) {
        result.or(extra)
    }
    return result
}

data class Coord(val x: Int, val y: Int) : Comparable<Coord> {
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
    fun top() = copy(y = y - 1)
    fun bottom() = copy(y = y + 1)
    fun surrounds() = listOf(left(), right(), top(), bottom())
    override fun compareTo(other: Coord): Int {
        var result = x.compareTo(other.x)
        if (result == 0) {
            result = y.compareTo(other.y)
        }
        return result
    }

    override fun toString(): String {
        return "Coord($x,$y)"
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
    fun isEntrance() = c == '@' || c.isDigit()
    fun isWall() = c == '#'

    fun toBit(): Int {
        return when {
            isKey()  -> c - 'a'
            isDoor() -> c - 'A'
            else     -> error("Didn't expected to support toBit for $c")
        }
    }

    fun toBitSet(): BitSet {
        val result = BitSet()
        if (isKey() || isDoor()) {
            result.set(toBit())
        }
        return result
    }

    fun isFloor(): Boolean {
        return c == '.'
    }

    override fun toString(): String {
        return "Cell($c:${pos.x},${pos.y})"
    }
}

data class Route(val node: Cell, val distance: Int, val doors: BitSet = BitSet(26)) {
    override fun toString(): String {
        return "Route(node=$node, distance=$distance, doors=$doors)"
    }

    fun makeCopy(node: Cell, distance: Int, doors: BitSet? = null): Route {
        return Route(node, distance, doors ?: this.doors.copy())
    }
}

data class Step(val node: Cell, val distance: Int, val visits: BitSet = BitSet(26)) {
    override fun toString(): String {
        return "Step(${node.c}, $distance, $visits)"
    }
}

data class MultiStep(val nodes: Set<Cell>, val distance: Int, val visits: BitSet = BitSet(26)) {
    override fun toString(): String {
        return "MultiStep(${nodes.map { it.c }.joinToString(",")}, $distance, $visits)"
    }
}

data class Visit(val distance: Int, val doors: BitSet) {
    override fun toString(): String {
        return "Visit($distance, $doors)"
    }
}

data class Grid(val cells: MutableMap<Coord, Cell>) {

    fun printToString(): String {
        val output = StringBuilder()
        val maxX = cells.keys.maxBy { it.x }?.x ?: error("Expected cells")
        val maxY = cells.keys.maxBy { it.y }?.y ?: error("Expected cells")
        for (y in 0..maxY) {
            for (x in 0..maxX) {
                output.append(cells[Coord(x, y)]?.c ?: ' ')
            }
            output.append('\n')
        }
        return output.toString()
    }
}

class World(val grid: Grid) {
    private val keys: Set<Cell> = grid.cells.values.filter { it.isKey() }.toSet()
    private val doors: Set<Cell> = grid.cells.values.filter { it.isDoor() }.toSet()
    private val entrances: Set<Cell> = grid.cells.values.filter { it.isEntrance() }.toSet()
    val route = mutableMapOf<Cell, MutableMap<Cell, Visit>>()

    init {
        createRoute()
    }

    private fun createRoute() {
        val nodes = keys() + entrances()
        nodes.forEach { node ->
            val queue = ArrayDeque(listOf(Route(node, 0)))
            val visited = mutableSetOf(node.pos)
            while (queue.isNotEmpty()) {
                val current = queue.pop()
                current.node.pos.surrounds().mapNotNull {
                    grid.cells[it]
                }.filterNot {
                    it.isWall()
                }.filterNot {
                    visited.contains(it.pos)
                }.forEach { next ->
                    visited.add(next.pos)
                    when {
                        next.isDoor()                       -> {
                            val doors = current.doors.copy(next.toBitSet())
                            queue.add(current.makeCopy(next, current.distance + 1, doors))
                        }
                        next.isKey()                        -> {
                            val routeMap = route[node] ?: mutableMapOf()
                            if (routeMap.isEmpty()) {
                                route[node] = routeMap
                            }
                            routeMap[next] = Visit(current.distance + 1, current.doors)
                            queue.add(current.makeCopy(next, current.distance + 1))
                        }
                        next.isEntrance() || next.isFloor() -> {
                            queue.add(current.makeCopy(next, current.distance + 1))
                        }
                        else                                -> error("Unexpected $next")
                    }
                }
            }
        }
    }

    fun keys(): Set<Cell> = keys
    fun doors(): Set<Cell> = doors
    fun entrances(): Set<Cell> = entrances

    fun entrance(): Cell = entrances.first()
}

fun findPath(progress: Boolean, world: World, start: Cell): Int {
    val visited = mutableMapOf<Pair<Cell, BitSet>, Int>()
    val allVisits = BitSet(world.keys().size)
    var visits = 0
    world.keys().forEach { allVisits.or(it.toBitSet()) }
    println("All Visits:$allVisits")
    val combinations = permutations(world.keys().size)
    println("Combinations:$combinations")
    println("Start:$start")
    if (progress) {
        println("Routes:")
        world.route.forEach { route ->
            println("Route:${route.key}")
            route.value.forEach { entry ->
                print("\t")
                println(entry)
            }
        }
    }
    val comparator = compareByDescending<Step> { it.visits.cardinality() }.thenBy { it.distance }
    val queue = PriorityQueue<Step>(world.keys().size * world.doors().size, comparator)
    queue.add(Step(start, 0))
    var best = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val step = queue.poll()
        if (step.distance >= best) {
            continue
        }
        if (progress) println("Checking $step")
        world.route[step.node].orEmpty().asSequence().filterNot { entry ->
            // only those we haven't visited yet
            val key = entry.key
            if (key.isKey()) {
                step.visits[key.toBit()]
            } else {
                false
            }.also {
                if (progress) println("FilterNot#1:$it = $step -> $entry")
            }
        }.filter { entry ->
            // if door for which we have a key
            if (entry.key.isKey()) {
                val doors = entry.value.doors.copy()
                doors.andNot(step.visits)
                if (progress) println("Checking Door ${entry.value.doors} for ${step.visits}")
                doors.cardinality() == 0
            } else {
                true
            }.also { if (progress) println("Doors#2:$it = $step -> $entry") }
        }.map { entry ->
            // Create a new Step
            val visits = step.visits.copy(entry.key.toBitSet())
            Step(entry.key, entry.value.distance + step.distance, visits)
                .also { if (progress) println("Next Step#3:$entry -> $it") }
        }.filter { bestStep ->
            // Allow where total distance is better than best
            val result = bestStep.distance < best
            result.also { if (progress) println("Better#4:$it = $step -> $bestStep") }
        }.filter { bestStep ->
            // where visited is better
            val key = Pair(bestStep.node, bestStep.visits)
            val result = bestStep.distance < visited[key] ?: Int.MAX_VALUE
            result.also { if (progress) println("Filter#5:$it = $step -> $bestStep") }
        }.forEach {
            visits += 1
            val key = Pair(it.node, it.visits)
            if (progress) println("Step=$step, Node=$it, Key=$key")
            visited[key] = it.distance // record best visit
            if (it.visits == allVisits) {
                best = minOf(best, it.distance)
                if (progress) println("Best=$it, Queue:$queue")
                queue.removeIf { step -> step.distance >= best }
            } else {
                queue.offer(it)
            }
            if (progress) println("Queue:$queue")
        }
    }
    println("Visits=$visits")
    return best
}

data class MultiRoute(val from: Cell, val route: Cell, val visit: Visit)

fun findPathMultipleEntrances(progress: Boolean, world: World, start: Set<Cell>): Int {
    val visited = mutableMapOf<Pair<Set<Cell>, BitSet>, Int>()
    val allVisits = BitSet(world.keys().size)
    var visits = 0
    world.keys().forEach { allVisits.or(it.toBitSet()) }
    println("All Visits:$allVisits")
    val combinations = permutations(world.keys().size)
    println("Combinations:$combinations")
    println("Start:$start")
    if (progress) {
        println("Routes:")
        world.route.forEach { route ->
            println("Route:${route.key}")
            route.value.forEach { entry ->
                print("\t")
                println(entry)
            }
        }
    }
    val comparator = compareByDescending<MultiStep> { it.visits.cardinality() }.thenBy { it.distance }
    val queue = PriorityQueue<MultiStep>(world.keys().size * world.doors().size, comparator)
    queue.add(MultiStep(start, 0))
    var best = Int.MAX_VALUE
    while (queue.isNotEmpty()) {
        val step = queue.poll()
        if (step.distance >= best) {
            continue
        }
        if (progress) {
            println("Checking $step")
        }

        step.nodes.flatMap { node ->
            world.route[node].orEmpty().map { MultiRoute(node, it.key, it.value) }
        }.asSequence().filterNot { entry ->
            // only those we haven't visited yet
            val key = entry.route
            val result = if (key.isKey()) step.visits[key.toBit()] else false
            result.also { if (progress) println("FilterNot#1:$it = $step -> $entry") }
        }.filter { entry ->
            // if door for which we have a key
            if (entry.route.isKey()) {
                val doors = entry.visit.doors.copy()
                doors.andNot(step.visits)
                if (progress) println("Checking Door ${entry.visit.doors} for ${step.visits}")
                doors.cardinality() == 0
            } else {
                true
            }.also { if (progress) println("Doors#2:$it = $step -> $entry") }
        }.map { route ->
            // Create a new Step
            val visits = step.visits.copy(route.route.toBitSet())
            MultiStep(step.nodes - route.from + route.route, route.visit.distance + step.distance, visits).also {
                if (progress) println("Next Step#3:$route -> $it")
            }
        }.filter { bestStep ->
            // Allow where total distance is better than best
            val result = bestStep.distance < best
            result.also { if (progress) println("Better#4:$it = $step -> $bestStep") }
        }.filter { bestStep ->
            // where visited is better
            val key = Pair(bestStep.nodes, bestStep.visits)
            val result = bestStep.distance < visited[key] ?: Int.MAX_VALUE
            result.also { if (progress) println("Filter#5:$it = $step -> $bestStep") }
        }.forEach {
            visits += 1
            val key = Pair(it.nodes, it.visits)
            if (progress) println("Step=$step, Node=$it, Key=$key")

            visited[key] = it.distance // record best visit
            if (it.visits == allVisits) {
                best = minOf(best, it.distance)
                if (progress) {
                    println("Best=$it")
                    println("Queue:$queue")
                }
                queue.removeIf { step -> step.distance >= best }
            } else {
                queue.offer(it)
            }
            if (progress) {
                println("Queue:$queue")
            }
        }
    }
    println("Visits=$visits")
    return best
}

fun findKeys(progress: Boolean, grid: Grid): Int {
    val world = World(grid)
    println("Grid:${grid.cells.size}")
    var start = world.entrance()
    println("Entrance:${world.entrance()}")
    val combinations = permutations(world.keys().size)
    println("Combinations:$combinations")
    val keys = world.keys()
    println("Keys:${keys.size}:${keys.map { it.c }.joinToString(", ")}")
    val doors = world.doors()
    println("Doors:${doors.size}:${doors.map { it.c }.joinToString(", ")}")
    println("------------------------------------")
    val result = findPath(progress, world, start)
    println("=====================")
    println("Steps=${result}")
    return result
}

fun findKeysMultipleEntrances(progress: Boolean, grid: Grid): Int {
    val world = World(grid)
    println("Grid:${grid.cells.size}")
    var start = world.entrances()
    println("Entrances:$start")
    val combinations = permutations(world.keys().size)
    println("Combinations:$combinations")
    val keys = world.keys()
    println("Keys:${keys.size}:${keys.map { it.c }.joinToString(", ")}")
    val doors = world.doors()
    println("Doors:${doors.size}:${doors.map { it.c }.joinToString(", ")}")
    println("------------------------------------")
    val result = findPathMultipleEntrances(progress, world, start)
    println("=====================")
    println("Steps=${result}")
    return result
}

fun readGrid(input: String): Grid {
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

fun modifyBots(grid: Grid): Grid {
    val cells = grid.cells.toMap().toMutableMap()
    val entrances = grid.cells.values.filter { it.isEntrance() }.toSet()
    require(entrances.size == 1)
    val entrance = entrances.first()
    cells[entrance.pos] = entrance.copy(c = '#')
    entrance.pos.surrounds().forEach {
        cells[it] = Cell('#', it)
    }
    val replacements = mutableListOf<Cell>()
    replacements.add(entrance.copy(c = '1', pos = Coord(entrance.pos.x - 1, entrance.pos.y - 1)))
    replacements.add(entrance.copy(c = '2', pos = Coord(entrance.pos.x + 1, entrance.pos.y - 1)))
    replacements.add(entrance.copy(c = '3', pos = Coord(entrance.pos.x - 1, entrance.pos.y + 1)))
    replacements.add(entrance.copy(c = '4', pos = Coord(entrance.pos.x + 1, entrance.pos.y + 1)))
    replacements.forEach {
        cells[it.pos] = it
    }
    return Grid(cells)
}

fun main(args: Array<String>) {
    val progress = args.toSet().contains("-p")
    val input = File("input.txt").readText()
    val grid = readGrid(input)
    println(grid.printToString())
    val distance = findKeys(progress, grid)
    println("Distance = $distance")
    require(distance == 5068) // ensure refactoring is still working
    val grid2 = modifyBots(grid)
    println(grid2.printToString())
    val distance2 = findKeysMultipleEntrances(progress, grid2)
    println("Distance Multiple = $distance2")
    require(distance2 == 1966)
}


