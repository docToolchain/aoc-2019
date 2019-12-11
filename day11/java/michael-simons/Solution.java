import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Solution {

	static class InputRequiredException extends RuntimeException {
	}

	public static class Computer {
		private final Deque<Long> stdIn = new ArrayDeque<>();
		private final Deque<Long> stdOut = new ArrayDeque<>();

		private final long[] program;

		private final Map<Long, Long> memory;

		private Optional<Long> next;

		private long base = 0L;

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

			return this.run(true);
		}

		public Computer run(boolean resetAfterHalt) {

			while (next.isPresent()) {
				try {
					next = next.flatMap(this::executeInstruction);
				} catch (InputRequiredException e) {
					break;
				}
			}

			if (!next.isPresent() && resetAfterHalt) {
				this.reset();
			}
			return this;
		}

		public Computer reset() {

			this.memory.clear();
			this.next = Optional.of(0L);
			this.base = 0L;
			return this;
		}

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

			return Optional.ofNullable(this.stdOut.poll());
		}

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
				case 3 -> {
					var v = readStdIn().orElseThrow(InputRequiredException::new);
					store(load(ptr++, 0), modes[0], v);
					yield Optional.of(ptr);
				}
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
				case 9 -> {
					var l = load(load(ptr++, 0), modes[0]);
					base += l;
					yield Optional.of(ptr);
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

		private long resolveAddress(long address, int mode) {

			return mode == 2 ? base + address : address;
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
	}

	// tag::basicComponents[]
	enum Direction {
		LEFT, RIGHT;

		static Direction fromLong(long instruction) {
			return instruction == 0L ? LEFT : RIGHT;
		}
	}

	enum View {
		NORTH("^"), SOUTH("v"), WEST("<"), EAST(">");

		private final String rep;

		View(String rep) {
			this.rep = rep;
		}

		View turn(Direction direction) {
			return switch (this) {
				case NORTH -> direction == Direction.LEFT ? WEST : EAST;
				case WEST -> direction == Direction.LEFT ? SOUTH : NORTH;
				case SOUTH -> direction == Direction.LEFT ? EAST : WEST;
				case EAST -> direction == Direction.LEFT ? NORTH : SOUTH;
			};
		}

		@Override
		public String toString() {
			return this.rep;
		}
	}

	static class Panel {
		private final int x;
		private final int y;

		Panel(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}

		Panel next(View view) {
			int dx = 0;
			int dy = 0;

			switch (view) {
				case NORTH -> dy = 1;
				case WEST -> dx = -1;
				case SOUTH -> dy = -1;
				case EAST -> dx = 1;
			}
			return new Panel(this.x + dx, this.y + dy);
		}
	}
	// end::basicComponents[]

	// tag::color[]
	enum Color {
		BLACK, WHITE;
		// end::color[]

		long toLong() {
			return switch (this) {
				case BLACK -> 0L;
				case WHITE -> 1L;
			};
		}

		@Override
		public String toString() {
			return switch (this) {
				case BLACK -> " ";
				case WHITE -> "#";
			};
		}

		static Color fromLong(Long l) {
			if (l == 0L) {
				return Color.BLACK;
			} else {
				return Color.WHITE;
			}
		}

		// tag::color[]
	}
	// end::color[]

	// tag::painting[]
	static String paint(Map<Panel, Color> panels) {
		var sb = new StringBuilder();

		var currentLine = 0;
		var currentColumn = 0;
		for (var e : panels.entrySet()) {
			var p = e.getKey();
			if (currentLine != p.getY()) {
				sb.append(System.lineSeparator());
				currentColumn = 0;
			}
			while (currentColumn < p.getX()) {
				sb.append(Color.BLACK);
				++currentColumn;
			}
			sb.append(e.getValue());
			currentLine = p.getY();
			currentColumn = p.getX() + 1;
		}

		return sb.toString();
	}
	// end::painting[]

	// Not so much relevant to the puzzle but for animating it
	interface Event<T> {
		Panel getPanel();

		T getPayload();

	}

	abstract static class AbstractEvent<T> implements Event<T> {
		final Panel panel;

		final T payload;

		AbstractEvent(Panel panel, T payload) {
			this.panel = panel;
			this.payload = payload;
		}

		@Override
		public Panel getPanel() {
			return panel;
		}

		@Override
		public T getPayload() {
			return payload;
		}
	}

	static class PanelPaintedEvent extends AbstractEvent<Color> {

		PanelPaintedEvent(Panel panel, Color color) {
			super(panel, color);
		}
	}

	static class MovedToPanelEvent extends AbstractEvent<View> {

		MovedToPanelEvent(Panel panel, View view) {
			super(panel, view);
		}

	}

	static class StoppedEvent extends AbstractEvent<Void> {

		StoppedEvent(Panel panel) {
			super(panel, null);
		}
	}

	// tag::solution[]
	static Map<Panel, Color> walkHull(Computer computer, Map.Entry<Panel, Color> startPanel, Consumer<Event> callback) {

		var panelComparator = Comparator
			.comparing(Panel::getY).reversed().thenComparing(Panel::getX); // <.>
		var panels = new TreeMap<Panel, Color>(panelComparator);

		Consumer<Event> handler = event -> {
			if (event instanceof PanelPaintedEvent) {
				PanelPaintedEvent panelPaintedEvent = (PanelPaintedEvent) event;
				panels.put(panelPaintedEvent.getPanel(), panelPaintedEvent.getPayload());
			}
		};
		handler = handler.andThen(callback);

		var currentView = View.NORTH;
		var currentPanel = startPanel.getKey();
		var currentColor = startPanel.getValue();

		// Need to initialize the start color
		handler.accept(new PanelPaintedEvent(currentPanel, currentColor));
		do {
			// Compute
			computer
				.pipe(panels.getOrDefault(currentPanel, Color.BLACK).toLong()) // <.>
				.run(false);
			var output = computer.drain();

			// Paint
			currentColor = Color.fromLong(output.get(0));
			handler.accept(new PanelPaintedEvent(currentPanel, currentColor));

			// Move
			currentView = currentView.turn(Direction.fromLong(output.get(1)));
			currentPanel = currentPanel.next(currentView);
			handler.accept(new MovedToPanelEvent(currentPanel, currentView));
		} while (computer.expectsInput());

		handler.accept(new StoppedEvent(currentPanel));

		computer.reset();
		return panels;
	}
	// end::solution[]

	public static void main(String... a) throws IOException {

		var instructions = Files.readAllLines(Path.of("input.txt")).stream().flatMap(s -> Arrays.stream(s.split(",")))
			.map(String::trim)
			.map(Long::parseLong)
			.collect(toList());

		var computer = Computer.loadProgram(instructions);
		Consumer<Event> eventHandler = event -> {
		};
		var panels = walkHull(computer, Map.entry(new Panel(0, 0), Color.BLACK), eventHandler);

		var animate = a.length == 1 && a[0].equals("--animate");
		if (animate) {
			var esc = 0x1B;
			eventHandler = event -> {
				try {
					Thread.sleep(10);
				} catch (Exception e) {
				}

				var fmt = "";
				var cur = event.getPanel();
				if (event instanceof StoppedEvent) {
					fmt = "\n\n";
				} else if (event instanceof PanelPaintedEvent) {
					fmt = "%c[%d;%df%s";
				} else {
					fmt = "%c[%d;%df%1$c[1;31m%s%1$c[0m";
				}
				var msg = String.format(fmt, esc, 3 + Math.abs(cur.y), 1 + cur.x, event.getPayload());
				System.out.print(msg);
			};

			System.out.print(String.format("%c[2J", esc));
			System.out.print(String.format("%c[%d;%dH", esc, 1, 0));
		}

		System.out.println(String.format("Star one %d", panels.size()));
		System.out.println("Star two");
		panels = walkHull(computer, Map.entry(new Panel(0, 0), Color.WHITE), eventHandler);
		if (!animate) {
			System.out.println(paint(panels));
		}
	}
}
