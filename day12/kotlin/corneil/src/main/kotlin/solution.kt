package com.github.corneil.aoc2019.day12

import java.math.BigInteger
import java.util.*
import kotlin.math.abs

typealias Axis = IntArray

fun Axis.energy(): Int = this.sumBy { abs(it) }
fun Axis.copy() = this.toList().toIntArray()

class Moon(
    val id: Int,
    val pos: Axis,
    val vel: Axis
) {

    fun applyGravity(moons: List<Moon>) {
        require(pos.size == 3)
        require(vel.size == 3)
        for (i in 0..2) {
            vel[i] += moons.count { pos[i] < it.pos[i] } - moons.count { pos[i] > it.pos[i] }
        }
    }

    fun move() {
        for (i in 0..2) {
            pos[i] += vel[i]
        }
    }

    fun totalEnergy(): Int = pos.energy() * vel.energy()

    fun copy() = Moon(id, pos.copy(), vel.copy())

    fun snapShot(index: Int) = Pair(pos[index], vel[index])
}

typealias SnapShot = List<Pair<Int, Int>>

class Orbits(val moons: List<Moon>) {
    fun advanceOrbit() {
        moons.forEach { moon ->
            moon.applyGravity(moons)
        }
        moons.forEach { moon ->
            moon.move()
        }
    }

    fun totalEnergy() = moons.sumBy { it.totalEnergy() }

    fun sameAs(original: Orbits): Boolean {
        return moons.filterIndexed { index, moon ->
            val orig = original.moons[index]
            moon.pos.contentEquals(orig.pos) && moon.vel.contentEquals(orig.vel)
        }.count() == moons.size
    }

    fun copy() = Orbits(moons.map { it.copy() })

    fun snapShot(index: Int): SnapShot {
        return moons.map { it.snapShot(index) }
    }

    fun makeSnapShot(): Map<Int, SnapShot> = (0..2).map { it to snapShot(it) }.toMap()
}

fun readBlock(line: String): Map<String, String> {
    val tokens = StringTokenizer(line, "=,<>", true).toList().map { it as String }
    var name = ""
    var value = ""
    val result = mutableMapOf<String, String>()
    for (i in 0 until tokens.size) {
        val token = tokens[i]
        when (token) {
            "="      -> {
                value = tokens[i + 1].trim();
                name = tokens[i - 1].trim()
            }
            ">", "," -> result.put(name, value)
            else     -> {
            }
        }
    }
    return result
}

fun readOrbits(lines: List<String>): Orbits {
    return Orbits(lines.map { line ->
        readBlock(line).map { it.key to it.value.toInt() }.toMap()
    }.map {
        arrayOf(it["x"]!!, it["y"]!!, it["z"]!!).toIntArray()
    }.mapIndexed { i, pos ->
        Moon(i, pos, arrayOf(0, 0, 0).toIntArray())
    })
}

fun readPosition(line: String): Pair<Axis, Axis> {
    val position = readBlock(line.substringAfter("pos=").substringBefore(", vel=")).map {
        it.key to it.value.toInt()
    }.toMap().let {
        arrayOf(it["x"]!!, it["y"]!!, it["z"]!!).toIntArray()
    }
    val velocity = readBlock(line.substringAfter("vel=")).map {
        it.key to it.value.toInt()
    }.toMap().let {
        arrayOf(it["x"]!!, it["y"]!!, it["z"]!!).toIntArray()
    }
    return Pair(position, velocity)
}

// Find how often each axis cycles by comparing to a snapshot of the start.
fun findCycles(orbits: Orbits): List<Long> {
    val snap = orbits.makeSnapShot().toMutableMap()
    val cycle = mutableListOf<Long>()
    var counter = 0L
    do {
        orbits.advanceOrbit()
        counter++
        for (x in 0..2) {
            if (snap.containsKey(x)) {
                if (snap[x] == orbits.snapShot(x)) {
                    println("Cycle[$x]=$counter")
                    cycle.add(counter)
                    snap.remove(x)
                }
            }
        }
    } while (snap.isNotEmpty())
    return cycle
}

fun lowestCommonMultiplier(v1: BigInteger, v2: BigInteger): BigInteger {
    return (v1 * v2).abs() / v1.gcd(v2)
}

// This will work for any number of values
fun lowestCommonMultiplier(list: List<BigInteger>): BigInteger {
    return if (list.size == 2) {
        lowestCommonMultiplier(list[0], list[1])
    } else {
        val lcds = mutableListOf<BigInteger>()
        for (i in 0 until list.size - 1) {
            lcds.add(lowestCommonMultiplier(list[i], list[i + 1]))
        }
        lowestCommonMultiplier(lcds)
    }
}

fun main() {
    val input = """
        <x=14, y=2, z=8>
        <x=7, y=4, z=10>
        <x=1, y=17, z=16>
        <x=-4, y=-1, z=1>
        """.trimIndent()

    val orbits = readOrbits(input.split('\n'))
    val original = orbits.copy()
    repeat(1000) {
        orbits.advanceOrbit()
    }
    val totalEnergy = orbits.totalEnergy()
    println("Total Energy=$totalEnergy")
    // Ensure still valid after refactoring
    require(totalEnergy == 9139)

    val totalOrbits = lowestCommonMultiplier(findCycles(original).map { it.toBigInteger() })
    println("Total = $totalOrbits")
    // Ensure still valid after refactoring
    require(totalOrbits == BigInteger("420788524631496"))
}
