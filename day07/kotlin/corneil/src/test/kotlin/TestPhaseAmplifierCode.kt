package com.github.corneil.aoc2019.day5

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.corneil.aoc2019.day7.phaseAmplifierFeedback
import com.github.corneil.aoc2019.day7.phaseAmplifierTest
import com.github.corneil.aoc2019.intcode.readProgram

import org.junit.Test

class TestPhaseAmplifierCode {
    @Test
    fun test1() {
        // given
        val program = readProgram("3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0")
        // when
        val output = phaseAmplifierTest(program, arrayOf(4L, 3L, 2L, 1L, 0L).toLongArray())
        // then
        println("output = $output")
        assertThat(output).isEqualTo(43210)
    }

    @Test
    fun test2() {
        // given
        val program = readProgram("3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0")
        // when
        val output = phaseAmplifierTest(program, arrayOf(0L, 1L, 2L, 3L, 4L).toLongArray())
        // then
        println("output = $output")
        assertThat(output).isEqualTo(54321L)
    }

    @Test
    fun test3() {

        val program =
            readProgram("3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0")
        // when
        val output = phaseAmplifierTest(program, arrayOf(1L, 0L, 4L, 3L, 2L).toLongArray())
        // then
        println("output = $output")
        assertThat(output).isEqualTo(65210L)
    }

    @Test
    fun test4() {

        val program =
            readProgram("3,26,1001,26,-4,26,3,27,1002,27,2,27,1,27,26,27,4,27,1001,28,-1,28,1005,28,6,99,0,0,5")
        // when
        val output = phaseAmplifierFeedback(program, arrayOf(9L, 8L, 7L, 6L, 5L).toLongArray())
        // then
        println("output = $output")
        assertThat(output).isEqualTo(139629729)
    }

    @Test
    fun test5() {

        val program =
            readProgram("3,52,1001,52,-5,52,3,53,1,52,56,54,1007,54,5,55,1005,55,26,1001,54,-5,54,1105,1,12,1,53,54,53,1008,54,0,55,1001,55,1,55,2,53,55,53,4,53,1001,56,-1,56,1005,56,6,99,0,0,0,0,10")
        // when
        val output = phaseAmplifierFeedback(program, arrayOf(9L, 7L, 8L, 5L, 6L).toLongArray())
        // then
        println("output = $output")
        assertThat(output).isEqualTo(18216L)
    }
}
