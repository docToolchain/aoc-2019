import java.util.Objects;

class LineSegment {
    private final Coordinate start;
    private final Coordinate end;
    private final boolean horizontal;
    private final boolean vertical;

    LineSegment(final Coordinate start, final Coordinate end) {
        this.start = start;
        this.end = end;
        horizontal = start.horizontalWith(end);
        vertical = start.verticalWith(end);
    }

    boolean crosses(LineSegment other) {
        return
                start.horizontallyBetween(other.start, other.end) &&
                other.start.verticallyBetween(this.start, this.end)
                ||
                start.verticallyBetween(other.start, other.end) &&
                other.start.horizontallyBetween(this.start, this.end);
    }

    public Coordinate crossPointWith(final LineSegment other) {
        if (!crosses(other)) throw new IllegalArgumentException(other + " does not cross " + this);

        int x = horizontal ? other.x() : x();
        int y = vertical ? other.y() : y();
        return new Coordinate(x, y);
    }

    private int y() {
        return start.getY();
    }

    private int x() {
        return start.getX();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final LineSegment that = (LineSegment) o;
        return Objects.equals(start, that.start) &&
               Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "LineSegment{" +
               "start=" + start +
               ", end=" + end +
               '}';
    }

    public int length() {
        return start.lengthTo(end);
    }

    public int lengthTo(final Coordinate point) {
        return new LineSegment(start, point).length();
    }
}
