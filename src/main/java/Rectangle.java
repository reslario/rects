import com.google.gson.annotations.SerializedName;
import util.Streams.Indexed;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static util.Streams.enumerate;

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

    public static Set<Intersection> intersections(List<Rectangle> rects) {
        Stream<Intersection> intersections = enumerate(rects.stream())
            .flatMap(rect -> enumerate(rects.stream())
                .filter(rect2 -> rect.getIndex() != rect2.getIndex())
                .map(rect2 -> indexedIntersection(rect, rect2))
                .filter(Optional::isPresent)
                .map(Optional::get)
            );

        return subIntersections(intersections);
    }

    private static Optional<Intersection> indexedIntersection(Indexed<Rectangle> rect1, Indexed<Rectangle> rect2) {
        return rect1
            .getValue()
            .intersection(rect2.getValue())
            .map(isect -> new Intersection(
                isect, new HashSet<>(Arrays.asList(rect1.getIndex(), rect2.getIndex()))
            ));
    }

    private static Set<Intersection> subIntersections(Stream<Intersection> intersections) {
        Set<Intersection> newIsects = intersections.collect(Collectors.toSet());
        Set<Intersection> subIsects = subIntersectionsOf(newIsects);

        // keep finding sub-intersections until none are left
        while (!subIsects.isEmpty()) {
            newIsects.addAll(subIsects);
            subIsects = subIntersectionsOf(subIsects);
        }

        return newIsects;
    }

    private static Set<Intersection> subIntersectionsOf(Set<Intersection> intersections) {
        return intersections
            .stream()
            .flatMap(isect -> intersections
                .stream()
                .filter(isect2 -> isect != isect2)
                .map(isect::and)
                .filter(Optional::isPresent)
                .map(Optional::get)
            ).collect(Collectors.toSet());
    }

    private Point topLeft() {
        return new Point(x, y);
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
