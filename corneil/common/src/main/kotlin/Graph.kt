package com.github.corneil.aoc2019.common

import java.util.*
import kotlin.collections.HashMap

/**
 * This Graph is very statefull and cannot be shared.
 * Maintain the collections of edges and use when needed.
 */
class Graph<T : Comparable<T>>(
    edges: Collection<Edge<T>>,
    directed: Boolean
) {
    class Vertex<T : Comparable<T>>(
        val key: T,
        var distance: Int = Int.MAX_VALUE,
        var prev: Vertex<T>? = null,
        val neighbours: MutableMap<Vertex<T>, Int> = mutableMapOf()
    ) : Comparable<Vertex<T>> {
        override fun compareTo(other: Vertex<T>): Int {
            var result = distance.compareTo(other.distance)
            if (result == 0) {
                result = key.compareTo(other.key)
            }
            return result
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Vertex<T>

            if (key != other.key) return false
            if (distance != other.distance) return false

            return true
        }

        override fun hashCode(): Int {
            var result = key.hashCode()
            result = 31 * result + distance
            return result
        }

        fun findPath(path: MutableList<Pair<T, Int>>) {
            if (this == prev) {
                path.add(Pair(key, distance))
            } else if (prev != null) {
                prev!!.findPath(path)
                path.add(Pair(key, distance))
            }
        }
    }

    data class Edge<T>(val c1: T, val c2: T, val distance: Int)

    private val graph = HashMap<T, Vertex<T>>(edges.size)

    init {
        for (e in edges) {
            if (!graph.containsKey(e.c1)) {
                graph[e.c1] = Vertex(e.c1)
            }
            if (!graph.containsKey(e.c2)) {
                graph[e.c2] = Vertex(e.c2)
            }
        }

        // another pass to set neighbouring vertices
        for (e in edges) {
            graph[e.c1]!!.neighbours[graph[e.c2]!!] = e.distance
            if (!directed) {
                graph[e.c2]!!.neighbours[graph[e.c1]!!] = e.distance
            }
        }
    }

    /**
     * Set the starting point for a findPath
     */
    fun dijkstra(start: T) {
        if (!graph.containsKey(start)) {
            error("Cannot find start $start")
        }
        val source = graph[start]
        val q = TreeSet<Vertex<T>>()

        // set-up vertices
        for (v in graph.values) {
            v.prev = if (v == source) source else null
            v.distance = if (v == source) 0 else Int.MAX_VALUE
            q.add(v)
        }

        dijkstra(q)
    }

    private fun dijkstra(q: TreeSet<Vertex<T>>) {
        while (!q.isEmpty()) {

            val u = q.pollFirst()
            if (u.distance == Int.MAX_VALUE) break
            for (a in u.neighbours) {
                val v = a.key

                val alternateDist = u.distance + a.value
                if (alternateDist < v.distance) {
                    q.remove(v)
                    v.distance = alternateDist
                    v.prev = u
                    q.add(v)
                }
            }
        }
    }

    /**
     * Set starting point and determine shortest path
     */
    fun findPath(start: T, end: T): List<Pair<T, Int>> {
        dijkstra(start)
        return findPath(end)
    }

    /**
     * Find shortest path from start to end. That last pair.second will also be to total distance
     */
    fun findPath(end: T): List<Pair<T, Int>> {
        if (!graph.containsKey(end)) {
            error("Cannot find $end")
        }
        val path = mutableListOf<Pair<T, Int>>()
        graph[end]!!.findPath(path)
        return path.toList()
    }
}
