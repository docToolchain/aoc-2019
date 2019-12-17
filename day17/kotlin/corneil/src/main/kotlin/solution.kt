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
}

data class Movement(val movement: Char, val distance: Int) {
    fun output() = if (movement.toInt() != 0) "$movement,$distance" else "$distance"
    fun len() = output().length
}

data class CleaningProgram(val mainRoutine: List<Char>, val subroutines: Map<Char, List<Movement>>) {
    init {
        require(mainRoutine.toSet() == subroutines.keys)
    }

    fun names() = subroutines.keys.toList().sorted()
    fun mainString(): String = mainRoutine.joinToString(",")
    fun subRountine(name: Char): String = subroutines[name]!!.map { it.output() }.joinToString(",")
    fun movements(): List<Movement> {
        return mainRoutine.flatMap { subroutines[it]!! }
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

fun printGrid(grid: Grid) {
    println(grid.printToString())
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

fun applyMovement(direction: Char, movement: Char): Char {
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
    val distance = abs(step.x - next.x) + abs(step.y - next.y)
    val movement = determineMovement(robot.direction, determineDirection(robot.pos, next))
    return Movement(movement, distance)
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
    val newDirection = applyMovement(robot.direction, direction.movement)
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
    fun output() = movements.map { it.output() }.joinToString(",")
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

fun isSuccess(sets: Set<Attempt>, movements: List<Movement>): Pair<Boolean, List<Attempt>> {
    val attemptMap = sets.map { it.name to it }.toMap()
    val result = attemptRoute(sets, movements)
    if (result.first) {
        // check if expanded route is the same as movements
        val expanded = mutableListOf<Movement>()
        result.second.forEach {
            expanded.addAll((attemptMap[it] ?: error("Expected $it in $attemptMap")).movements)
        }
        require(expanded == movements) {
            val expandedString = expanded.map { it.output() }.joinToString(",")
            val movementString = movements.map { it.output() }.joinToString(",")
            "Expected \n$expandedString == \n$movementString"
        }
        return Pair(result.first, result.second.map { attemptMap[it] ?: error("Expected $it in $attemptMap") })
    }
    return Pair(false, emptyList())
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
    val validCombinations = mutableListOf<Pair<Set<Attempt>, List<Attempt>>>()
    for (combination in attemptSets) {
        val result = isSuccess(combination, movements)
        if (result.first) {
            if (result.second.size * 2 <= maxOutput) {
                validCombinations.add(Pair(combination, result.second))
            }
        }
    }
    println("Valid Combindations=${validCombinations.size}")
    validCombinations.sortByDescending { it.second.size }
    val programs = validCombinations.map { createCleaningProgram(it) }
    programs.forEachIndexed { index, cleaningProgram ->
        print("Main Routine:$index:")
        println(cleaningProgram.mainString())
        println("Functions:\n\t" + cleaningProgram.names().map { "$it:${cleaningProgram.subRountine(it)}" }.joinToString("\n\t"))
        require(movements == cleaningProgram.movements()) { "Expected $movements == ${cleaningProgram.movements()}" }
    }
    return programs
}

fun createAttempts(
    movements: List<Movement>,
    maxOutput: Int
): MutableMap<Attempt, Int> {
    val attempts = mutableMapOf<Attempt, Int>()
    for (i in 0 until movements.size) {
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

fun createIntCodeInput(functionString: String) = functionString.map { it.toLong() } + 10L

fun createCleaningProgram(instructionSet: Pair<Set<Attempt>, List<Attempt>>): CleaningProgram {
    val names = instructionSet.first.map { it to it.name }.toMap()
    require(instructionSet.second.all { names.keys.contains(it) })
    require(names.size == names.size)
    val mainRoutine = instructionSet.second.map { names[it]!! }
    require(mainRoutine.toSet().size == names.size)
    val subroutines = names.keys.map { it.name to it.movements }.toMap()
    return CleaningProgram(mainRoutine, subroutines)
}

fun executeInstructions(program: ProgramState, cleaningProgram: CleaningProgram) {
    program.setMemory(0, 2L)
    program.reset()
    val main = cleaningProgram.mainRoutine.joinToString(",")
    val totalInput = mutableListOf<Long>()
    val input = createIntCodeInput(main)
    totalInput.addAll(input)
    println("Main Routine=$main: $input")
    cleaningProgram.subroutines.keys.toList().sorted().forEach { name ->
        val functionString = cleaningProgram.subroutines[name]!!.map { it.output() }.joinToString(",")
        val functionInput = createIntCodeInput(functionString)
        println("Function $name: $functionString: $functionInput")
        totalInput.addAll(functionInput)
    }
    totalInput.addAll(createIntCodeInput("n"))
    println("All Input = $totalInput")
    do {
        program.executeUntilInput(totalInput)
        val output = program.extractOutput()
        if (output.size == 1) {
            println("Output=\n$output")
        } else {
            val str = output.map { if (it < 256L) it.toChar().toString() else it.toString() }.joinToString("")
            println(str)
        }
        totalInput.clear()
    } while (!program.isHalted())
}

fun main(args: Array<String>) {
    val code = readProgram(File("input.txt"))
    val program = Program(code).executeProgram(emptyList())
    val grid = loadGrid(program.extractOutput())
    printGrid(grid)
    val ap = calculateAlignment(grid)
    println("Alignment Parameter = $ap")
    val movements = determineRouteInstructions(grid)
    val instructions = movements.map { it.output() }.joinToString(",")
    println("Movements:$instructions")
    val programs = findRepeating(3, 20, movements)
    programs.forEach { cleaningProgram ->
        executeInstructions(Program(code).createProgram(), cleaningProgram)
    }
}

