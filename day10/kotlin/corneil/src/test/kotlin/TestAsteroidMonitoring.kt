package com.github.corneil.aoc2019.day10

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestAsteroidMonitoring {
    @Test
    fun test1() {
        val input = """.#..#
                            .....
                            #####
                            ....#
                            ...##
                            """.trimIndent()
        val map = readMap(input.split('\n'))
        println("Asteroids=${map.asteroidLocations}")

        val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
        val best = counts.maxBy { it.second }
        println("best=$best")
        assertThat(best).isEqualTo(Pair(Coord(3, 4), 8))
        val shot = shootInOrder(map, best!!.first)
        println("Shot = $shot")
    }

    @Test
    fun test2() {
        val input = """......#.#.
                            #..#.#....
                            ..#######.
                            .#.#.###..
                            .#..#.....
                            ..#....#.#
                            #..#....#.
                            .##.#..###
                            ##...#..#.
                            .#....####""".trimIndent()
        val map = readMap(input.split('\n'))
        val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
        val best = counts.maxBy { it.second }
        println("best=$best")
        assertThat(best).isEqualTo(Pair(Coord(5, 8), 33))
    }

    @Test
    fun test3() {
        val input = """#.#...#.#.
                            .###....#.
                            .#....#...
                            ##.#.#.#.#
                            ....#.#.#.
                            .##..###.#
                            ..#...##..
                            ..##....##
                            ......#...
                            .####.###.""".trimIndent()
        val map = readMap(input.split('\n').map { it.trim() })
        val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
        val best = counts.maxBy { it.second }
        println("best=$best")
        assertThat(best).isEqualTo(Pair(Coord(1, 2), 35))
    }

    @Test
    fun test4() {
        val input = """.#..#..###
                            ####.###.#
                            ....###.#.
                            ..###.##.#
                            ##.##.#.#.
                            ....###..#
                            ..#.#..#.#
                            #..#.#.###
                            .##...##.#
                            .....#.#..""".trimIndent()
        val map = readMap(input.split('\n').map { it.trim() })
        val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
        val best = counts.maxBy { it.second }
        println("best=$best")
        assertThat(best).isEqualTo(Pair(Coord(6, 3), 41))
    }

    @Test
    fun test5() {
        val input = """.#..##.###...#######
                            ##.############..##.
                            .#.######.########.#
                            .###.#######.####.#.
                            #####.##.#.##.###.##
                            ..#####..#.#########
                            ####################
                            #.####....###.#.#.##
                            ##.#################
                            #####.##.###..####..
                            ..######..##.#######
                            ####.##.####...##..#
                            .#####..#.######.###
                            ##...#.##########...
                            #.##########.#######
                            .####.#.###.###.#.##
                            ....##.##.###..#####
                            .#.#.###########.###
                            #.#.#.#####.####.###
                            ###.##.####.##.#..##""".trimIndent()
        val map = readMap(input.split('\n').map { it.trim() })
        val counts = map.asteroidLocations.map { it to findLineOfSightCount(map, it) }
        val best = counts.maxBy { it.second }
        println("best=$best")
        assertThat(best).isEqualTo(Pair(Coord(11, 13), 210))
        val shot = shootInOrder(map, best!!.first)
        assertThat(shot[199]).isEqualTo(Coord(8, 2))
    }
}
