package aoc2019;

import java.util.function.UnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Solution {

	public static void main(String... args) throws IOException{

		// tag::equation[]
		UnaryOperator<Integer> computeFuel = mass -> mass/3 - 2;
		// end::equation[]

		// tag::starTwo[]
		UnaryOperator<Integer> computeFuelForFuel = fuelForIndividualMass ->  // <1>
		Stream
			.iterate(computeFuel.apply(fuelForIndividualMass), fuel -> fuel > 0, computeFuel) // <2>
			.reduce(fuelForIndividualMass, Integer::sum); // <3>

		// end::starTwo[]

		// tag::someTests[]
		assert computeFuel.andThen(computeFuelForFuel).apply(1969) == 966;
		assert computeFuel.andThen(computeFuelForFuel).apply(100756) == 50346;
		assert Stream.of(1969, 100756).map(computeFuel.andThen(computeFuelForFuel))
			.reduce(0, Integer::sum) == 51312;
		// end::someTests[]

		// tag::starOne[]
		var fuelForMass = Files.readAllLines(Path.of("input.txt")).stream() // <1>
			.map(Integer::parseInt) // <2>
			.map(computeFuel) // <3>
			.reduce(0, Integer::sum); // <4>

		System.out.println("Fuel for mass " + fuelForMass);
		// end::starOne[]

		// tag::starTwo[]
		var totalFuel = Files.readAllLines(Path.of("input.txt")).stream()
			.map(Integer::parseInt)
			.map(computeFuel.andThen(computeFuelForFuel)) // <4>
			.reduce(0, Integer::sum);

		System.out.println("Total fuel needed " + totalFuel);
		// end::starTwo[]
	}
}