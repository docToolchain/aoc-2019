package com.github.corneil.aoc2019.day11

import com.github.corneil.aoc2019.day11.DIRECTION.EAST
import com.github.corneil.aoc2019.day11.DIRECTION.NORTH
import com.github.corneil.aoc2019.day11.DIRECTION.SOUTH
import com.github.corneil.aoc2019.day11.DIRECTION.WEST
import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import java.io.File

enum class DIRECTION(val direction: Char) {
    NORTH('^'),
    EAST('>'),
    SOUTH('v'),
    WEST('<')
}

data class Cell(val color: Int = 0, val painted: Boolean = false)
data class Coord(val x: Int, val y: Int)
data class Robot(var position: Coord, var direction: DIRECTION) {
    fun turn(direction: Int) {
        if (direction == 0) {
            turnLeft()
        } else {
            turnRight()
        }
        advance()
    }

    fun advance() {
        position = when (direction) {
            NORTH -> position.copy(y = position.y - 1)
            EAST  -> position.copy(x = position.x + 1)
            SOUTH -> position.copy(y = position.y + 1)
            WEST  -> position.copy(x = position.x - 1)
        }
    }

    fun turnLeft() {
        direction = when (direction) {
            NORTH -> WEST
            WEST  -> SOUTH
            SOUTH -> EAST
            EAST  -> NORTH
        }
    }

    fun turnRight() {
        direction = when (direction) {
            NORTH -> EAST
            EAST  -> SOUTH
            SOUTH -> WEST
            WEST  -> NORTH
        }
    }
}

data class Grid(val cells: MutableMap<Coord, Cell> = mutableMapOf()) {
    fun cellColor(pos: Coord): Int {
        val cell = cells[pos] ?: Cell()
        return cell.color
    }

    fun paintCell(pos: Coord, color: Int) {
        cells[pos] = Cell(color, true)
    }
}

fun runRobot(input: List<Long>, startingColor: Int): Int {
    val program = Program(input).createProgram()
    val robot = Robot(Coord(0, 0), NORTH)
    val grid = Grid()
    val input = mutableListOf(startingColor.toLong())
    do {
        val outputs = mutableListOf<Long>()
        while (outputs.size < 2 && !program.isHalted()) {
            outputs.addAll(program.executeUntilOutput(input))
            input.clear()
        }
        if (outputs.isNotEmpty()) {
            for (i in 0 until outputs.size - 1) {
                grid.paintCell(robot.position, outputs[i].toInt())
            }
            robot.turn(outputs[outputs.size - 1].toInt())
        }
        input.add(grid.cellColor(robot.position).toLong())
    } while (!program.isHalted())
    printGrid(grid)
    return grid.cells.values.count { it.painted }
}

fun printGrid(grid: Grid) {
    val maxX = grid.cells.keys.maxBy { it.x }?.x ?: 0
    val minX = grid.cells.keys.minBy { it.x }?.x ?: 0
    val maxY = grid.cells.keys.maxBy { it.y }?.y ?: 0
    val minY = grid.cells.keys.minBy { it.y }?.y ?: 0
    for (y in minY..maxY) {
        for (x in minX..maxX) {
            val color = grid.cellColor(Coord(x, y))
            print(if (color == 0) '.' else '#')
        }
        println()
    }
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val painted = runRobot(readProgram(File(fileName)), 0)
    println("Painted = $painted")
    runRobot(readProgram(File(fileName)), 1)
}
