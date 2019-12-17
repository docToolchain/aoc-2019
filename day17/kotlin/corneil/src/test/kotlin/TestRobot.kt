package com.github.corneil.aoc2019.day17

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test
import kotlin.test.assertTrue

class TestRobot {
    @Test
    fun testLoad() {
        val input = """
            #######...#####
            #.....#...#...#
            #.....#...#...#
            ......#...#...#
            ......#...###.#
            ......#.....#.#
            ^########...#.#
            ......#.#...#.#
            ......#########
            ........#...#..
            ....#########..
            ....#...#......
            ....#...#......
            ....#...#......
            ....#####...... 
        """.trimIndent()
        val output = input.map { it.toLong() }
        val grid = loadGrid(output)
        println(grid.printToString())
        val movements = determineRouteInstructions(grid)
        val mov = movements.map { it.output() }.joinToString(",")
        println("Movements=$mov")
        assertThat(mov).isEqualTo("R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2")
        var found = false
        val instructionSets = findRepeating(3, 20, movements)
        instructionSets.forEach { instructionSet ->
            println(instructionSet.printToString())
            val routines = instructionSet.names().map { instructionSet.subRoutine(it) }.toSet()
            if (routines.contains("R,8,R,8") &&
                routines.contains("R,4,R,4,R,8") &&
                routines.contains("L,6,L,2")
            ) {
                found = true
            }
        }
        assertTrue(found)
    }
}
