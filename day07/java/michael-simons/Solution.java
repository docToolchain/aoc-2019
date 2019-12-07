package aoc2019;

import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solution {

	static class Computer {
		private final Deque<Integer> io = new ArrayDeque<>();

		private final Integer[] memory;

		private Optional<Integer> next;

		static Computer loadProgram(List<Integer> instructions) {
			return new Computer(instructions.toArray(Integer[]::new));
		}

		private Computer(Integer[] memory) {
			this.memory = memory;
			this.next = Optional.of(0);
		}

		private final Map<Integer, BiFunction<Integer, Integer, Integer>> numericOperations = Map.of(
			1, (a, b) -> a + b,
			2, (a, b) -> a * b
		);

		private final Map<Integer, Function<Integer, Boolean>> conditions = Map.of(
			5, (a) -> a != 0,
			6, (a) -> a == 0
		);

		private final Map<Integer, BiFunction<Integer, Integer, Boolean>> comparisons = Map.of(
			7, (a, b) -> a < b,
			8, (a, b) -> a == 0
		);

		public Optional<Integer> run() {

			next = next.flatMap(this::executeInstruction);
			return read();
		}

		public Optional<Integer> read() {
			return Optional.ofNullable(io.poll());
		}

		public void write(Integer in) {
			io.push(in);
		}

		public boolean expectsInput() {
			return next.isPresent();
		}

		private Optional<Integer> executeInstruction(int ptr) {
			int opcode = memory[ptr] % 100;
			int[] modes = extractModes(memory[ptr]);
			return switch (opcode) {
				case 1, 2 -> {
					var p1 = load(memory[++ptr], modes[0]);
					var p2 = load(memory[++ptr], modes[1]);
					store(memory[++ptr], numericOperations.get(opcode).apply(p1, p2));
					yield executeInstruction(++ptr);
				}
				case 3 -> {
					store(memory[++ptr], read().get());
					yield executeInstruction(++ptr);
				}
				case 4 -> {
					write(load(memory[++ptr], modes[0]));
					yield Optional.of(++ptr);
				}
				case 5, 6 -> {
					var p1 = load(memory[++ptr], modes[0]);
					var p2 = load(memory[++ptr], modes[1]);
					yield executeInstruction(conditions.get(opcode).apply(p1) ? p2 : ++ptr);
				}
				case 7, 8 -> {
					var p1 = load(memory[++ptr], modes[0]);
					var p2 = load(memory[++ptr], modes[1]);
					store(memory[++ptr], comparisons.get(opcode).apply(p1, p2) ? 1 : 0);
					yield executeInstruction(++ptr);
				}
				case 99 -> Optional.empty();
				default -> throw new IllegalArgumentException(String.format("Invalid opcode %d", opcode));
			};
		}

		private static int[] extractModes(Integer of) {
			return IntStream.rangeClosed(3, 5)
				.map(i -> of % (int) Math.pow(10, i) / (int) Math.pow(10, i - 1))
				.toArray();
		}

		private Integer load(Integer p, int mode) {
			return mode == 0 ? memory[p] : p;
		}

		private void store(Integer p, Integer v) {
			memory[p] = v;
		}
	}

	private static <T> void swap(T[] v, int i, int j) {
		T t = v[i];
		v[i] = v[j];
		v[j] = t;
	}

	@SuppressWarnings("unchecked")
	private static <T> Stream<List<T>> permutations(Collection<T> v) {
		return permuteImpl((T[]) v.toArray(), v.size());
	}

	/**
	 * See: https://en.wikipedia.org/wiki/Heap%27s_algorithm
	 *
	 * @param v
	 * @param n
	 * @return
	 */
	private static <T> Stream<List<T>> permuteImpl(T[] v, int n) {

		var builder = Stream.<List<T>>builder();
		int c[] = new int[n];

		builder.accept(List.of(v));

		int i = 0;
		while (i < n) {
			if (c[i] < i) {
				if (i % 2 == 0) {
					swap(v, 0, i);
				} else {
					swap(v, c[i], i);
				}
				builder.accept(List.of(v));
				c[i] += 1;
				i = 0;
			} else {
				c[i] = 0;
				i += 1;
			}
		}

		return builder.build();
	}

	public static void main(String... a) throws IOException {

		var instructions = Files.readAllLines(Path.of("input.txt")).stream().flatMap(s -> Arrays.stream(s.split(",")))
			.map(String::trim)
			.map(Integer::parseInt)
			.collect(toList());

		BiFunction<Integer, List<Map.Entry<Computer, Optional<Integer>>>, Integer> runSequence = (initialInput, sequence) -> {
			AtomicInteger input = new AtomicInteger(initialInput);
			for (var computerAndPhase : sequence) {
				var c = computerAndPhase.getKey();
				c.write(input.get());
				computerAndPhase.getValue().ifPresent(c::write);
				input.set(c.run().get());
			}
			return input.get();
		};

		permutations(IntStream.rangeClosed(0, 4).mapToObj(Optional::of).collect(toList()))
			.map(p -> IntStream.rangeClosed(0, p.size() - 1)
				.mapToObj(i -> Map.entry(Computer.loadProgram(instructions), p.get(i))).collect(toList()))
			.parallel()
			.map(s -> runSequence.apply(0, s))
			.max(Integer::compareTo)
			.ifPresent(d -> System.out.println(String.format("Star two %d", d)));

		permutations(IntStream.rangeClosed(5, 9).mapToObj(Optional::of).collect(toList()))
			.parallel()
			.map(phases -> {
				var computers = IntStream.rangeClosed(0, phases.size() - 1)
					.mapToObj(i -> Computer.loadProgram(instructions)).collect(toList());

				var phasesIterator = phases.iterator();
				Supplier<Optional<Integer>> phaseSupplier = () -> phasesIterator.hasNext() ?
					phasesIterator.next() :
					Optional.empty();
				Supplier<Boolean> nextRun = () -> computers.get(computers.size() - 1).expectsInput();

				var lastOutput = new AtomicInteger(0);
				while (nextRun.get()) {
					var sequence = computers.stream().map(c -> Map.entry(c, phaseSupplier.get())).collect(toList());
					lastOutput.set(runSequence.apply(lastOutput.get(), sequence));
				}
				return lastOutput.get();
			})
			.max(Integer::compareTo)
			.ifPresent(d -> System.out.println(String.format("Star two %d", d)));
	}
}
