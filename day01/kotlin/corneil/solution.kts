import java.io.BufferedReader
import java.io.File
import java.io.FileReader

// tag::calc1[]
fun calculateFuel(mass: Long) = (mass / 3L) - 2L

assert(calculateFuel(100756L) == 33583L)
// end::calc1[]

// tag::read[]
val massValues = mutableListOf<Long>()
BufferedReader(FileReader(File("input.txt"))).use { reader ->
    reader.readLines().forEach { line ->
        val mass = line.trim()
        if (mass.length > 0) {
            massValues.add(mass.toLong())
        }
    }
}
// end::read[]

// tag::calc2[]
val moduleFuel = massValues.map { calculateFuel(it) }.sum()
println("Module Fuel=${moduleFuel}")
// end::calc2[]

assert(calculateFuelForMass(1969L) == 966L)
assert(calculateFuelForMass(100756L) == 50346L)
// tag::calc3[]
fun calculateFuelForMass(mass: Long): Long {
    val fuel = calculateFuel(mass)
    if (fuel <= 0) {
        return 0
    }
    return fuel + calculateFuelForMass(fuel)
}

val totalFuel = massValues.map { calculateFuelForMass(it) }.sum()
println("Total Fuel=${totalFuel}")
//end::calc3[]
