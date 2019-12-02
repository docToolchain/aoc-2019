package aoc2019

import java.io.File
import kotlin.math.min

// tag::theSolution[]
val ops : Array<(Int, Int) -> Int> = arrayOf(Int::plus, Int::times) // <1>

fun run(program: List<Int>, noun: Int, verb: Int): IntArray {

	val mem = program.toIntArray() // <2>
	mem[1] = noun
	mem[2] = verb

	for (i in 0..mem.size step 4) { // <3>
		val inst = mem.sliceArray(i.until(min(i + 4, mem.size))) // <4>
		when (inst[0]) { // <5>
			in 1..2 -> mem[inst[3]] = ops[inst[0]-1].invoke(mem[inst[1]], mem[inst[2]])
			99 -> return mem
		}
	}
	return mem;
}
// end::theSolution[]

// tag::readingTheInput[]
val program = File("input.txt").readText().split(",").map { it.trim().toInt() }
// end::readingTheInput[]

println(run(program, 12, 2)[0])

// tag::starTwo[]
for(noun in 0..99) {
	for(verb in 0..99) {
		val mem = run(program, noun, verb);
		if(mem[0] == 19690720) {
			println(100 * noun + verb)
			break
		}
	}
}
// end::starTwo[]
