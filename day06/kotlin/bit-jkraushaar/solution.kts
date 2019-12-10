#!/usr/bin/env -S kotlinc-jvm -script

import java.io.File

// tag::node[]
data class Node(val id: String) {
    private var parent: Node? = null
    val children = mutableSetOf<Node>()

    fun addChild(node: Node) {
        children.add(node)
        node.parent = this
    }

    fun pathToRoot(): List<Node> {
        val path = mutableListOf<Node>()
        var tmpNode: Node? = this
        while(tmpNode?.parent != null) {
            tmpNode = tmpNode.parent
            if (tmpNode != null) {
                path.add(tmpNode)
            }
        }
        return path
    }

    fun level(): Int = pathToRoot().size
}
// end::node[]

// tag::factoryFunction[]
fun createNodesFrom(orbitDefinition: Pair<String, String>) {
    val firstNode = orbitMap.getOrPut(orbitDefinition.first) { Node(orbitDefinition.first) }
    val secondNode = orbitMap.getOrPut(orbitDefinition.second) { Node(orbitDefinition.second) }
    firstNode.addChild(secondNode)
}

val orbitMap = mutableMapOf<String, Node>()

File("./input.txt").readLines()
    .map { orbit -> orbit.split(")", limit = 2) }
    .map { ids -> ids[0] to ids[1] }
    .forEach { createNodesFrom(it) }
// end::factoryFunction[]

// tag::firstStar[]
fun totalLevels(node: Node): Int {
    var totalLevels = node.level()
    for (child in node.children) {
        totalLevels += totalLevels(child)
    }
    return totalLevels
}

val comNode = orbitMap["COM"]
if (comNode != null) {
    println(totalLevels(comNode))
}
// end::firstStar[]

// tag::secondStar[]
val youNode = orbitMap["YOU"]
val sanNode = orbitMap["SAN"]
if (youNode != null && sanNode != null) {
    val firstCommonNode = youNode.pathToRoot().intersect(sanNode.pathToRoot()).first()
    val youToCommon = youNode.pathToRoot().indexOf(firstCommonNode)
    val sanToCommon = sanNode.pathToRoot().indexOf(firstCommonNode)
    println(youToCommon + sanToCommon)
}
// end::secondStar[]
