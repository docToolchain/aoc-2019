package com.github.corneil.aoc2019.day15

import com.github.corneil.aoc2019.day15.DIRECTION.EAST
import com.github.corneil.aoc2019.day15.DIRECTION.NORTH
import com.github.corneil.aoc2019.day15.DIRECTION.SOUTH
import com.github.corneil.aoc2019.day15.DIRECTION.WEST
import com.github.corneil.aoc2019.day15.Graph.Edge
import com.github.corneil.aoc2019.day15.RobotStatus.MOVED
import com.github.corneil.aoc2019.day15.RobotStatus.OXYGEN
import com.github.corneil.aoc2019.day15.RobotStatus.UNKNOWN
import com.github.corneil.aoc2019.day15.RobotStatus.WALL
import com.github.corneil.aoc2019.day15.STRATEGY.ALTERNATE
import com.github.corneil.aoc2019.day15.STRATEGY.LEFT
import com.github.corneil.aoc2019.day15.STRATEGY.RANDOM
import com.github.corneil.aoc2019.day15.STRATEGY.RIGHT
import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import org.fusesource.jansi.Ansi
import org.fusesource.jansi.AnsiConsole
import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.random.Random

data class Coord(val x: Int, val y: Int) : Comparable<Coord> {
    override fun compareTo(other: Coord): Int {
        var result = y.compareTo(other.y)
        if (result == 0) {
            result = x.compareTo(other.x)
        }
        return result
    }

    fun move(direction: DIRECTION): Coord {
        return when (direction) {
            NORTH -> copy(y = y - 1)
            SOUTH -> copy(y = y + 1)
            EAST  -> copy(x = x + 1)
            WEST  -> copy(x = x - 1)
        }
    }
}

enum class DIRECTION(val value: Int) {
    NORTH(1),
    SOUTH(2),
    WEST(3),
    EAST(4);

    fun nextLeft(): DIRECTION = when (this) {
        NORTH -> WEST
        WEST  -> SOUTH
        SOUTH -> EAST
        EAST  -> NORTH
    }

    fun nextRight(): DIRECTION = when (this) {
        NORTH -> EAST
        EAST  -> SOUTH
        SOUTH -> WEST
        WEST  -> NORTH
    }
}

enum class STRATEGY {
    LEFT,
    RIGHT,
    ALTERNATE,
    RANDOM
}

fun direction(direction: DIRECTION): String {
    return when (direction) {
        NORTH -> "^"
        WEST  -> "<"
        SOUTH -> "v"
        EAST  -> ">"
    }
}

fun directionFrom(input: Int): DIRECTION {
    return DIRECTION.values().find { it.value == input } ?: error("Invalid direction $input")
}

enum class RobotStatus(val input: Int) {
    WALL(0),
    MOVED(1),
    OXYGEN(2),
    UNKNOWN(-1)
}

fun robotStatusFrom(input: Int): RobotStatus {
    return RobotStatus.values().find { it.input == input } ?: error("Invalid RobotStatus $input")
}

val random = Random(System.currentTimeMillis())

data class Cell(val pos: Coord, val status: RobotStatus, var visits: Int = 0)

class Robot(
    var location: Coord,
    dir: DIRECTION,
    var prevLocation: Coord = Coord(0, 0),
    var prev: DIRECTION = NORTH,
    var moves: Int = 0,
    var prevChoice: Boolean = false
) {
    var direction: DIRECTION = dir
        set(value) {
            prev = direction
            field = value
        }

    fun move() {
        prevLocation = location
        location = location.move(direction)
        moves += 1
    }

    fun next(strategy: STRATEGY): DIRECTION {
        prev = direction
        return when (strategy) {
            LEFT      -> direction.nextLeft()
            RIGHT     -> direction.nextRight()
            ALTERNATE -> {
                val choice = if (prevChoice) direction.nextRight() else direction.nextLeft()
                prevChoice = !prevChoice
                choice
            }
            RANDOM    -> directionFrom(random.nextInt(4) + 1)
        }
    }
}

data class Grid(
    val cells: MutableMap<Coord, Cell> = mutableMapOf(),
    var maxX: Int = 0,
    var minX: Int = 0,
    var maxY: Int = 0,
    var minY: Int = 0
) {
    fun determineSize() {
        maxX = (cells.keys.maxBy { it.x }?.x ?: 0)
        minX = (cells.keys.minBy { it.x }?.x ?: 0)
        maxY = (cells.keys.maxBy { it.y }?.y ?: 0)
        minY = (cells.keys.minBy { it.y }?.y ?: 0)
    }

    fun printCell(cell: Cell, robot: Robot) {
        when {
            cell.status == UNKNOWN       -> print(" ")
            cell.pos == robot.location &&
                    cell.status == WALL  -> print("%")
            cell.status == WALL          -> print("#")
            cell.pos == robot.location &&
                    cell.status == MOVED -> print(direction(robot.direction))
            cell.status == MOVED         -> print(".")
            cell.status == OXYGEN        -> print("O")
        }
    }

    fun printGrid(robot: Robot) {
        determineSize()
        print(Ansi.ansi().eraseScreen().cursor(1, 1))
        for (y in minY..maxY) {
            for (x in minX..maxX) {
                val location = Coord(x, y)
                val cell = cells[location] ?: Cell(location, UNKNOWN)
                printCell(cell, robot)
            }
            println()
        }
    }

    fun setStatus(pos: Coord, status: RobotStatus): Cell {
        val cell = cells[pos] ?: Cell(pos, status)
        if (status != UNKNOWN) {
            cell.visits += 1
        }
        cells[pos] = cell
        return cell
    }

    fun findPosition(robot: Robot, direction: DIRECTION): Coord {
        return robot.location.move(direction)
    }

    fun goStraightOrFindOpen(robot: Robot): DIRECTION {
        val location = findPosition(robot, robot.direction)
        val cell = cells[location] ?: Cell(location, UNKNOWN)
        if (cell.status == UNKNOWN) {
            return robot.direction
        }
        return findOpenSpace(robot)
    }

    fun findOpenSpace(robot: Robot): DIRECTION {
        var test = robot.direction
        val tried = mutableSetOf<Cell>()
        val valid = mutableSetOf<Pair<DIRECTION, Cell>>()
        do {
            val location = findPosition(robot, test)
            val cell = cells[location] ?: Cell(location, UNKNOWN)
            tried.add(cell)
            if (cell.status == MOVED || cell.status == UNKNOWN) {
                valid.add(Pair(test, cell))
            }
            test = test.nextRight()
        } while (test != robot.direction)
        if (valid.isNotEmpty()) {
            return findMinVisit(valid)
        }
        error("Cannot move from ${robot.location} tried:$tried")
    }

    fun findMinVisit(valid: Collection<Pair<DIRECTION, Cell>>): DIRECTION {
        val minVisit = valid.minBy { it.second.visits }!!
        val minVisits = valid.filter { it.second.visits == minVisit.second.visits }
        if (minVisits.size > 1) {
            return minVisits[random.nextInt(minVisits.size)].first
        }
        return minVisit.first
    }

    fun modified(maxX: Int, minX: Int, maxY: Int, minY: Int): Boolean {
        return this.maxX != maxX || this.minX != minX || this.maxY != maxY || this.minY != minY
    }

    fun isComplete(): Boolean {
        determineSize()
        val width = maxX - minX
        val height = maxY - minY
        val hasOxygen = cells.values.any { it.status == OXYGEN }
        return width * height == cells.size && hasOxygen
    }
}

fun determinResponse(grid: Grid, robot: Robot, status: RobotStatus, strategy: STRATEGY): Int {
    grid.setStatus(grid.findPosition(robot, robot.direction), status)
    if (status != WALL) {
        robot.move()
    }
    return when (status) {
        OXYGEN, MOVED -> grid.goStraightOrFindOpen(robot).value
        WALL          -> grid.findOpenSpace(robot).value
        else          -> error("Unexpected response:$status")
    }
}

fun printGrid(grid: Grid, robot: Robot, cell: Cell?, printAll: Boolean) {
    val maxX = (grid.cells.keys.maxBy { it.x }?.x ?: 0)
    val minX = (grid.cells.keys.minBy { it.x }?.x ?: 0)
    val maxY = (grid.cells.keys.maxBy { it.y }?.y ?: 0)
    val minY = (grid.cells.keys.minBy { it.y }?.y ?: 0)
    if (printAll || grid.modified(maxX, minX, maxY, minY)) {
        grid.printGrid(robot)
    } else {
        if (cell != null) {
            print(Ansi.ansi().cursor(cell.pos.y - minY + 1, cell.pos.x - minX + 1))
            grid.printCell(cell, robot)
        }
    }
    val totalVisits = grid.cells.values.sumBy { it.visits }
    print(Ansi.ansi().cursor(maxY - minY + 2, 1).render("Total Visits:$totalVisits, Cells = ${grid.cells.size}"))
}

data class OperationRecord(val start: Coord, val direction: DIRECTION, val response: RobotStatus, val end: Coord)

fun discoverGrid(
    printOutput: Boolean,
    code: List<Long>,
    initial: DIRECTION,
    strategy: STRATEGY,
    grid: Grid
): List<OperationRecord> {
    print(Ansi.ansi().eraseScreen())
    val robot = Robot(Coord(0, 0), initial)
    val program = Program(code).createProgram()
    val record = mutableListOf<OperationRecord>()
    do {
        if (printOutput) {
            printGrid(grid, robot, grid.cells[robot.prevLocation], false)
            printGrid(grid, robot, grid.cells[robot.location], false)
        }
        val start = robot.location
        val initialDirection = robot.direction
        val output = program.executeUntilOutput(listOf(initialDirection.value.toLong()))
        val response = robotStatusFrom(output.first().toInt())
        val direction = determinResponse(grid, robot, response, strategy)
        robot.direction = directionFrom(direction)
        record.add(OperationRecord(start, initialDirection, response, robot.location))
        if (printOutput) {
            printGrid(grid, robot, grid.cells[robot.prevLocation.move(robot.prev)], false)
        }
    } while (!grid.isComplete())
    printGrid(grid, robot, grid.cells[robot.location], true)
    return record
}

fun main(args: Array<String>) {
    var printOutput = false
    AnsiConsole.systemInstall()
    if (args.contains("-p")) {
        printOutput = true
    }
    val code = readProgram(File("input.txt"))
    var grid = Grid()
    val edges = mutableListOf<Edge<Coord>>()

    val result = discoverGrid(printOutput, code, NORTH, LEFT, grid)
    result.forEach { record ->
        edges.add(Edge(record.start, record.end, 1))
    }
    var oxygenLocation = result.find { it.response == OXYGEN }?.end
    requireNotNull(oxygenLocation) { "Could not find Oxygen" }

    val spaces = result.filter { it.response == MOVED || it.response == OXYGEN }.map { it.end }
    println("Records=${result.size}")
    println("\nEdges=${edges.size}")
    with(Graph(edges, true)) {
        dijkstra(Coord(0, 0))
        val path = findPath(oxygenLocation!!)
        println("Last=${path.last()}")
        val moves = path.last().second
        println("Moves=$moves")
        require(moves == 250)
    }
    with(Graph(edges, true)) {
        dijkstra(oxygenLocation)
        val distances = spaces.mapNotNull {
            val path = findPath(it)
            path.lastOrNull()
        }
        val furthest = distances.maxBy { it.second }
        println("Furthest=$furthest")
        val minutes = furthest!!.second
        println("Minutes = $minutes")
        require(minutes == 332)
    }
    AnsiConsole.systemUninstall()
}

class Graph<T : Comparable<T>>(
    edges: Collection<Edge<T>>,
    directed: Boolean
) {
    class Vertex<T : Comparable<T>>(
        val key: T,
        var distance: Int = Int.MAX_VALUE,
        var prev: Vertex<T>? = null,
        val neighbours: MutableMap<Vertex<T>, Int> = mutableMapOf()
    ) : Comparable<Vertex<T>> {
        override fun compareTo(other: Vertex<T>): Int {
            var result = distance.compareTo(other.distance)
            if (result == 0) {
                result = key.compareTo(other.key)
            }
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Vertex<T>

            if (key != other.key) return false
            if (distance != other.distance) return false

            return true
        }

        override fun hashCode(): Int {
            var result = key.hashCode()
            result = 31 * result + distance
            return result
        }

        fun findPath(path: MutableList<Pair<T, Int>>) {
            if (this == prev) {
                path.add(Pair(key, distance))
            } else if (prev != null) {
                prev!!.findPath(path)
                path.add(Pair(key, distance))
            }
        }
    }

    data class Edge<T>(val c1: T, val c2: T, val distance: Int)

    private val graph = HashMap<T, Vertex<T>>(edges.size)

    init {
        for (e in edges) {
            if (!graph.containsKey(e.c1)) {
                graph[e.c1] = Vertex(e.c1)
            }
            if (!graph.containsKey(e.c2)) {
                graph[e.c2] = Vertex(e.c2)
            }
        }

        // another pass to set neighbouring vertices
        for (e in edges) {
            graph[e.c1]!!.neighbours[graph[e.c2]!!] = e.distance
            if (!directed) {
                graph[e.c2]!!.neighbours[graph[e.c1]!!] = e.distance
            }
        }
    }

    fun dijkstra(start: T) {
        if (!graph.containsKey(start)) {
            error("Cannot find start $start")
        }
        val source = graph[start]
        val q = TreeSet<Vertex<T>>()

        // set-up vertices
        for (v in graph.values) {
            v.prev = if (v == source) source else null
            v.distance = if (v == source) 0 else Int.MAX_VALUE
            q.add(v)
        }

        dijkstra(q)
    }

    private fun dijkstra(q: TreeSet<Vertex<T>>) {
        while (!q.isEmpty()) {

            val u = q.pollFirst()
            if (u.distance == Int.MAX_VALUE) break
            for (a in u.neighbours) {
                val v = a.key

                val alternateDist = u.distance + a.value
                if (alternateDist < v.distance) {
                    q.remove(v)
                    v.distance = alternateDist
                    v.prev = u
                    q.add(v)
                }
            }
        }
    }

    fun findPath(end: T): List<Pair<T, Int>> {
        if (!graph.containsKey(end)) {
            error("Cannot find $end")
        }
        val path = mutableListOf<Pair<T, Int>>()
        graph[end]!!.findPath(path)
        return path.toList()
    }
}
