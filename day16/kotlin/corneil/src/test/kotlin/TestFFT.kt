package com.github.corneil.aoc2019.day16

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestFFT {
    @Test
    fun testExpandShift() {
        val expanded = (0 until 12).map { expand(it, 2) }
        assertThat(expanded).isEqualTo(listOf(0, 1, 1, 0, 0, -1, -1, 0, 0, 1, 1, 0))
        val expanded2 = (0 until 12).map { expand(it, 3) }
        assertThat(expanded2).isEqualTo(listOf(0, 0, 1, 1, 1, 0, 0, 0, -1, -1, -1, 0))
    }

    @Test
    fun test1() {
        val input = "12345678"
        val p1 = calculatePhase(input)
        assertThat(p1).isEqualTo("48226158")
        val p2 = calculatePhase(p1)
        assertThat(p2).isEqualTo("34040438")
        val p3 = calculatePhase(p2)
        assertThat(p3).isEqualTo("03415518")
        val p4 = calculatePhase(p3)
        assertThat(p4).isEqualTo("01029498")
    }

    @Test
    fun test2() {
        val result = calculatePhases("80871224585914546619083218645595", 100)
        assertThat(result.substring(0, 8)).isEqualTo("24176176")
        val result2 = calculatePhases("19617804207202209144916044189917", 100)
        assertThat(result2.substring(0, 8)).isEqualTo("73745418")
        val result3 = calculatePhases("69317163492948606335995924319873", 100)
        assertThat(result3.substring(0, 8)).isEqualTo("52432133")
    }

    @Test
    fun test3() {
        val result = interpretSignal("03036732577212944063491565474664")
        assertThat(result).isEqualTo("84462026")
    }

    @Test
    fun test4() {
        val result = interpretSignal("02935109699940807407585447034323")
        assertThat(result).isEqualTo("78725270")
    }
    @Test
    fun test5() {
        val result = interpretSignal("03081770884921959731165446850517")
        assertThat(result).isEqualTo("53553731")
    }
}
