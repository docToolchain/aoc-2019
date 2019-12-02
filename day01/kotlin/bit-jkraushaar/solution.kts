#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

// tag::firstStar[]
val modules = File("./input.txt").readLines().map { it.toInt() }

fun calculateFuel(mass: Int) = (mass / 3) - 2

fun calculateModuleOnlyFuel (modules : List<Int>) = modules.map { calculateFuel(it) }.sum()

val moduleOnlyFuel = calculateModuleOnlyFuel(modules)
println("First star: $moduleOnlyFuel")
// end::firstStar[]

// tag::secondStar[]
fun calculateModuleFuel(module: Int): Int {
    val moduleFuel = calculateFuel(module)
    if (moduleFuel <= 0) return 0
    return moduleFuel + calculateModuleFuel(moduleFuel)
}

fun calculateTotalFuel(modules : List<Int>) = modules.map { calculateModuleFuel(it) }.sum()

val totalFuel = calculateTotalFuel(modules)
println("Second star: $totalFuel")
// end::secondStar[]
