import static java.lang.String.format;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.readAllLines(Paths.get("input.txt"));
        if(lines.size() != 2) throw new IllegalStateException("There should be exactly two lines in the input file.");

        LOGGER.info("Lowest Manhattan Distance: " + lowestManhattanDistance(lines));

    }

    static int lowestManhattanDistance(final List<String> lines) {
        var wirePathParser = new WirePathParser();
        final var wire1 = wirePathParser.parse(lines.get(0));
        LOGGER.info(format("Wire 1 has %d line segments.", wire1.size()));

        final var wire2 = wirePathParser.parse(lines.get(1));
        LOGGER.info(format("Wire 2 has %d line segments.", wire2.size()));

        var minSteps = Integer.MAX_VALUE;
        var crossPoints = new ArrayList<Coordinate>();
        for (var wire1Segment : wire1) {
            for (var wire2Segment : wire2) {
                if(wire1Segment.crosses(wire2Segment)) {
                    final var crossPoint = wire1Segment.crossPointWith(wire2Segment);
                    crossPoints.add(crossPoint);

                    var l1 = lengthExcluding(wire1, wire1Segment);
                    var l2 = lengthExcluding(wire2, wire2Segment);
                    var l3 = wire1Segment.lengthTo(crossPoint);
                    var l4 = wire2Segment.lengthTo(crossPoint);
                    var crosspointSteps = l1 + l2 + l3 + l4;
                    LOGGER.info(crossPoint+ " has "+crosspointSteps+" steps.");

                    if(crosspointSteps > 0 && crosspointSteps < minSteps) {
                        minSteps = crosspointSteps;
                    }
                }
            }
        }
        LOGGER.info(format("Wires have %d crosspoints.", crossPoints.size()));
        LOGGER.info(format("Minimal steps to an intersection is %d.", minSteps));

        int lowestManhattanDistance = Integer.MAX_VALUE;
        for (var crossPoint : crossPoints) {
            final var manhattanDistance = crossPoint.manhattanDistance();
            if(manhattanDistance > 0 && manhattanDistance < lowestManhattanDistance) {
                lowestManhattanDistance = manhattanDistance;
            }
        }

        return lowestManhattanDistance;
    }

    static int lengthExcluding(final List<LineSegment> wire, final LineSegment segment) {
        int length = 0;
        for (LineSegment lineSegment : wire) {
            if(lineSegment.equals(segment)) {
                return length;
            }

            length += lineSegment.length();
        }

        return length;
    }
}
