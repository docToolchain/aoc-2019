package com.github.corneil.aoc2019.day20

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.Test

class TestGridWalker {
    @Test
    fun test1() {
        val input = """
#         A           
#         A           
#  #######.#########  
#  #######.........#  
#  #######.#######.#  
#  #######.#######.#  
#  #######.#######.#  
#  #####  B    ###.#  
#BC...##  C    ###.#  
#  ##.##       ###.#  
#  ##...DE  F  ###.#  
#  #####    G  ###.#  
#  #########.#####.#  
#DE..#######...###.#  
#  #.#########.###.#  
#FG..#########.....#  
#  ###########.#####  
#             Z       
#             Z       
"""
        val grid = readGrid(input)
        println(grid.printToString())
        val route = findRoute(grid, "AA", "ZZ")
        val steps = route.map { it.first.name }.joinToString(",")
        val count = route.last().second
        println("Steps = $steps")
        assertThat(count).isEqualTo(23)
    }

    @Test
    fun test2() {
        val input = """
             Z L X W       C                 
             Z P Q B       K                 
  ###########.#.#.#.#######.###############  
  #...#.......#.#.......#.#.......#.#.#...#  
  ###.#.#.#.#.#.#.#.###.#.#.#######.#.#.###  
  #.#...#.#.#...#.#.#...#...#...#.#.......#  
  #.###.#######.###.###.#.###.###.#.#######  
  #...#.......#.#...#...#.............#...#  
  #.#########.#######.#.#######.#######.###  
  #...#.#    F       R I       Z    #.#.#.#  
  #.###.#    D       E C       H    #.#.#.#  
  #.#...#                           #...#.#  
  #.###.#                           #.###.#  
  #.#....OA                       WB..#.#..ZH
  #.###.#                           #.#.#.#  
CJ......#                           #.....#  
  #######                           #######  
  #.#....CK                         #......IC
  #.###.#                           #.###.#  
  #.....#                           #...#.#  
  ###.###                           #.#.#.#  
XF....#.#                         RF..#.#.#  
  #####.#                           #######  
  #......CJ                       NM..#...#  
  ###.#.#                           #.###.#  
RE....#.#                           #......RF
  ###.###        X   X       L      #.#.#.#  
  #.....#        F   Q       P      #.#.#.#  
  ###.###########.###.#######.#########.###  
  #.....#...#.....#.......#...#.....#.#...#  
  #####.#.###.#######.#######.###.###.#.#.#  
  #.......#.......#.#.#.#.#...#...#...#.#.#  
  #####.###.#####.#.#.#.#.###.###.#.###.###  
  #.......#.....#.#...#...............#...#  
  #############.#.#.###.###################  
               A O F   N                     
               A A D   M                                 
        """
        val grid = readGrid(input)
        println(grid.printToString())
        val innerPortals = grid.innerPortals()
        grid.outerPortals().values.forEach {outer ->
            val inner = innerPortals[outer.name] ?: error("Expected to find Inner Portal ${outer.name}")
            println("${outer.name}:${outer.pos.x},${outer.pos.y}:${inner.pos.x},${inner.pos.y}")
        }
        val route = findRouteRecursive(grid, "AA", "ZZ")
        var prevDistance = 0
        var prevName = "AA:0"
        val count = route.last().second
        println("Steps:")
        route.filter { it.first.portal }.forEach {
            val distance = it.second - prevDistance
            prevDistance = it.second
            val name = "${it.first.name}:${it.first.level}"
            println("$prevName -> $name = $distance")
            prevName = name
        }
        println("$prevName -> ZZ:0 = ${count - prevDistance}")

        println("Total:$count")
        assertThat(count).isEqualTo(396)
    }
}
