package com.github.corneil.aoc2019.day6

import java.io.File

class OrbitData {
    fun addOrbit(obj: String, orbiting: String) {
        orbits.add(
            Orbit(
                objects[obj] ?: createObj(obj),
                objects[orbiting] ?: createObj(orbiting)
            )
        )
    }

    private fun createObj(obj: String): Orbitable {
        val o = Orbitable(obj)
        objects[obj] = o
        return o
    }

    val objects: MutableMap<String, Orbitable> = mutableMapOf()
    val orbits: MutableList<Orbit> = mutableListOf()
    fun findOrbits(obj: String): List<Orbit> {
        val foundObj = objects[obj]
        val orbiting = orbits.filter { it.orbits == foundObj }
        return orbiting + orbiting.flatMap { findOrbits(it.centre.name) }
    }

    fun findTransfer(start: String, end: String): List<Pair<Orbitable, Orbitable>> {
        val startOrbits = findOrbits(start)
        val endOrbits = findOrbits(end)
        val shared = startOrbits.find { orbit -> endOrbits.find { it == orbit } != null }
        requireNotNull(shared) { "Expected a shared orbit" }
        val inward = startOrbits.subList(0, startOrbits.indexOf(shared))
        val outward = endOrbits.subList(0, endOrbits.indexOf(shared))
        val transfers = inward.map { it.orbits to it.centre } + outward.map { it.centre to it.orbits }
        return transfers.subList(0, transfers.size - 2)
    }

    fun findOrbit(obj: String): Orbit {
        return orbits.find { it.orbits.name == obj } ?: throw Exception("Expected to find $obj orbiting something")
    }
}

data class Orbitable(val name: String)

data class Orbit(
    val centre: Orbitable,
    val orbits: Orbitable
)

fun prepareData(input: String): List<String> {
    return input.split('\n').map { it.trim() }.toList()
}

fun loadOrbits(input: List<String>): OrbitData {
    val data = OrbitData()
    input.forEach { line ->
        val items = line.split(')')
        data.addOrbit(items[0], items[1])
    }
    return data
}

fun main(args: Array<String>) {
    val fileName = if (args.size > 1) args[0] else "input.txt"
    val orbits = loadOrbits(File(fileName).readLines().map { it.trim() })
    val allOrbits = orbits.objects.keys.flatMap { orbits.findOrbits(it) }
    println("Orbits=${allOrbits.size}")
    val you2san = orbits.findTransfer("YOU", "SAN")
    println("YOU->SAN=${you2san.size}")
    val you = orbits.findOrbit("YOU")
    val san = orbits.findOrbit("SAN")
    println("YOU=$you")
    println("SAN=$san")
    val transfer = orbits.findTransfer(you.centre.name, san.centre.name)
    println(transfer.map { it.first.name + " -> " + it.second.name }.joinToString("\n"))
    println("Transfers=${transfer.size}")
}
