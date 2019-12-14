package com.github.corneil.aoc2019.day6

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestOrbitCheck {
    @Test
    fun test1() {
        // given
        val input = """
            COM)B
            B)C
            C)D
            D)E
            E)F
            B)G
            G)H
            D)I
            E)J
            J)K
            K)L
        """.trimIndent()
        val orbits = loadOrbits(prepareData(input))
        // when
        val allOrbits = orbits.objects.keys.flatMap { orbits.findOrbits(it) }
        // then
        assertThat(allOrbits.size).isEqualTo(42)
    }

    @Test
    fun test2() {
        val input = """COM)B
                            B)C
                            C)D
                            D)E
                            E)F
                            B)G
                            G)H
                            D)I
                            E)J
                            J)K
                            K)L
                            K)YOU
                            I)SAN""".trimIndent()
        val orbits = loadOrbits(prepareData(input))
        val transfer = orbits.findTransfers("YOU", "SAN")
        println(transfer.joinToString("\n") { it.first.name + " -> " + it.second.name })
        assertThat(transfer.size).isEqualTo(4)
        val you = orbits.findOrbit("YOU")
        val san = orbits.findOrbit("SAN")
        println("YOU=$you")
        println("SAN=$san")
        val transfer2 = orbits.findTransfers(you.centre.name, san.centre.name)
        println(transfer2.joinToString("\n") { it.first.name + " -> " + it.second.name })
        println("Transfers=${transfer2.size}")
    }
}
