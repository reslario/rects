package geometry;

import java.util.Objects;
import java.util.function.IntBinaryOperator;

public class Point {
    private int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point min(Point other) {
        return extreme(other, Math::min);
    }

    public Point max(Point other) {
        return extreme(other, Math::max);
    }


    private <C extends IntBinaryOperator> Point extreme(Point other, C comp) {
        return new Point(
            comp.applyAsInt(x, other.x),
            comp.applyAsInt(y, other.y)
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
            y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
