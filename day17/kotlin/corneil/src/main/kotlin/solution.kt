package com.github.corneil.aoc2019.day17

import com.github.corneil.aoc2019.common.Combinations
import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.ProgramState
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.math.abs

data class Robot(val pos: Coord, val direction: Char)

data class Coord(val x: Int, val y: Int) {
    fun left() = copy(x = x - 1)
    fun right() = copy(x = x + 1)
    fun top() = copy(y = y - 1)
    fun bottom() = copy(y = y + 1)
    fun surrounds() = listOf(left(), right(), top(), bottom())
    fun north(distance: Int) = copy(y = y - distance)
    fun south(distance: Int) = copy(y = y + distance)
    fun west(distance: Int) = copy(x = x - distance)
    fun east(distance: Int) = copy(x = x + distance)
    fun distance(target: Coord): Int = abs(target.x - x) + abs(target.y - y)
}

data class Movement(val movement: Char, val distance: Int) {
    fun output() = if (movement.toInt() != 0) "$movement,$distance" else "$distance"
}

data class CleaningProgram(val mainRoutine: List<Char>, val subroutines: Map<Char, List<Movement>>) {
    init {
        require(mainRoutine.toSet() == subroutines.keys)
    }

    fun printToString(): String {
        val sw = StringWriter()
        sw.use {
            PrintWriter(it).use { pw ->
                val main = mainString()
                pw.println("Main Routine=${main}: ${createIntCodeInput(main)}")
                names().forEach { name ->
                    val functionString = subRoutine(name)
                    val functionInput = createIntCodeInput(functionString)
                    pw.println("Function $name: $functionString: $functionInput")
                }
            }
        }
        return sw.toString()
    }

    fun names() = subroutines.keys.toList().sorted()
    fun mainString(): String = mainRoutine.joinToString(",")
    fun subRoutine(name: Char): String =
        (subroutines[name] ?: error("Expected $name in $subroutines")).joinToString(",") { it.output() }

    fun movements(): List<Movement> {
        return mainRoutine.flatMap { subroutines[it] ?: error("Expected $it in $subroutines") }
    }
}

class Grid(val cells: MutableMap<Coord, Char> = mutableMapOf()) {
    fun printToString(): String {
        val sw = StringWriter()
        sw.use {
            PrintWriter(sw).use { pw ->
                val maxY = cells.keys.maxBy { it.y }?.y ?: 0
                val maxX = cells.keys.maxBy { it.x }?.x ?: 0
                for (y in 0..maxY) {
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
}

fun loadGrid(output: List<Long>): Grid {
    val grid = Grid()
    var x = 0
    var y = 0
    output.forEach { out ->
        if (out == 10L) {
            x = 0
            y += 1
        } else {
            val coord = Coord(x, y)
            x += 1
            grid.cells[coord] = out.toChar()
        }
    }
    return grid
}

fun isScaffold(c: Char) = when (c) {
    '#', '^', '>', 'v', '<' -> true
    else                    -> false
}

fun isRobot(c: Char) = when (c) {
    '^', '>', 'v', '<' -> true
    else               -> false
}

fun isIntersection(coord: Coord, grid: Grid): Boolean {
    val left = grid.cells[coord.left()] ?: ' '
    val right = grid.cells[coord.right()] ?: ' '
    val top = grid.cells[coord.top()] ?: ' '
    val bottom = grid.cells[coord.bottom()] ?: ' '
    return isScaffold(left) && isScaffold(right) && isScaffold(top) && isScaffold(bottom)
}

fun calculateAlignment(grid: Grid): Long {
    val intersections = grid.cells.filter { isScaffold(it.value) && isIntersection(it.key, grid) }
    return intersections.keys.map { it.x * it.y }.sum().toLong()
}

fun robotLocation(grid: Grid): Coord {
    val locs = grid.cells.filter { isRobot(it.value) }
    require(locs.size == 1)
    return locs.keys.first()
}

fun turnLeft(direction: Char): Char {
    return when (direction) {
        '^'  -> '<'
        '>'  -> '^'
        'v'  -> '>'
        '<'  -> 'v'
        else -> error("Invalid direction $direction")
    }
}

fun turnRight(direction: Char): Char {
    return when (direction) {
        '^'  -> '>'
        '>'  -> 'v'
        'v'  -> '<'
        '<'  -> '^'
        else -> error("Invalid direction $direction")
    }
}

fun nextMovement(direction: Char, movement: Char): Char {
    return if (movement == 'L') {
        turnLeft(direction)
    } else {
        turnRight(direction)
    }
}

fun determineMovement(robotDirection: Char, direction: Char): Char {
    return when {
        robotDirection == direction            -> 0.toChar()
        turnLeft(robotDirection) == direction  -> 'L'
        turnRight(robotDirection) == direction -> 'R'
        else                                   -> '*'
    }
}

fun determineDirection(robot: Coord, next: Coord): Char {
    return when {
        robot.x == next.x && next.y > robot.y -> 'v'
        robot.x == next.x && next.y < robot.y -> '^'
        robot.y == next.y && next.x > robot.x -> '>'
        robot.y == next.y && next.x < robot.x -> '<'
        else                                  -> error("Expect difference $robot, $next")
    }
}

fun isValidDirection(robotDirection: Char, robot: Coord, next: Coord): Boolean {
    val direction = determineDirection(robot, next)
    return determineMovement(robotDirection, direction) != '*'
}

fun scaffoldDirection(robot: Robot, grid: Grid): Movement? {
    val surrounds = robot.pos.surrounds().toSet()
    val potential = grid.cells.filter {
        surrounds.contains(it.key)
    }.filter {
        isScaffold(it.value)
    }.filter {
        isValidDirection(robot.direction, robot.pos, it.key)
    }.toMutableMap()
    val movements = potential.map {
        createMovementUntilEnd(robot, it.key, grid)
    }
    if (movements.size > 1) {
        println("Found multiple movements:$movements")
    }
    return movements.maxBy { it.distance }
}

fun createMovementUntilEnd(robot: Robot, next: Coord, grid: Grid): Movement {
    val diffX = next.x - robot.pos.x
    val diffY = next.y - robot.pos.y
    var step = Coord(next.x + diffX, next.y + diffY)
    while (isScaffold(grid.cells[step] ?: ' ')) {
        step = step.copy(x = step.x + diffX, y = step.y + diffY)
    }
    val movement = determineMovement(robot.direction, determineDirection(robot.pos, next))
    return Movement(movement, step.distance(next))
}

fun determineRouteInstructions(grid: Grid): List<Movement> {
    val result = mutableListOf<Movement>()
    val robotLocation = robotLocation(grid)
    val robotDirection = grid.cells[robotLocation] ?: error("Expected robot at $robotLocation")
    var robot = Robot(robotLocation, robotDirection)
    do {
        val direction = scaffoldDirection(robot, grid)
        if (direction == null) {
            println("End = $robot")
            break
        }
        result.add(direction)
        robot = walk(robot, direction)
    } while (true)
    return result
}

fun walk(robot: Robot, direction: Movement): Robot {
    val newDirection = nextMovement(robot.direction, direction.movement)
    val newLocation = when (newDirection) {
        '^'  -> robot.pos.north(direction.distance)
        '>'  -> robot.pos.east(direction.distance)
        'v'  -> robot.pos.south(direction.distance)
        '<'  -> robot.pos.west(direction.distance)
        else -> error("Invalid direction $newDirection")
    }
    return Robot(newLocation, newDirection)
}

data class Attempt(var name: Char, val movements: List<Movement>) {
    fun output() = movements.joinToString(",") { it.output() }
    fun len() = output().length
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as Attempt
        if (movements != other.movements) return false
        return true
    }

    override fun hashCode(): Int {
        return movements.hashCode()
    }
}

fun attemptMove(attempt: Attempt, movements: List<Movement>): Boolean {
    if (attempt.movements.size <= movements.size) {
        val compare = movements.subList(0, attempt.movements.size)
        return attempt.movements == compare
    }
    return false
}

fun attemptRoute(sets: Set<Attempt>, movements: List<Movement>): Pair<Boolean, List<Char>> {
    for (attempt in sets) {
        if (attemptMove(attempt, movements)) {
            if (attempt.movements.size == movements.size) {
                return Pair(true, listOf(attempt.name))
            }
            val attemptMovements = movements.subList(attempt.movements.size, movements.lastIndex + 1)
            val result = attemptRoute(sets, attemptMovements)
            if (result.first) {
                return Pair(true, listOf(attempt.name) + result.second)
            }
        }
    }
    return Pair(false, emptyList())
}

fun checkValidAndResults(sets: Set<Attempt>, movements: List<Movement>): Pair<Boolean, List<Attempt>> {
    val attemptMap = sets.map { it.name to it }.toMap()
    val result = attemptRoute(sets, movements)
    return Pair(result.first, result.second.map { attemptMap[it] ?: error("Expected $it in $attemptMap") })
}

fun createIntCodeInput(functionString: String) = functionString.map { it.toLong() } + 10L

fun createCleaningProgram(instructionSet: Pair<Set<Attempt>, List<Attempt>>): CleaningProgram {
    val names = instructionSet.first.map { it to it.name }.toMap()
    require(instructionSet.second.all { names.keys.contains(it) })
    require(names.size == names.size)
    val mainRoutine = instructionSet.second.map { names[it] ?: error("Expected $it in $names") }
    require(mainRoutine.toSet().size == names.size)
    val subroutines = names.keys.map { it.name to it.movements }.toMap()
    return CleaningProgram(mainRoutine, subroutines)
}

fun findRepeating(
    totalSets: Int,
    maxOutput: Int,
    movements: List<Movement>
): List<CleaningProgram> {
    val attempts = createAttempts(movements, maxOutput)
    println("Potential Subroutines = ${attempts.size}")
    val attemptArray = attempts.keys.toTypedArray()
    val combinations = Combinations(totalSets, attempts.size)
    val attemptSets = combinations.combinations.map { combination ->
        combination.mapIndexed { index, attempt ->
            attemptArray[attempt].copy(name = ('A'.toInt() + index).toChar())
        }.toSet()
    }
    println("Testing combinations = ${attemptSets.size}")
    val validCombinations = attemptSets.map {
        Pair(it, checkValidAndResults(it, movements))
    }.filter {
        it.second.first && it.second.second.size * 2 <= maxOutput
    }.map {
        Pair(it.first, it.second.second)
    }.sortedBy { it.second.size }

    println("Valid Combindations=${validCombinations.size}")
    val programs = validCombinations.map { createCleaningProgram(it) }
    programs.forEachIndexed { index, cleaningProgram ->
        print("Valid:$index:")
        println(cleaningProgram.printToString())
        require(movements == cleaningProgram.movements()) { "Expected $movements == ${cleaningProgram.movements()}" }
    }
    return programs
}

fun createAttempts(
    movements: List<Movement>,
    maxOutput: Int
): MutableMap<Attempt, Int> {
    val attempts = mutableMapOf<Attempt, Int>()
    for (i in movements.indices) {
        for (j in (i + 2)..movements.size) {
            val attempt = Attempt(0.toChar(), movements.subList(i, j))
            if (attempt.len() < maxOutput) {
                val count = attempts[attempt] ?: 0
                attempts[attempt] = count + 1
            }
        }
    }
    return attempts
}

fun executeInstructions(program: ProgramState, cleaningProgram: CleaningProgram) {
    program.setMemory(0, 2L)
    program.reset()
    println(cleaningProgram.printToString())
    val totalInput = mutableListOf<Long>()
    totalInput.addAll(createIntCodeInput(cleaningProgram.mainString()))
    cleaningProgram.names().forEach { name ->
        totalInput.addAll(createIntCodeInput(cleaningProgram.subRoutine(name)))
    }
    totalInput.addAll(createIntCodeInput("n"))
    println("All Input = $totalInput")
    do {
        program.executeUntilInput(totalInput)
        val output = program.extractOutput()
        if (output.size == 1) {
            println("Output=\n$output")
        } else {
            val str = output.joinToString("") { if (it < 256L) it.toChar().toString() else it.toString() }
            println(str)
        }
        totalInput.clear()
    } while (!program.isHalted())
}

fun main() {
    val code = readProgram(File("input.txt"))
    val program = Program(code).executeProgram(emptyList())
    val grid = loadGrid(program.extractOutput())
    println(grid.printToString())
    val ap = calculateAlignment(grid)
    println("Alignment Parameter = $ap")
    val movements = determineRouteInstructions(grid)
    val instructions = movements.joinToString(",") { it.output() }
    println("Movements:$instructions")
    val programs = findRepeating(3, 20, movements)
    programs.forEach { cleaningProgram ->
        executeInstructions(Program(code).createProgram(), cleaningProgram)
    }
}

