package com.github.corneil.aoc2019.day15

import com.github.corneil.aoc2019.common.Graph
import com.github.corneil.aoc2019.common.Graph.Edge

import com.github.corneil.aoc2019.day15.DIRECTION.EAST
import com.github.corneil.aoc2019.day15.DIRECTION.NORTH
import com.github.corneil.aoc2019.day15.DIRECTION.SOUTH
import com.github.corneil.aoc2019.day15.DIRECTION.WEST

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
    println("\nRecords=${result.size}")
    println("Edges=${edges.size}")
    val robotLocation = result.first().start
    val graph = Graph(edges, true)
    val path = graph.findPath(robotLocation, oxygenLocation!!)
    println("From ${path.first()} -> ${path.last()}")
    val moves = path.last().second
    println("Moves=$moves")
    require(moves == 250)

    val graph2 = Graph(edges, true)
    graph2.dijkstra(oxygenLocation)
    val distances = spaces.mapNotNull {
        val path = graph2.findPath(it)
        path.lastOrNull()
    }
    val furthest = distances.maxBy { it.second }
    println("Furthest=$furthest")
    val minutes = furthest!!.second
    println("Minutes = $minutes")
    require(minutes == 332)

    AnsiConsole.systemUninstall()
}

