package com.github.corneil.aoc2019.day1

import java.io.BufferedReader
import java.io.File
import java.io.FileReader

// tag::calc1[]
fun calculateFuel(mass: Long) = (mass / 3L) - 2L
// end::calc1[]

// tag::calc3[]
fun calculateFuelForMass(mass: Long): Long {
    val fuel = calculateFuel(mass)
    if (fuel <= 0) {
        return 0
    }
    return fuel + calculateFuelForMass(fuel)
}
//end::calc3[]

fun main() {
    // tag::read[]
    val massValues = mutableListOf<Long>()
    BufferedReader(FileReader(File("input.txt"))).use { reader ->
        reader.readLines().forEach { line ->
            val mass = line.trim()
            if (mass.isNotEmpty()) {
                massValues.add(mass.toLong())
            }
        }
    }
    // end::read[]

    assert(calculateFuel(100756L) == 33583L)
    // tag::calc2[]
    val moduleFuel = massValues.map { calculateFuel(it) }.sum()
    println("Module Fuel=${moduleFuel}")
    // end::calc2[]
    assert(calculateFuelForMass(1969L) == 966L)
    assert(calculateFuelForMass(100756L) == 50346L)
    // tag::calc4[]
    val totalFuel = massValues.map { calculateFuelForMass(it) }.sum()
    println("Total Fuel=${totalFuel}")
    // end::calc4[]
}
