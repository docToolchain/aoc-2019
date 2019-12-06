import java.util.Objects;

class Coordinate {
    private final int x;
    private final int y;
    private final int manhattanDistance;

    Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.manhattanDistance = Math.abs(x) + Math.abs(y);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Coordinate that = (Coordinate) o;
        return x == that.x &&
               y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }

    public boolean horizontallyBetween(final Coordinate start, final Coordinate end) {
        return start.x <= x && x <= end.x
               ||
               end.x <= x && x <= start.x
                ;
    }

    public boolean verticallyBetween(final Coordinate start, final Coordinate end) {
        return start.y <= y && y <= end.y
               ||
               end.y <= y && y <= start.y
                ;
    }

    public boolean horizontalWith(final Coordinate other) {
        return y == other.y;
    }

    public boolean verticalWith(final Coordinate other) {
        return x == other.x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int manhattanDistance() {
        return manhattanDistance;
    }

    public int lengthTo(final Coordinate other) {
        if(x == other.x) {
            return Math.abs(y - other.y);
        } else {
            return Math.abs(x - other.x);
        }
    }
}
