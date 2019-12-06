import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

class WirePathParser {
    public List<LineSegment> parse(final String input) {
        List<LineSegment> lineSegments = new ArrayList<>();

        int currentX = 0;
        int currentY = 0;

        for (String part : input.split(",")) {
            final Coordinate start = new Coordinate(currentX, currentY);
            int steps = parseInt(part.substring(1));
            char direction = part.charAt(0);
            switch (direction) {
                case 'U' -> currentY += steps;
                case 'D' -> currentY -= steps;
                case 'L' -> currentX -= steps;
                case 'R' -> currentX += steps;
                default -> throw new RuntimeException("Unknown direction: " + part);
            }
            lineSegments.add(new LineSegment(start, new Coordinate(currentX, currentY)));
        }

        return lineSegments;
    }
}
