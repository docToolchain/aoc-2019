package com.github.corneil.aoc2019.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestNewIntCode {
    @Test
    fun test1() {
        val program = Program(readProgram("1002,4,3,4,33"))
        val result = program.executeProgram(mutableListOf())
        println("result = ${result.memory()}")
        assertThat(result.memory(4)).isEqualTo(99L)
    }

    @Test
    fun test2() {
        val program = Program(readProgram("1101,100,-1,4,0"))
        val result = program.executeProgram(mutableListOf())
        println("result = ${result.memory()}")
        assertThat(result.memory(4)).isEqualTo(99L)
    }

    @Test
    fun test3() {
        val program =
            Program(readProgram("3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"))

        val result1 = program.executeProgram(mutableListOf(7L))
        assertThat(result1.outputs().last()).isEqualTo(999L)

        val result2 = program.executeProgram(mutableListOf(8L))
        assertThat(result2.outputs().last()).isEqualTo(1000L)

        val result3 = program.executeProgram(mutableListOf(9L))
        assertThat(result3.outputs().last()).isEqualTo(1001L)
    }
}
