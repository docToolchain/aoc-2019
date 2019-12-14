package com.github.corneil.aoc2019.day13

import com.github.corneil.aoc2019.day13.TILE.BALL
import com.github.corneil.aoc2019.day13.TILE.BLOCK
import com.github.corneil.aoc2019.day13.TILE.EMPTY
import com.github.corneil.aoc2019.day13.TILE.PADDLE
import com.github.corneil.aoc2019.day13.TILE.WALL
import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.readProgram
import org.fusesource.jansi.Ansi
import org.fusesource.jansi.Ansi.Color.BLACK
import org.fusesource.jansi.Ansi.Color.WHITE
import org.fusesource.jansi.Ansi.Erase.FORWARD
import org.fusesource.jansi.AnsiConsole
import java.io.File

enum class TILE(val tile: Int) {
    EMPTY(0),
    WALL(1),
    BLOCK(2),
    PADDLE(3),
    BALL(4)
}

fun fromValue(input: Int): TILE {
    for (v in TILE.values()) {
        if (input == v.tile) {
            return v
        }
    }
    error("Invalid TILE $input")
}

data class Coord(val x: Int, val y: Int)
data class Cell(val pos: Coord, val tile: TILE)
data class Grid(var score: Int, val cells: MutableMap<Coord, Cell> = mutableMapOf()) {
    fun setTile(pos: Coord, tile: TILE): Cell {
        val cell = Cell(pos, tile)
        cells[pos] = cell
        return cell
    }

    fun hasBlocks(): Boolean {
        return cells.values.any { it.tile == BLOCK }
    }

    fun findTile(tile: TILE): Coord {
        return cells.values.find { it.tile == tile }?.pos ?: Coord(-1, -1)
    }
}

fun printCell(cell: Cell) {
    print(Ansi.ansi().saveCursorPosition().cursor(cell.pos.y, cell.pos.x))
    when (cell.tile) {
        EMPTY  -> print(Ansi.ansi().render(" "))
        WALL   -> print(Ansi.ansi().render("#"))
        BLOCK  -> print(Ansi.ansi().bg(WHITE).fg(BLACK).render("$").reset())
        PADDLE -> print(Ansi.ansi().render("_"))
        BALL   -> print(Ansi.ansi().render("o"))
    }
    print(Ansi.ansi().restoreCursorPosition())
}

fun determineInput(grid: Grid): Int {
    val maxY = grid.cells.keys.maxBy { it.y }?.y ?: 40
    val ball = grid.findTile(BALL)
    val paddle = grid.findTile(PADDLE)
    return when {
        ball.x > paddle.x -> {
            print(Ansi.ansi().cursor(maxY, 1).fgBlue().render(">> ").reset())
            1
        }
        ball.x < paddle.x -> {
            print(Ansi.ansi().cursor(maxY, 1).fgGreen().render("<< ").reset())
            -1
        }
        else              -> {
            print(Ansi.ansi().cursor(maxY, 1).render(".. "))
            0
        }
    }
}

fun runGame(code: List<Long>): Grid {
    print(Ansi.ansi().eraseScreen())
    val grid = Grid(0)
    val program = Program(code)
    val output = mutableListOf<Long>()
    val state = program.executeProgram(emptyList()) {
        // outputHandler
        output.add(it)
        if (output.size == 3) {
            val x = output.removeAt(0).toInt()
            val y = output.removeAt(0).toInt()
            val tile = output.removeAt(0).toInt()
            if (x == -1 && y == 0) {
                grid.score = tile
                print(Ansi.ansi().cursor(0, 0).render(grid.score.toString() + " ").eraseLine(FORWARD))
            } else {
                val cell = grid.setTile(Coord(x, y), fromValue(tile))
                printCell(cell)
            }
        }
    }
    val input = mutableListOf<Long>()
    do {
        state.executeUntilInput(input)
        input.clear()
        if (grid.score > 0 && !grid.hasBlocks()) {
            println(Ansi.ansi().eraseScreen().render("Score:${grid.score}!!"))
            break
        }
        input.add(determineInput(grid).toLong())

    } while (!state.isHalted())

    return grid
}

fun main(args: Array<String>) {
    AnsiConsole.systemInstall()
    val fileName = if (args.size > 1) args[1] else "input.txt"
    val code = readProgram(File(fileName))
    val grid = runGame(code)
    val tiles = grid.cells.count { it.value.tile == BLOCK }
    println("Tiles = $tiles")

    val free = code.toMutableList()
    free[0] = 2L
    runGame(free)
    AnsiConsole.systemUninstall()
}
