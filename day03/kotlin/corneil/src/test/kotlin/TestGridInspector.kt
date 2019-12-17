import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.corneil.aoc2019.day3.Coord
import com.github.corneil.aoc2019.day3.Grid
import com.github.corneil.aoc2019.day3.stringToInstructions
import org.junit.Test

class TestGridInspector {
    @Test
    fun test1() {
        // given
        val input1 = "R8,U5,L5,D3"
        val input2 = "U7,R6,D4,L4"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val start = Coord(0, 0)
        val distance = grid.findClosestManhattenDistance(start, 2)
        // then
        grid.printGrid().forEach {
            println(it)
        }
        assertThat(distance).isEqualTo(6)

    }
    @Test
    fun test2() {
        // given
        val input1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        val input2 = "U62,R66,U55,R34,D71,R55,D58,R83"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val start = Coord(0, 0)
        val distance = grid.findClosestManhattenDistance(start, 2)
        // then
        grid.printGrid().forEach {
            println(it)
        }
        assertThat(distance).isEqualTo(159)

    }
    @Test
    fun test3() {
        // given
        val input1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
        val input2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val start = Coord(0, 0)
        val distance = grid.findClosestManhattenDistance(start, 2)
        // then
        grid.printGrid().forEach {
            println(it)
        }
        assertThat(distance).isEqualTo(135)

    }

    @Test
    fun testBest1() {
        val input1 = "R8,U5,L5,D3"
        val input2 = "U7,R6,D4,L4"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val best = grid.findLowestStepsIntersection(2)
        // then
        println("Best=$best")
        assertThat(best).isEqualTo(30)
    }
    @Test
    fun testBest2() {
        // given
        val input1 = "R75,D30,R83,U83,L12,D49,R71,U7,L72"
        val input2 = "U62,R66,U55,R34,D71,R55,D58,R83"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val best = grid.findLowestStepsIntersection(2)
        // then
        println("Best=$best")
        assertThat(best).isEqualTo(610)

    }

    @Test
    fun testBest3() {
        // given
        val input1 = "R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51"
        val input2 = "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7"
        val instructions1 = stringToInstructions(input1)
        val instructions2 = stringToInstructions(input2)
        // when
        val grid = Grid()
        grid.updateGrid(instructions1, 1)
        grid.updateGrid(instructions2, 2)
        val best = grid.findLowestStepsIntersection(2)
        // then
        println("Best=$best")
        assertThat(best).isEqualTo(410)

    }

}
