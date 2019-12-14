package com.github.corneil.aoc2019.day4

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestPasswordValidation {
    @Test
    fun test1() {
        assertTrue(validPassword("111111"))
        assertFalse(validPassword("223450"))
        assertFalse(validPassword("123789"))
    }

    @Test
    fun test2() {
        assertTrue(validPassword2("112233"))
        assertFalse(validPassword2("123444"))
        assertTrue(validPassword2("111122"))
    }
}
