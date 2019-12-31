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
        val grid = readGrid(input)

        val distance = findKeys(true, grid)

        println("Steps = $distance")
        assertThat(distance).isEqualTo(8)
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
        val grid = readGrid(input)
        val distance = findKeys(true, grid)
        assertThat(distance).isEqualTo(86)
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
        val grid = readGrid(input)
        val distance = findKeys(true, grid)
        assertThat(distance).isEqualTo(132)
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
        val grid = readGrid(input)
        val distance = findKeys(System.getProperty("print.progress", "false").toBoolean(), grid)
        assertThat(distance).isEqualTo(136)
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
        val grid = readGrid(input)
        val distance = findKeys(true, grid)
        assertThat(distance).isEqualTo(81)
    }

    @Test
    fun test6() {
        val input = """
            #######
            #a.#Cd#
            ##@#@##
            #######
            ##@#@##
            #cB#Ab#
            #######
        """.trimIndent()
        val grid = readGrid(input)
        val distance = findKeysMultipleEntrances(true, grid)
        assertThat(distance).isEqualTo(8)
    }

    @Test
    fun test7() {
        val input = """
            ###############
            #d.ABC.#.....a#
            ######...######
            ######.@.######
            ######...######
            #b.....#.....c#
            ###############
        """.trimIndent()
        val grid = modifyBots(readGrid(input))
        println(grid.printToString())
        val distance = findKeysMultipleEntrances(true, grid)
        assertThat(distance).isEqualTo(24)
    }

    @Test
    fun test8() {
        val input = """
            #############
            #DcBa.#.GhKl#
            #.###...#I###
            #e#d#.@.#j#k#
            ###C#...###J#
            #fEbA.#.FgHi#
            #############
        """.trimIndent()
        val map = modifyBots(readGrid(input))
        println(map.printToString())
        val distance = findKeysMultipleEntrances(true, map)
        assertThat(distance).isEqualTo(32)
    }

    @Test
    fun test9() {
        val input = """
            #############
            #g#f.D#..h#l#
            #F###e#E###.#
            #dCba...BcIJ#
            #####.@.#####
            #nK.L...G...#
            #M###N#H###.#
            #o#m..#i#jk.#
            #############
        """.trimIndent()
        val map = modifyBots(readGrid(input))
        println(map.printToString())
        val distance = findKeysMultipleEntrances(true, map)
        assertThat(distance).isEqualTo(72)
    }
}
