import assertk.assertThat
import assertk.assertions.isEqualTo
import com.github.corneil.aoc2019.day22.applyShuffleReverse
import com.github.corneil.aoc2019.day22.cut
import com.github.corneil.aoc2019.day22.cutIndex
import com.github.corneil.aoc2019.day22.increment
import com.github.corneil.aoc2019.day22.incrementIndex
import com.github.corneil.aoc2019.day22.reverse
import com.github.corneil.aoc2019.day22.reverseIndex
import com.github.corneil.aoc2019.day22.shuffleFrom
import org.junit.Test

class TestShuffle {
    @Test
    fun test1() {
        val deck = (0..9).toList().toTypedArray()
        val result = reverse(deck)
        val expected = (9 downTo 0).toList().toTypedArray()
        println("Result = ${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = reverseIndex(i.toLong(), deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test2() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, 3)
        val expected = arrayOf(3, 4, 5, 6, 7, 8, 9, 0, 1, 2)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        expected.forEachIndexed { index, i ->
            val reverseIndex = cutIndex(index.toLong(), -3, deck.size.toLong())
            assertThat(deck[reverseIndex.toInt()] == i)
        }
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = cutIndex(i.toLong(), 3, deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test2_cut8() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, 8)
        val expected = arrayOf(8, 9, 0, 1, 2, 3, 4, 5, 6, 7)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = cutIndex(i.toLong(), 8, deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test3() {
        val deck = (0..9).toList().toTypedArray()
        val result = cut(deck, -1)
        val expected = arrayOf(9, 0, 1, 2, 3, 4, 5, 6, 7, 8)
        println("Cut:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = cutIndex(i.toLong(), -1, deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test4() {
        val deck = (0..9).toList().toTypedArray()
        val result = increment(deck, 3)
        val expected = arrayOf(0, 7, 4, 1, 8, 5, 2, 9, 6, 3)
        println("Increment:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = incrementIndex(i.toLong(), 3, deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test4inc9() {
        val deck = (0..9).toList().toTypedArray()
        val result = increment(deck, 9)
        val expected = arrayOf(0, 9, 8, 7, 6, 5, 4, 3, 2, 1)
        println("Increment:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        for (i in 0 until deck.size) {
            val index = incrementIndex(i.toLong(), 9, deck.size.toLong())
            check[index.toInt()] = deck[i]
        }
        assertThat(check.toList()).isEqualTo(expected.toList())
    }

    @Test
    fun test5() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            deal with increment 7
            deal into new stack
            deal into new stack
            """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }

        val result = shuffleFrom(input, deck, true)
        println("Result:${result.toList()}")
        val expected = arrayOf(0, 8, 5, 2, 10, 7, 4, 1, 9, 6, 3)
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }
        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test5_reverse() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            deal into new stack
            deal into new stack
            """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }

        val result = shuffleFrom(input, deck, true)
        println("Result:${result.toList()}")
        val expected = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }
        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test6() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            deal with increment 7
            cut -2
            deal with increment 9
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        val result = shuffleFrom(input, deck)
        println("Result:${result.toList()}")
        val expected = arrayOf(6, 2, 9, 5, 1, 8, 4, 0, 7, 3, 10)
        assertThat(result.toList()).isEqualTo(expected.toList())

        val check = Array<Int>(deck.size) { -1 }

        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }

        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test6_inc7_cut2() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            cut -2
            deal with increment 7
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        val result = shuffleFrom(input, deck)
        println("Result:${result.toList()}")
        val expected = arrayOf(9, 6, 3, 0, 8, 5, 2, 10, 7, 4, 1)
        assertThat(result.toList()).isEqualTo(expected.toList())

        val check = Array<Int>(deck.size) { -1 }

        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }

        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test6_inc7() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            deal with increment 7
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        val result = shuffleFrom(input, deck)
        println("Start:${result.toList()}")
        val expected = arrayOf(0, 8, 5, 2, 10, 7, 4, 1, 9, 6, 3)
        assertThat(result.toList()).isEqualTo(expected.toList())

        val check = Array<Int>(deck.size) { -1 }

        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }

        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test6_inc9() {
        val deck = (0..10).toList().toTypedArray()
        val input = """
            deal with increment 9
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        val result = shuffleFrom(input, deck)
        println("Start:${result.toList()}")
        val expected = arrayOf(0, 5, 10, 4, 9, 3, 8, 2, 7, 1, 6)
        assertThat(result.toList()).isEqualTo(expected.toList())

        val check = Array<Int>(deck.size) { -1 }

        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }

        assertThat(check.toList()).isEqualTo(deck.toList())
    }

    @Test
    fun test7() {
        val deck = (0..10).toList().toTypedArray()
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
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        require(input.size == 10)
        val result = shuffleFrom(input, deck, true)
        val expected2 =
            cut(increment(increment(cut(increment(cut(cut(increment(cut(reverse(deck), -2), 7), 8), -4), 7), 3), 9), 3), -1)
        println("Result:${result.toList()}")
        assertThat(result.toList()).isEqualTo(expected2.toList())
        println("Expected 2 = ${expected2.toList()}")
        val expected = arrayOf(1, 8, 4, 0, 7, 3, 10, 6, 2, 9, 5)
        assertThat(result.toList()).isEqualTo(expected.toList())
        val check = Array<Int>(deck.size) { -1 }
        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong())
            .forEachIndexed { index, l ->
                check[l.toInt()] = expected[index]
            }
        assertThat(check.toList()).isEqualTo(deck.toList())

    }

    @Test
    fun testinc_9_reverse() {
        val deck = arrayOf(0, 5, 10, 4, 9, 3, 8, 2, 7, 1, 6)
        val input = """
            deal with increment 9
        """.trimIndent().split('\n').map { it.trim() }.filter { it.isNotEmpty() }
        println("Deck:${deck.toList()}")
        val expected = (0..10).toList().toTypedArray()
        var check = shuffleFrom(input, expected)
        println("Check:${check.toList()}")
        assertThat(check.toList()).isEqualTo(deck.toList())
        val result = Array<Int>(deck.size) { -1 }
        applyShuffleReverse(input, (0L until deck.size.toLong()).toList(), deck.size.toLong(), 1L)
            .forEachIndexed { index, l ->
                result[l.toInt()] = deck[index]
            }

        println("Result:${result.toList()}")
        if (result.toSet() != expected.toSet()) {
            println("Missing values ${expected.toSet() - result.toSet()}")
        }
        assertThat(result.toList()).isEqualTo(expected.toList())

    }

}
