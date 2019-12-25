package com.github.corneil.aoc2019.day19

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.corneil.aoc2019.day24.Coord
import com.github.corneil.aoc2019.day24.Grid
import com.github.corneil.aoc2019.day24.readGrid
import com.github.corneil.aoc2019.day24.readRecursiveGrid
import org.junit.Test

class TestBugsLife {
    @Test
    fun testLoad() {
        val grid = Grid(5, 5)
        grid.setBug(Coord(0, 3), true)
        grid.setBug(Coord(1, 4), true)
        val bd1 = grid.bioDiversity(Coord(0, 3))
        assertThat(bd1).isEqualTo(32768L)
        val bd2 = grid.bioDiversity(Coord(1, 4))
        assertThat(bd2).isEqualTo(2097152L)
        val total = grid.bioDiversity()
        assertThat(total).isEqualTo(2129920L)
    }

    @Test
    fun testStep1() {
        val input = """
            ....#
            #..#.
            #..##
            ..#..
            #....
        """.trimIndent()
        val grid = readGrid(input)
        println(grid.printToString())
        grid.stepLife()
        val result = grid.printToString().trim()
        println(result)
        val expected = readGrid(
            """
            #..#.
            ####.
            ###.#
            ##.##
            .##..
        """.trimIndent()
        )

        assertThat(grid.bioDiversity()).isEqualTo(expected.bioDiversity())
    }

    @Test
    fun testStep4() {
        val input = """
            ....#
            #..#.
            #..##
            ..#..
            #....
        """.trimIndent()
        val grid = readGrid(input)
        repeat(4) {
            grid.stepLife()
        }
        val result = grid.printToString().trim()
        println(result)
        val expected = readGrid(
            """
            ####.
            ....#
            ##..#
            .....
            ##...                    
        """.trimIndent()
        )

        assertThat(grid.bioDiversity()).isEqualTo(expected.bioDiversity())
    }

    @Test
    fun testRepeat() {
        val input = """
            ....#
            #..#.
            #..##
            ..#..
            #....
        """.trimIndent()
        val grid = readGrid(input)
        val bioSet = mutableSetOf<Long>()
        var lastBD = 0L
        do {
            grid.stepLife()
            val bd = grid.bioDiversity()
            lastBD = bd
            if (bioSet.contains(bd)) {
                println(grid.printToString())
                break
            } else {
                bioSet.add(bd)
            }
        } while (true)
        assertThat(lastBD).isEqualTo(2129920L)
        assertThat(grid.totalBugs()).isEqualTo(2)
    }

    @Test
    fun testRecursive() {
        val input = """
            ....#
            #..#.
            #..##
            ..#..
            #....
        """.trimIndent()
        val grid = readRecursiveGrid(input)
        val reference = mutableMapOf<String, Coord>()
        for (y in 0 until 5) {
            for (x in 0 until 5) {
                val coord = Coord(x, y, 1)
                val pos = coord.pos(5)
                val level1 = ('A' + pos - 1).toString()
                reference[level1] = coord
                val level0 = pos.toString()
                reference[level0] = coord.copy(level = 0)
            }
        }
        val cellE = Coord(4, 0, 1)
        assertThat(reference["E"]).isEqualTo(cellE)
        val surroundsE = grid.surrounds(cellE).toSet()
        println("SurroundsE for $cellE = $surroundsE")
        val expectedE = listOf("8", "D", "14", "J").map { reference[it]!! }.toSet()
        println("Expected $expectedE")
        assertThat(surroundsE).isEqualTo(expectedE)
        val cell19 = reference["19"]!!
        assertThat(cell19).isEqualTo(Coord(3, 3, 0))
        val surrounds19 = grid.surrounds(cell19).toSet()
        val expected19 = listOf("14", "18", "20", "24").map { reference[it]!! }.toSet()
        println("Surrounds19 = $surrounds19")
        assertThat(surrounds19).isEqualTo(expected19)
        val cell14 = reference["14"]!!
        assertThat(cell14).isEqualTo(Coord(3, 2, 0))
        val surrounds14 = grid.surrounds(cell14).toSet()
        println("Surrounds14 = $surrounds14")
        val expected14 = listOf("9", "E", "J", "O", "T", "Y", "15", "19").map { reference[it]!! }.toSet()
        assertThat(surrounds14).isEqualTo(expected14)

        println(grid.printToString())
        repeat(10) {
            grid.stepLife()
        }

        for (level in grid.levels()) {
            if (grid.countBugs(level) > 0) {
                println("Depth=$level")
                println(grid.printToString(level))
            }
        }
        assertThat(grid.countBugs(-5)).isEqualTo(7)
        assertThat(grid.countBugs(-4)).isEqualTo(6)
        assertThat(grid.countBugs(-3)).isEqualTo(6)
        assertThat(grid.countBugs(-2)).isEqualTo(10)
        assertThat(grid.countBugs(-1)).isEqualTo(10)
        assertThat(grid.countBugs(0)).isEqualTo(5)
        assertThat(grid.countBugs(1)).isEqualTo(15)
        assertThat(grid.countBugs(2)).isEqualTo(12)
        assertThat(grid.countBugs(3)).isEqualTo(7)
        assertThat(grid.countBugs(4)).isEqualTo(9)
        assertThat(grid.countBugs(5)).isEqualTo(12)
        assertThat(grid.totalBugs()).isEqualTo(99)

    }
}
