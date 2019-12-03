package aoc2019;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class Solution {

	// tag::thePoint[]
	static class Point {

		final int x;
		final int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		List<Point> createSegment(String s) {
			Function<Integer, Point> newPoint;
			switch (s.charAt(0)) {
				case 'R':
					newPoint = i -> new Point(x + i, y);
					break;
				case 'L':
					newPoint = i -> new Point(x - i, y);
					break;
				case 'U':
					newPoint = i -> new Point(x, y + i);
					break;
				case 'D':
					newPoint = i -> new Point(x, y - i);
					break;
				default:
					newPoint = i -> this;
			}

			var steps = Integer.parseInt(s.substring(1));
			var segment = new ArrayList<Point>(steps);
			for (int i = 1; i <= steps; ++i) {
				segment.add(newPoint.apply(i));
			}
			return segment;
		}
		// end::thePoint[]

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Point point = (Point) o;
			return x == point.x &&
				y == point.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		// tag::thePoint[]
	}
	// end::thePoint[]

	public static void main(String... a) throws IOException {

		// tag::creatingTheWires[]
		var wires = Files.readAllLines(Path.of("input.txt"));
		var pointsOfWires = List.of(new HashMap<Point, Integer>(), new HashMap<Point, Integer>());
		for (int i = 0; i < 2; ++i) { // <1>
			var wirePoints = pointsOfWires.get(i);
			var startingPoint = new Point(0, 0); // <2>
			var steps = 0;
			for (String segments : wires.get(i).split(",")) { // <3>
				for (Point l : startingPoint.createSegment(segments)) {
					wirePoints.put(l, ++steps);
					startingPoint = l; // <4>
				}
			}
		}
		// end::creatingTheWires[]

		// tag::findIntersections[]
		var intersectingPoints = new HashSet<>(pointsOfWires.get(0).keySet());
		intersectingPoints.retainAll(pointsOfWires.get(1).keySet());
		// end::findIntersections[]

		// tag::firstStar[]
		intersectingPoints.stream()
			.map(p -> Math.abs(p.x) + Math.abs(p.y))
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);
		// end::firstStar[]

		// tag::secondStar[]
		intersectingPoints.stream()
			.map(p -> pointsOfWires.get(0).get(p) + pointsOfWires.get(1).get(p))
			.sorted()
			.findFirst()
			.ifPresent(System.out::println);
		// end::secondStar[]
	}
}
