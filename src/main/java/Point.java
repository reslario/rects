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


}
