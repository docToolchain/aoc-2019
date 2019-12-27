package com.github.corneil.aoc2019.day18

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestMapWalker {
    @Test
    fun test1() {
        val input = """
            #########
            #b.A.@.a#
            #########
        """.trimIndent()
        val map = readMap(input)

        val distance = findKeys(true, map)
        val steps = distance.second
        println("Steps = $steps")
        assertThat(distance.first).isEqualTo(8)
    }

    @Test
    fun test2() {
        val input = """
            ########################
            #f.D.E.e.C.b.A.@.a.B.c.#
            ######################.#
            #d.....................#
            ########################
        """.trimIndent()
        val map = readMap(input)
        val distance = findKeys(true, map)
        assertThat(distance.first).isEqualTo(86)
    }

    @Test
    fun test3() {
        val input = """
            ########################
            #...............b.C.D.f#
            #.######################
            #.....@.a.B.c.d.A.e.F.g#
            ########################
        """.trimIndent()
        val map = readMap(input)
        val distance = findKeys(true, map)
        assertThat(distance.first).isEqualTo(132)
    }

    @Test
    fun test4() {
        val input = """
            #################
            #i.G..c...e..H.p#
            ########.########
            #j.A..b...f..D.o#
            ########@########
            #k.E..a...g..B.n#
            ########.########
            #l.F..d...h..C.m#
            #################
        """.trimIndent()
        val map = readMap(input)
        val distance = findKeys(System.getProperty("print.progress", "false").toBoolean(), map)
        assertThat(distance.first).isEqualTo(136)
    }

    @Test
    fun test5() {
        val input = """
            ########################
            #@..............ac.GI.b#
            ###d#e#f################
            ###A#B#C################
            ###g#h#i################
            ########################
        """.trimIndent()
        val map = readMap(input)
        val distance = findKeys(true, map)
        assertThat(distance.first).isEqualTo(81)
        // There are 4 solutions for 81 steps.
    }
}
