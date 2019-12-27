#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File
import java.math.BigInteger
import kotlin.math.abs

// tag::helperFunctions[]
operator fun Triple<Int, Int, Int>.plus(other: Triple<Int, Int, Int>) =
    Triple(this.first + other.first, this.second + other.second, this.third + other.third)

operator fun Triple<Int, Int, Int>.times(scalar: Int) =
    Triple(this.first * scalar, this.second * scalar, this.third * scalar)

fun Triple<Int, Int, Int>.energy() = abs(this.first) + abs(this.second) + abs(this.third)
// end::helperFunctions[]

// tag::moon[]
class Moon(val name: String, var position: Triple<Int, Int, Int>, var velocity: Triple<Int, Int, Int> = Triple(0, 0, 0)) {

    fun applyGravity(otherMoon: Moon) {
        val (thisX, thisY, thisZ) = this.position
        val (otherX, otherY, otherZ) = otherMoon.position

        val velocityXUpdate = velocityUpdateValue(thisX, otherX)
        val velocityYUpdate = velocityUpdateValue(thisY, otherY)
        val velocityZUpdate = velocityUpdateValue(thisZ, otherZ)
        val velocityUpdate = Triple(velocityXUpdate, velocityYUpdate, velocityZUpdate)

        this.velocity = this.velocity + velocityUpdate
        otherMoon.velocity = otherMoon.velocity + (velocityUpdate * -1)
    }

    private fun velocityUpdateValue(value1: Int, value2: Int): Int {
        if (value1 < value2) return 1
        if (value1 == value2) return 0
        if (value1 > value2) return -1
        throw RuntimeException("Should not happen")
    }

    fun applyVelocity() {
        this.position = this.position + this.velocity
    }

    fun totalEnergy() = potentialEnergy() * kineticEnergy()

    private fun potentialEnergy() = this.position.energy()

    private fun kineticEnergy() = this.velocity.energy()

    override fun toString() = "<$name, $position, $velocity>"
}
// end::moon[]

// tag::pairOfMoonsFactory[]
fun List<Moon>.toPairOfMoons(): List<Pair<Moon, Moon>> {
    val pairs = mutableListOf<Pair<Moon, Moon>>()

    if (this.size > 1) {
        val moon = this[0]
        val otherMoons = this - moon
        for (otherMoon in otherMoons) {
            pairs.add(Pair(moon, otherMoon))
        }
        pairs.addAll(otherMoons.toPairOfMoons())
    }

    return pairs
}
// end::pairOfMoonsFactory[]

// tag::parseInput[]
fun String.toTriple() : Triple<Int, Int, Int> {
    val regex = "<x=(-?\\d+), y=(-?\\d+), z=(-?\\d+)>".toRegex()
    val matchResult = regex.matchEntire(this)
    if (matchResult != null) {
        return Triple(matchResult.groupValues[1].toInt(), matchResult.groupValues[2].toInt(), matchResult.groupValues[3].toInt())
    } else {
        throw RuntimeException("Illegal input: $this")
    }
}
// end::parseInput[]

// tag::firstStar[]
fun firstStar(initialPositions: List<Triple<Int, Int, Int>>) {
    val moons = listOf(
        Moon("Io", initialPositions[0]),
        Moon("Europa", initialPositions[1]),
        Moon("Ganymede", initialPositions[2]),
        Moon("Callisto", initialPositions[3])
    )
    val pairsOfMoons = moons.toPairOfMoons()

    for (step in 1..1000) {
        for (pairOfMoons in pairsOfMoons) {
            pairOfMoons.first.applyGravity(pairOfMoons.second)
        }

        for (moon in moons) {
            moon.applyVelocity()
        }
    }

    val totalSystemEnergy = moons.map { it.totalEnergy() }.sum()
    println("First star: $totalSystemEnergy")
}

val initialPositions = File("./input.txt").readLines().map { it.toTriple() }
firstStar(initialPositions.toList())
// end::firstStar[]

// tag::secondStar[]
class UniverseSnapshot(val moons: List<Moon>) {
    fun splitByAxis(): Map<String, String> {
        val axisMap = mutableMapOf<String, String>()
        axisMap["x"] = moons.map { "(${it.position.first},${it.velocity.first})" }.joinToString(";")
        axisMap["y"] = moons.map { "(${it.position.second},${it.velocity.second})" }.joinToString(";")
        axisMap["z"] = moons.map { "(${it.position.third},${it.velocity.third})" }.joinToString(";")
        return axisMap
    }
}

fun secondStar(initialPositions: List<Triple<Int, Int, Int>>) {
    val moons = listOf(
        Moon("Io", initialPositions[0]),
        Moon("Europa", initialPositions[1]),
        Moon("Ganymede", initialPositions[2]),
        Moon("Callisto", initialPositions[3])
    )
    val pairsOfMoons = moons.toPairOfMoons()

    val initialAxis = UniverseSnapshot(moons).splitByAxis()
    val iterationsToComplete = mutableMapOf(
        "x" to -1,
        "y" to -1,
        "z" to -1
    )
    var count = 0
    do {
        count++

        for (pairOfMoons in pairsOfMoons) {
            pairOfMoons.first.applyGravity(pairOfMoons.second)
        }

        for (moon in moons) {
            moon.applyVelocity()
        }

        val axisMap = UniverseSnapshot(moons).splitByAxis()
        axisMap.forEach {(key, value) ->
            val countForAxis = iterationsToComplete[key] ?: -1
            if (countForAxis < 0) {
                if (value == initialAxis[key]) {
                    iterationsToComplete[key] = count
                }
            }
        }
    } while(iterationsToComplete.values.any { it < 0 })

    val iterations = iterationsToComplete.values.toList().map { it.toBigInteger() }.reduce { first, second -> leastCommonMultiple(first, second) }
    println("Second star: $iterations")
}

fun leastCommonMultiple(first: BigInteger, second: BigInteger): BigInteger {
    val multiplier = if (first > second) first else second
    var lcm = multiplier

    var counter = 1
    while (!(lcm % first == 0.toBigInteger() && lcm % second == 0.toBigInteger())) {
        lcm = multiplier * counter.toBigInteger()
        counter++
    }

    return lcm
}


secondStar(initialPositions.toList())
// end::secondStar[]