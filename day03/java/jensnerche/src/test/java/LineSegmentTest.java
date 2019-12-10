import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LineSegmentTest {

    @Test
    void thatCrossingIsDetected() {
        var segment1 = new LineSegment(new Coordinate(3, 2), new Coordinate(3, 5));
        var segemnt2 = new LineSegment(new Coordinate(2, 3), new Coordinate(6, 3));

        assertTrue(segment1.crosses(segemnt2));
        assertTrue(segemnt2.crosses(segment1));
    }

    @Test
    void thatNoCrossingIsNotDetected() {
        var segment1 = new LineSegment(new Coordinate(0, 0), new Coordinate(0, 8));
        var segemnt2 = new LineSegment(new Coordinate(2, 3), new Coordinate(6, 3));

        assertFalse(segment1.crosses(segemnt2));
        assertFalse(segemnt2.crosses(segment1));
    }

    @Test
    void thatCrossPointIsCalculated() {
        var segment1 = new LineSegment(new Coordinate(3, 2), new Coordinate(3, 5));
        var segemnt2 = new LineSegment(new Coordinate(2, 3), new Coordinate(6, 3));

        var crossPoint = segment1.crossPointWith(segemnt2);

        assertEquals(new Coordinate(3, 3), crossPoint);
    }

    @Test
    void thatLengthIsCorrectForHorizontalSegment() {
        var segment = new LineSegment(new Coordinate(2, 3), new Coordinate(6, 3));

        assertEquals(4, segment.length());
    }

    @Test
    void thatLengthIsCorrectForVerticalSegment() {
        var segment = new LineSegment(new Coordinate(3, 5), new Coordinate(3, 2));

        assertEquals(3, segment.length());
    }
}
