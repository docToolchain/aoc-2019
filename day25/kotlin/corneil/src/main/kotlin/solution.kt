package com.github.corneil.aoc2019.day25

import com.github.corneil.aoc2019.day25.CollectionStrategy.ALL
import com.github.corneil.aoc2019.day25.CollectionStrategy.NONE
import com.github.corneil.aoc2019.day25.CollectionStrategy.ONE
import com.github.corneil.aoc2019.intcode.Program
import com.github.corneil.aoc2019.intcode.ProgramState
import com.github.corneil.aoc2019.intcode.readProgram
import org.jgrapht.alg.shortestpath.JohnsonShortestPaths
import org.jgrapht.graph.DefaultDirectedGraph
import org.jgrapht.graph.DefaultEdge
import java.io.File
import kotlin.random.Random

fun makeInput(input: String): List<Long> {
    val lines = input.trim().split('\n')
    return lines.map { it.trim().map { it.toLong() } + 10L }.flatten()
}

fun printOutput(output: List<Long>): String {
    return output.map { if (it.toInt() < 256) it.toChar().toString() else it.toString() }.joinToString("")
}

enum class CollectionStrategy {
    NONE,
    ONE,
    ALL
}

data class Robot(
    var location: String = "",
    var move: String = "",
    var lastObject: String = "",
    var collectionStrategy: CollectionStrategy = ALL,
    val tried: MutableMap<String, MutableList<String>> = mutableMapOf(),
    val commands: MutableList<String> = mutableListOf(),
    val movements: MutableList<Pair<String, String>> = mutableListOf(),
    val objects: MutableSet<String> = mutableSetOf()
) {
    fun addObject(obj: String) {
        lastObject = obj
        objects.add(obj)
    }
}

fun findLocation(input: String): String {
    if (input.contains("== ")) {
        val sub = input.substringAfter("== ")
        return sub.substringBefore(" ==")
    }
    return ""
}

fun parseList(input: String, start: String): List<String> {
    if (input.contains(start)) {
        val output = mutableListOf<String>()
        val lines = input.substringAfter(start).trim().split('\n')
        for (line in lines) {
            if (line.startsWith("- ")) {
                output.add(line.substringAfter("- "))
            } else {
                break
            }
        }
        return output
    }
    return emptyList()
}

fun findItems(input: String): List<String> = parseList(input, "Items here:")
fun findDirections(input: String) = parseList(input, "Doors here lead:")
fun findInventory(input: String) = parseList(input, "Items in your inventory:")

fun execute(state: ProgramState, cmd: String): String {

    val output = if (cmd.isNotBlank()) {
        println("Executing:$cmd")
        val input = makeInput(cmd)
        state.executeUntilInput(input)
        printOutput(state.extractOutput())
    } else {
        println("Waiting for output")
        printOutput(state.executeUntilOutput(emptyList()))
    }
    println(output)
    if (output.contains("Unrecognized")) {
        error("Command:[$cmd]")
    }
    return output
}

val directions = setOf("north", "south", "east", "west")

class World(code: List<Long>) {
    var program = Program(code).createProgram(emptyList())
    val graph = DefaultDirectedGraph<String, DefaultEdge>(DefaultEdge::class.java)
    val links = mutableMapOf<Pair<String, String>, String>()
    val locations = mutableMapOf<String, Set<String>>()
    val locationItems = mutableMapOf<String, MutableSet<String>>()
    val locationEntries = mutableMapOf<String, MutableList<String>>()
    val allItems = mutableSetOf<String>()

    fun processCommands(robot: Robot, commands: MutableList<String>): String {
        var output = ""
        while (commands.isNotEmpty()) {
            val command = commands.removeAt(0)
            if (directions.contains(command)) {
                robot.move = command
                robot.movements.add(Pair(robot.location, command))
                val triedOptions = robot.tried[robot.location] ?: mutableListOf()
                triedOptions.add(command)
                robot.tried[robot.location] = triedOptions
                println("${robot.location} -> $command")
            }
            output = execute(program, command)
            if (output.contains("You don't see that item here")) {
                println("Reset the items for ${robot.location}")
                locationItems[robot.location] = mutableSetOf()
            }
            if (output.contains("You take the")) {
                val item = output.substringAfter("You take the").substringBefore(".").trim()
                require(allItems.contains(item)) { "Expected $item to be in $allItems" }
                robot.addObject(item)
            }
            if (output.contains("You drop the")) {
                val item = output.substringAfter("You drop the").substringBefore(".").trim()
                require(allItems.contains(item)) { "Expected $item to be in $allItems" }
                robot.objects.remove(item)
            }
            val loc = findLocation(output)
            if (loc.isNotBlank()) {
                println("Location:$loc")
                val directions = findDirections(output)
                if (directions.isNotEmpty()) {
                    println("Directions:$directions")
                    locations[loc] = directions.toSet()
                }
                if (robot.location.isNotBlank()) {
                    if (!graph.containsVertex(loc)) {
                        graph.addVertex(loc)
                    }
                    if (!graph.containsVertex(robot.location)) {
                        graph.addVertex(robot.location)
                    }
                    if (graph.getEdge(robot.location, loc) == null) {
                        graph.addEdge(robot.location, loc)
                    }
                }
                links[Pair(robot.location, loc)] = robot.move
                robot.location = loc
                val entries = locationEntries[loc] ?: mutableListOf()
                if (robot.move.isNotBlank()) {
                    entries.add(robot.move)
                }
                locationEntries[loc] = entries
            }

            val items = findItems(output)
            if (items.isNotEmpty()) {
                println("Items=$items")
                val locItems = locationItems[loc] ?: mutableSetOf()
                locItems.addAll(items)
                locationItems[loc] = locItems
                allItems.addAll(items)
            }
            val inventory = findInventory(output)
            if (inventory.isNotEmpty()) {
                robot.objects.clear()
                robot.objects.addAll(inventory)
            }
        }
        return output
    }
}

fun findUntried(location: String, move: String, world: World, tried: MutableMap<String, MutableList<String>>): String {
    val movementOptions = world.locations[location] ?: mutableSetOf()
    if (movementOptions.isNotEmpty()) {
        val triedOptions = tried[location]?.toList() ?: emptyList()
        val untriedOptions = movementOptions - triedOptions.toSet()
        if (untriedOptions.isNotEmpty()) {
            val attempt = when {
                move.isNotBlank() && untriedOptions.contains(move) -> move
                untriedOptions.size == 1                           -> untriedOptions.first()
                else                                               -> {
                    untriedOptions.toList().get(Random(System.currentTimeMillis()).nextInt(untriedOptions.size))
                }
            }
            if (attempt.isNotBlank()) {
                return attempt
            }
        }
    }
    return ""
}

fun findRoute(start: String, end: String, world: World, robot: Robot): Pair<String, List<String>> {
    val jsp = JohnsonShortestPaths(world.graph)
    val path = jsp.getPath(start, end)
    val route = mutableListOf<String>()
    if (path != null && path.vertexList.isNotEmpty()) {
        val links = mutableListOf<Pair<String, String>>()
        val startIndex = if (path.vertexList.first() == start) {
            1
        } else {
            0
        }
        var prev = start
        for (i in startIndex until path.vertexList.size) {
            val current = path.vertexList[i]
            val link = world.links[Pair(prev, current)]
            if (link != null) {
                route.add(link)
            } else {
                route.clear()
                break
            }
            prev = current
        }
    }
    if (route.isEmpty()) {
        val firstMove = robot.movements.find { it.first == start }
        if (firstMove != null) {
            val remainList = robot.movements.subList(robot.movements.indexOf(firstMove), robot.movements.lastIndex)
            val lastMove = remainList.find { it.first == end }
            if (lastMove != null) {
                val lastIndex = remainList.indexOf(lastMove)
                for (i in 0 until lastIndex) {
                    route.add(remainList[i].second)
                }
            }
        }
    }
    return Pair(end, route)
}

fun findPassword(code: List<Long>): String {

    var password = ""
    val ignoreItems = mutableSetOf("infinite loop")
    do {
        var commands = mutableListOf("inv")
        var lastTake = ""
        val dropped = mutableSetOf<String>()
        var world = World(code)
        val robot = Robot()
        var steps = 0

        try {
            do {
                if (robot.commands.isNotEmpty()) {
                    val movement = robot.commands.removeAt(0)
                    commands.add(movement)
                }
                val output = world.processCommands(robot, commands)
                if (robot.commands.isNotEmpty()) {
                    continue
                }
                if (output.contains("Analysis complete!")) {
                    password = output.substringAfter("get in by typing ").substringBefore(' ').trim()
                    continue
                }
                if (output.contains("ejected")) {
                    val loc = findLocation(output.substringAfter("ejected"))
                    if (loc.isNotBlank()) {
                        robot.location = loc
                    }
                }
                if (output.contains("Alert! Droids on this ship are lighter than the detected value!")) {
                    println("All Items:${world.allItems},Robot:${robot.objects}")
                    println("Ignored:$ignoreItems")
                    if ((robot.objects - dropped).isEmpty()) {
                        println("Clearing Dropped:$dropped")
                        dropped.clear()
                    }
                    val dropList = (robot.objects - dropped).toList()
                    val drop = dropList.get(Random(System.currentTimeMillis()).nextInt(dropList.size))
                    println("Dropping $drop")
                    robot.collectionStrategy = NONE
                    commands.add("drop $drop")
                    dropped.add(drop)
                    commands.add("inv")
                } else if (output.contains("Alert!")) {
                    println("All Items:${world.allItems},Robot:${robot.objects}")
                    println("Ignored:$ignoreItems")
                    commands.add("inv")
                    commands.add(robot.move)
                    robot.collectionStrategy = ONE
                }

                val items = world.locationItems[robot.location]?.toSet() ?: emptySet()
                if (items.isNotEmpty()) {
                    val taken = when (robot.collectionStrategy) {
                        ONE  -> {
                            val take = items.toList().get(Random(System.currentTimeMillis()).nextInt(items.size))
                            commands.add("take $take")
                            lastTake = take
                            true
                        }
                        ALL  -> {
                            var result = false
                            for (item in items) {
                                if (!ignoreItems.contains(item) && !robot.objects.contains(item)) {
                                    commands.add("take $item")
                                    lastTake = item
                                    result = true
                                }
                            }
                            result
                        }
                        NONE -> {
                            false
                        }
                    }
                    if (taken) {
                        continue
                    }
                }
                val movement = findUntried(robot.location, robot.move, world, robot.tried)
                if (movement.isNotBlank()) {
                    println("Location:${robot.location}:Untried move:$movement")
                    commands.add(movement)
                    continue
                }
                val untriedMovements = world.locations.entries.map {
                    it.key to (it.value - (robot.tried[it.key]?.toSet() ?: emptySet()))
                }.filter {
                    it.second.isNotEmpty()
                }
                val movements = untriedMovements.map { findRoute(robot.location, it.first, world, robot) }
                    .filter { it.second.isNotEmpty() }
                println("Other Untried:$movements")
                if (movements.isNotEmpty()) {
                    val route =
                        if (Random(System.currentTimeMillis()).nextBoolean()) movements.first() else movements.last()
                    println("Route:$route")
                    commands.addAll(route.second)
                    continue
                }
                if (untriedMovements.isNotEmpty()) {
                    error("Could not find routes!! $untriedMovements")
                }
                val routeToDoor = findRoute(robot.location, "Pressure-Sensitive Floor", world, robot)
                if (routeToDoor.second.isNotEmpty()) {
                    commands.addAll(routeToDoor.second)
                    continue
                }
                val routeToSecurity = findRoute(robot.location, "Security Checkpoint", world, robot)
                if (routeToSecurity.second.isNotEmpty()) {
                    commands.addAll(routeToSecurity.second)
                    commands.add("south")
                    continue
                } else {
                    println("Cannot find Security Checkpoint!!")
                }
                println("Tried:$robot.tried")
                println("Robot=$robot")
                println(":")
                val str = System.console().readLine().trim()
                if (str.isNotBlank()) {
                    when {
                        str.startsWith("take")   -> {
                            val item = str.substringAfter(' ')
                            println("Taking $item")
                        }
                        str.startsWith("drop")   -> {
                            val item = str.substringAfter(' ')
                            robot.objects.remove(item)
                        }
                        str == "inv"             -> {
                            println("Robot=$robot")
                        }
                        directions.contains(str) -> {
                            println("Move=$str")
                        }
                        else                     -> {
                            println("Invalid $str")
                        }
                    }
                    commands.add(str.trim())
                }
            } while (password.isBlank() && !world.program.isHalted() && steps++ < 1000)
        } catch (x: Throwable) {
            x.printStackTrace()
        }
        if (password.isBlank()) {
            println("Locations:${world.locations}")
            println("Locations Items:${world.locationItems}")
            println("Tried:${robot.tried}")
            print("Untried:")
            println(world.locations.entries.map {
                it.key to (it.value - (robot.tried[it.key]?.toSet() ?: emptySet()))
            }.filter {
                it.second.isNotEmpty()
            })
            println("Robot:$robot")
            if(steps < 1000) {
                println("==IGNORING:$lastTake")
                ignoreItems.add(lastTake)
            }
        } else {
            println("Robot is at ${robot.location} carrying: ${robot.objects}")
        }

    } while (password.isBlank())
    return password
}

fun main() {
    val code = readProgram(File("input.txt"))
    val password = findPassword(code)
    println("Password=$password")
    require(password.trim() == "262848")
}

