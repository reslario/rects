import com.google.gson.annotations.SerializedName;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Rectangle {
    private int x, y;

    @SerializedName("w")
    private int width;

    @SerializedName("h")
    private int height;

    public Rectangle() {
        x = y = width = height = 0;
    }

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    private Rectangle(Point topLeft, Point botRight) {
        x = topLeft.getX();
        y = topLeft.getY();
        width = botRight.getX() - x;
        height = botRight.getY() - y;
    }

    public Optional<Rectangle> intersection(Rectangle other) {
        Point isectTopLeft = topLeft().max(other.topLeft());
        Point isectBotRight = bottomRight().min(other.bottomRight());

        if (isectTopLeft.getX() > isectBotRight.getX()
            || isectTopLeft.getY() > isectBotRight.getY())
        {
            return Optional.empty();
        }

        return Optional.of(
            new Rectangle(isectTopLeft, isectBotRight)
        );
    }

    private boolean contains(Point point) {
        Point topLeft = topLeft();
        Point botRight = bottomRight();

        return topLeft.getX() < point.getX()
            && topLeft.getY() < point.getY()
            && botRight.getX() > point.getX()
            && botRight.getY() > point.getY();
    }

    private Stream<Point> points() {
        return Stream.of(
            topLeft(), topRight(), bottomLeft(), bottomRight()
        );
    }

    private Point topLeft() {
        return new Point(x, y);
    }

    private Point topRight() {
        return new Point(x + width, y);
    }

    private Point bottomLeft() {
        return new Point(x, y + height);
    }

    private Point bottomRight() {
        return new Point(x + width, y + height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return x == rectangle.x &&
            y == rectangle.y &&
            width == rectangle.width &&
            height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
            "x=" + x +
            ", y=" + y +
            ", width=" + width +
            ", height=" + height +
            '}';
    }
}
