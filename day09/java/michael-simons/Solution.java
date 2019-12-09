import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Solution {

	static class InputRequiredException extends RuntimeException {
	}

	public static class Computer {
		private final Deque<Long> stdIn = new ArrayDeque<>();
		private final Deque<Long> stdOut = new ArrayDeque<>();

		private final long[] program;

		// tag::memorySpace[]
		private final Map<Long, Long> memory;

		// end::memorySpace[]
		private Optional<Long> next;

		private int base = 0;

		private final Map<Integer, BiFunction<Long, Long, Long>> numericOperations = Map.of(
			1, (a, b) -> a + b,
			2, (a, b) -> a * b
		);

		private final Map<Integer, Function<Long, Boolean>> conditions = Map.of(
			5, (a) -> a != 0L,
			6, (a) -> a == 0L
		);

		private final Map<Integer, BiFunction<Long, Long, Boolean>> comparisons = Map.of(
			7, (a, b) -> a < b,
			8, (a, b) -> a == b
		);

		public static Computer loadProgram(List<Long> instructions) {
			return new Computer(instructions.stream().mapToLong(Long::longValue).toArray());
		}

		private Computer(long[] program) {
			this.program = program;
			this.memory = Collections.synchronizedMap(new HashMap<>());
			this.next = Optional.of(0L);
		}

		public Computer run() {

			while (next.isPresent()) {
				try {
					next = next.flatMap(this::executeInstruction);
				} catch (InputRequiredException e) {
					break;
				}
			}
			return this;
		}

		// tag::usingNewIo[]
		private Optional<Long> readStdIn() {

			return Optional.ofNullable(stdIn.poll());
		}

		private void writeStdOut(Long in) {

			stdOut.push(in);
		}

		public Computer pipe(Long in) {

			stdIn.push(in);
			return this;
		}

		public List<Long> drain() {

			var output = new ArrayList<Long>(this.stdOut.size());
			this.stdOut.descendingIterator().forEachRemaining(output::add);
			this.stdOut.clear();
			return output;
		}

		public Optional<Long> head() {

			return Optional.of(this.stdOut.poll());
		}

		// end::usingNewIo[]

		public boolean expectsInput() {
			return next.isPresent();
		}

		private Optional<Long> executeInstruction(long ptr) {

			var opcodeAndModes = Math.toIntExact(load(ptr++, 0));
			var opcode = opcodeAndModes % 100;
			int[] modes = extractModes(opcodeAndModes);
			return switch (opcode) {
				case 1, 2 -> {
					var p1 = load(load(ptr++, 0), modes[0]);
					var p2 = load(load(ptr++, 0), modes[1]);
					store(load(ptr++, 0), modes[2], numericOperations.get(opcode).apply(p1, p2));
					yield Optional.of(ptr);
				}
				// tag::newIoHandling[]
				case 3 -> {
					var v = readStdIn().orElseThrow(InputRequiredException::new);
					store(load(ptr++, 0), modes[0], v);
					yield Optional.of(ptr);
				}
				// end::newIoHandling[]
				case 4 -> {
					writeStdOut(load(load(ptr++, 0), modes[0]));
					yield Optional.of(ptr);
				}
				case 5, 6 -> {
					var p1 = load(load(ptr++, 0), modes[0]);
					var p2 = load(load(ptr++, 0), modes[1]);
					yield Optional.of(conditions.get(opcode).apply(p1) ? p2 : ptr);
				}
				case 7, 8 -> {
					var p1 = load(load(ptr++, 0), modes[0]);
					var p2 = load(load(ptr++, 0), modes[1]);
					store(load(ptr++, 0), modes[2], comparisons.get(opcode).apply(p1, p2) ? 1L : 0L);
					yield Optional.of(ptr);
				}
				// tag::relativeMemory[]
				case 9 -> {
					var l = load(load(ptr++, 0), modes[0]);
					base += l;
					yield Optional.of(ptr);
				}
				// end::relativeMemory[]
				case 99 -> Optional.empty();
				default -> throw new IllegalArgumentException(String.format("Invalid opcode %d", opcode));
			};
		}

		private static int[] extractModes(Integer of) {
			return IntStream.rangeClosed(3, 5)
				.map(i -> of % (int) Math.pow(10, i) / (int) Math.pow(10, i - 1))
				.toArray();
		}

		// tag::memorySpace[]
		private long resolveAddress(long address, int mode) {

			return mode == 2 ? base + address : address;  // <1>
		}

		private Long load(long p, int mode) {

			if (mode == 1) {
				return p;
			}

			var address = resolveAddress(p, mode);
			return this.memory.computeIfAbsent(address, i -> i > program.length ? 0L : program[Math.toIntExact(i)]);
		}

		private void store(long p, int mode, Long v) {

			var address = resolveAddress(p, mode);
			this.memory.put(address, v);
		}
		// end::memorySpace[]
	}

	public static void main(String... a) throws IOException {

		var instructions = Files.readAllLines(Path.of("input.txt")).stream().flatMap(s -> Arrays.stream(s.split(",")))
			.map(String::trim)
			.map(Long::parseLong)
			.collect(toList());

		var computer = Computer.loadProgram(instructions);
		computer.pipe(1L).run().head()
			.ifPresent(d -> System.out.println(String.format("Star one %d", d)));

		computer = Computer.loadProgram(instructions);
		computer.pipe(2L).run().head()
			.ifPresent(d -> System.out.println(String.format("Star two %d", d)));
	}
}
