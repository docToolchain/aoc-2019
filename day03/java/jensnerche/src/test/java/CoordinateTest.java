import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void thatManhattanDistanceIsCalculated() {
        var point = new Coordinate(2, 3);

        assertEquals(5, point.manhattanDistance());
    }
}
