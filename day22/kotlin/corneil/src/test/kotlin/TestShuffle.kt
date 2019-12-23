import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.corneil.aoc2019.day22.applyShuffleReverse
import com.github.corneil.aoc2019.day22.cut
import com.github.corneil.aoc2019.day22.cutIndex
import com.github.corneil.aoc2019.day22.cutIndexReverse
import com.github.corneil.aoc2019.day22.increment
import com.github.corneil.aoc2019.day22.reverse
import com.github.corneil.aoc2019.day22.shuffleFrom
import org.junit.Test

class TestShuffle {
    @Test
    fun test1() {
        val deck = (0..9).toList().toTypedArray()
        val result = reverse(deck)
        val expected = (9 downTo 0).toList().toTypedArray()
        println("Result = ${result.toList()}")
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun test2() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, 3)
        val expected = arrayOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
        println("Cut:${result.toList()}")
        assertThat(result).isEqualTo(expected)
        expected.forEachIndexed { index, i ->
            val reverseIndex = cutIndex(index.toLong(), -3, deck.size.toLong())
            assertThat(deck[reverseIndex.toInt()] == i)
        }
    }

    @Test
    fun test2_cut8() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, 8)
        val expected = arrayOf(8, 9, 0, 1, 2, 3, 4, 5, 6, 7)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test3() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, -1)
        val expected = arrayOf(9, 0, 1, 2, 3, 4, 5, 6, 7, 8)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test3cut_6() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, -6)
        val expected = arrayOf(4, 5, 6, 7, 8, 9, 0, 1, 2, 3)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test4() {
        val deck = (0..9).toList().toTypedArray()
        val result = increment(deck, 3)
        val expected = arrayOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
        println("Increment:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test4inc9() {
        val deck = (0..9).toList().toTypedArray()
        val result = increment(deck, 9)
        val expected = arrayOf(0, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        println("Increment:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test5() {
        val deck = (0..9).toList().toTypedArray()
        val input = """
            deal with increment 7
            deal into new stack
            deal into new stack
            """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }

        val result = shuffleFrom(input, deck)
        println("Result:${result.toList()}")
        val expected = arrayOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test6() {
        val deck = (0..9).toList().toTypedArray()
        val input = """
            deal with increment 7
            deal with increment 9
            cut -2
        """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }
        val result = shuffleFrom(input, deck)
        println("Result:${result.toList()}")
        val expected = arrayOf(6, 3, 0, 7, 4, 1, 8, 5, 2, 9)
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test7() {
        val deck = (0..9).toList().toTypedArray()
        val input = """
            deal into new stack
            cut -2
            deal with increment 7
            cut 8
            cut -4
            deal with increment 7
            cut 3
            deal with increment 9
            deal with increment 3
            cut -1
        """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }
        require(input.size == 10)
        val result = shuffleFrom(input, deck, true)
        val expected2 =
            cut(increment(increment(cut(increment(cut(cut(increment(cut(reverse(deck), -2), 7), 8), -4), 7), 3), 9), 3), -1)
        println("Result:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected2.toList())
        println("Expected 2 = ${expected2.toList()}")
        val expected = arrayOf(9, 2, 5, 8, 1, 4, 7, 0, 3, 6)
        assertThat(result.toList()).isEqualTo(expected.toList())

    }

    @Test
    fun testinc_9_reverse() {
        val deck = arrayOf(0, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        val input = """
            deal with increment 9
        """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }
        println("Deck:${deck.toList()}")
        val expected = (0..9).toList().toTypedArray()
        val result = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = applyShuffleReverse(input, i.toLong(), deck.size.toLong())
            result[index.toInt()] = deck[i]
        }
        println("Result:${result.toList()}")
        for (i in 0 until deck.size) {
            val index = applyShuffleReverse(input, i.toLong(), deck.size.toLong())
            val expectedIndex = expected.indexOf(deck[i]).toLong()
            // if(index != expectedIndex) {
            println("inc=9 $i -> $index expected $expectedIndex")
            // }
        }
        assertThat(result.toList()).isEqualTo(expected.toList())

    }

    @Test
    fun testinc_3_reverse() {
        val deck = arrayOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
        val input = """
            deal with increment 3
        """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }
        val result = Array<Int>(deck.size) { -1 }
        val expected = (0..9).toList().toTypedArray()
        println("Deck:${deck.toList()}")
        for (i in 0 until deck.size) {
            val index = applyShuffleReverse(input, i.toLong(), deck.size.toLong())
            val expectedIndex = expected.indexOf(deck[i]).toLong()
            // if(index != expectedIndex) {
            println("inc=3 $i -> $index expected $expectedIndex")
            // }
            result[index.toInt()] = deck[i]
        }
        println("Result:${result.toList()}")
        println("Expected:${expected.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun testinc_7_reverse() {
        val deck = arrayOf(0, 3, 6, 9, 2, 5, 8, 1, 4, 7)
        val input = """
            deal with increment 7
        """.trimIndent().split('\n').map { it.trim() }.filter { it.length > 0 }
        val result = Array<Int>(deck.size) { -1 }
        val expected = (0..9).toList().toTypedArray()
        println("Deck:${deck.toList()}")
        for (i in 0 until deck.size) {
            val index = applyShuffleReverse(input, i.toLong(), deck.size.toLong())
            val expectedIndex = expected.indexOf(deck[i]).toLong()
            // if(index != expectedIndex) {
            println("inc=7 $i -> $index expected $expectedIndex")
            // }
            result[index.toInt()] = deck[i]
        }
        println("Result:${result.toList()}")
        println("Expected:${expected.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test_cut() {
        for (deck in 10L until 100L step 10L) {
            for (n in (0 - deck + 1) until deck) {
                for (index in 0L until deck) {
                    val forward = cutIndex(index, n.toInt(), deck)
                    val reverse = cutIndexReverse(forward, n.toInt(), deck)
                    if (reverse != index) {
                        println("N = $n, deck=$deck, $index=$index, reverse=$reverse, $forward=$forward")
                    }
                    assertThat(reverse).isEqualTo(index)
                }
            }
        }
    }

}
