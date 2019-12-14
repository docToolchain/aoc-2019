package com.github.corneil.aoc2019.day12

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestNBodyProblem {
    @Test
    fun test1() {
        val input = """
            <x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>
            """.trimIndent()
        val orbits = readOrbits(input.split('\n'))
        val step0 = """
            pos=<x=-1, y=  0, z= 2>, vel=<x= 0, y= 0, z= 0>
            pos=<x= 2, y=-10, z=-7>, vel=<x= 0, y= 0, z= 0>
            pos=<x= 4, y= -8, z= 8>, vel=<x= 0, y= 0, z= 0>
            pos=<x= 3, y=  5, z=-1>, vel=<x= 0, y= 0, z= 0>
            """.trimIndent()
        assertPositionAndVelocity(orbits, step0.split('\n').map { readPosition(it) })

        orbits.advanceOrbit()
        val step1 = """
            pos=<x= 2, y=-1, z= 1>, vel=<x= 3, y=-1, z=-1>
            pos=<x= 3, y=-7, z=-4>, vel=<x= 1, y= 3, z= 3>
            pos=<x= 1, y=-7, z= 5>, vel=<x=-3, y= 1, z=-3>
            pos=<x= 2, y= 2, z= 0>, vel=<x=-1, y=-3, z= 1>
            """.trimIndent()
        assertPositionAndVelocity(orbits, step1.split('\n').map { readPosition(it) })

        orbits.advanceOrbit()
        val step2 = """
            pos=<x= 5, y=-3, z=-1>, vel=<x= 3, y=-2, z=-2>
            pos=<x= 1, y=-2, z= 2>, vel=<x=-2, y= 5, z= 6>
            pos=<x= 1, y=-4, z=-1>, vel=<x= 0, y= 3, z=-6>
            pos=<x= 1, y=-4, z= 2>, vel=<x=-1, y=-6, z= 2>
            """.trimIndent()
        assertPositionAndVelocity(orbits, step2.split('\n').map { readPosition(it) })

        orbits.advanceOrbit()
        val step3 = """
            pos=<x= 5, y=-6, z=-1>, vel=<x= 0, y=-3, z= 0>
            pos=<x= 0, y= 0, z= 6>, vel=<x=-1, y= 2, z= 4>
            pos=<x= 2, y= 1, z=-5>, vel=<x= 1, y= 5, z=-4>
            pos=<x= 1, y=-8, z= 2>, vel=<x= 0, y=-4, z= 0>
            """.trimIndent()
        assertPositionAndVelocity(orbits, step3.split('\n').map { readPosition(it) })
        for (i in 4..10) {
            orbits.advanceOrbit()
        }
        val step10 = """
            pos=<x= 2, y= 1, z=-3>, vel=<x=-3, y=-2, z= 1>
            pos=<x= 1, y=-8, z= 0>, vel=<x=-1, y= 1, z= 3>
            pos=<x= 3, y=-6, z= 1>, vel=<x= 3, y= 2, z=-3>
            pos=<x= 2, y= 0, z= 4>, vel=<x= 1, y=-1, z=-1>
        """.trimIndent()
        assertPositionAndVelocity(orbits, step10.split('\n').map { readPosition(it) })
        val totalEnergy = orbits.totalEnergy()
        assertThat(totalEnergy).isEqualTo(179)
    }

    @Test
    fun test2() {
        val input = """<x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>""".trimIndent()
        val orbits = readOrbits(input.split('\n'))
        val original = orbits.copy()
        var count = 0
        do {
            orbits.advanceOrbit()
            count++
        } while (!orbits.sameAs(original))

        println("Count=$count")
        assertThat(count).isEqualTo(2772)
    }

    @Test
    fun test3() {
        val input = """<x=-1, y=0, z=2>
            <x=2, y=-10, z=-7>
            <x=4, y=-8, z=8>
            <x=3, y=5, z=-1>""".trimIndent()
        val orbits = readOrbits(input.split('\n'))
        val count = lowestCommonMultiplier(findCycles(orbits).map { it.toBigInteger() })

        println("Count=$count")
        assertThat(count).isEqualTo(2772.toBigInteger())
    }

    @Test
    fun test4() {
        val input = """
            <x=-8, y=-10, z=0>
            <x=5, y=5, z=10>
            <x=2, y=-7, z=3>
            <x=9, y=-8, z=-3>
        """.trimIndent()
        val orbits = readOrbits(input.split('\n'))
        val count = lowestCommonMultiplier(findCycles(orbits).map { it.toBigInteger() })

        println("Count=$count")
        assertThat(count).isEqualTo(4686774924.toBigInteger())
    }

    private fun assertPositionAndVelocity(
        orbits: Orbits,
        positions1: List<Pair<Axis, Axis>>
    ) {
        orbits.moons.forEachIndexed { index, moon ->
            assertThat(moon.pos).isEqualTo(positions1[index].first)
            assertThat(moon.vel).isEqualTo(positions1[index].second)
        }
    }
}
