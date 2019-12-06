import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputParser {
    @Test
    void thatWirePathParserProducesLineSegments() {
        WirePathParser parser = new WirePathParser();
        final List<LineSegment> expectedLineSegments = List.of(
                new LineSegment(new Coordinate(0, 0), new Coordinate(8,0)),
                new LineSegment(new Coordinate(8, 0), new Coordinate(8,5)),
                new LineSegment(new Coordinate(8, 5), new Coordinate(3,5)),
                new LineSegment(new Coordinate(3, 5), new Coordinate(3,2))
        );

        List<LineSegment> lineSegments = parser.parse("R8,U5,L5,D3");

        assertEquals(4, lineSegments.size(), "Parsing four waypoints should produce four line segments.");
        assertEquals(expectedLineSegments, lineSegments, "Line segments are somewhat unexpected, hum?");
    }
}
