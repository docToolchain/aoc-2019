package com.github.corneil.aoc2019.day2

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestIntCode {
    @Test
    fun test1() {
        // given
        val program = readProgram("1,9,10,3,2,3,11,0,99,30,40,50")
        // when
        val result = executeProgram(program.toMutableList())
        // then
        assertThat(result.joinToString(",")).isEqualTo("3500,9,10,70,2,3,11,0,99,30,40,50")
    }

    @Test
    fun test2() {
        // given
        val program = readProgram("1,0,0,0,99")
        // when
        val result = executeProgram(program.toMutableList())
        // then
        assertThat(result.joinToString(",")).isEqualTo("2,0,0,0,99")
    }

    @Test
    fun test3() {
        // given
        val program = readProgram("2,3,0,3,99")
        // when
        val result = executeProgram(program.toMutableList())
        // then
        assertThat(result.joinToString(",")).isEqualTo("2,3,0,6,99")
    }

    @Test
    fun test4() {
        // given
        val program = readProgram("2,4,4,5,99,0")
        // when
        val result = executeProgram(program.toMutableList())
        // then
        assertThat(result.joinToString(",")).isEqualTo("2,4,4,5,99,9801")
    }

    @Test
    fun test5() {
        // given
        val program = readProgram("1,1,1,4,99,5,6,0,99")
        // when
        val result = executeProgram(program.toMutableList())
        // then
        assertThat(result.joinToString(",")).isEqualTo("30,1,1,4,2,5,6,0,99")
    }
}
