package com.github.corneil.aoc2019.intcode

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestBoostCode {
    @Test
    fun test1() {
        // given
        val program = readProgram("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99")
        // when
        val output = Program(program).executeProgram(emptyList())
        // then
        println("output = ${output.outputs()}")
        assertThat(output.outputs()).isEqualTo(program)
    }

    @Test
    fun test2() {
        // given
        val program = readProgram("1102,34915192,34915192,7,4,7,99,0")
        //when
        val output = Program(program).executeProgram(emptyList())
        // then
        println("output = ${output.outputs()}")
        assertThat(output.outputs()[0].toString().length).isEqualTo(16)
    }

    @Test
    fun test3() {
        // given
        val program = readProgram("104,1125899906842624,99")
        // when
        val output = Program(program).executeProgram(emptyList())
        // then
        println("output = ${output.outputs()}")
        assertThat(output.outputs()[0]).isEqualTo(program[1])
    }
}
