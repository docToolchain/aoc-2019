package aoc2019;

import java.util.function.UnaryOperator;
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

		// tag::starOne[]
		var fuelForMass = Files.readAllLines(Path.of("input.txt")).stream() // <1>
			.map(Integer::parseInt) // <2>
			.map(computeFuel) // <3>
			.reduce(0, (a,b) -> a + b); // <4>

		System.out.println("Fuel for mass " + fuelForMass);
		// end::starOne[]

		// tag::starTwo[]
		var totalFuel = Files.readAllLines(Path.of("input.txt")).stream()
			.map(Integer::parseInt)
			.map(computeFuel)
			.flatMap(fuelForIndividualMass -> { // <1>
				var builder = Stream.<Integer>builder();
				builder.add(fuelForMass); // <2>
				for(
					var fuel = computeFuel.apply(fuelForIndividualMass); 
					fuel > 0; 
					fuel = computeFuel.apply(fuel)
				) { // <3>
					builder.add(fuel);
				}
				return builder.build();
			})
			.reduce(0, (a,b) -> a + b);
		// end::starTwo[]

		System.out.println("Total fuel needed " + totalFuel);
	}
}