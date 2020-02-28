import com.google.gson.Gson;
import geometry.Intersection;
import geometry.Rectangle;
import json.Rectangles;
import util.Streams;

import java.io.FileReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static util.Streams.enumerate;

public class Main {
    public static void main(String[] args) {
        Optional<String> optPath = getPathArg(args);
        if (!optPath.isPresent()) {
            System.out.println("Usage: rects FILE");
            return;
        }
        String path = optPath.get();

        Optional<List<Rectangle>> optRects = readRectangles(path);
        if (!optRects.isPresent()) {
            System.out.println("Failed to read json file " + path);
            return;
        }
        List<Rectangle> rects = optRects.get();

        printInput(rects);
        Set<Intersection> intersections = Rectangle.intersections(rects);
        printOutput(intersections);
    }

    private static Optional<String> getPathArg(String[] args) {
        return Arrays.stream(args)
            .findFirst();
    }

    private static Optional<List<Rectangle>> readRectangles(String path) {
        try {
            Reader jsonReader = new FileReader(path);
            return Optional.of(
                new Gson()
                    .fromJson(jsonReader, Rectangles.class)
                    .get()
            );
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private static void printInput(List<Rectangle> rects) {
        System.out.println("Input:");
        enumerate(rects.stream())
            .forEach(Main::printIndexed);
    }

    private static void printOutput(Set<Intersection> isects) {
        System.out.println("Intersections:");
        enumerate(isects
            .stream()
            .sorted(Main::sortByMembers)
            .sorted(Main::sortByNumMembers)
        ).forEach(Main::printIndexed);
    }

    private static <T> void printIndexed(Streams.Indexed<T> item) {
        System.out.println(
            "\t" + item.getIndex() + ": " + item.getValue()
        );
    }

    private static int sortByNumMembers(Intersection i1, Intersection i2) {
        return i1.getMembers().size()
            - i2.getMembers().size();
    }

    private static int sortByMembers(Intersection i1, Intersection i2) {
        return (int) i1.getMembers()
            .stream()
            .mapToLong(l -> l)
            .sum()
            - (int) i2.getMembers()
                .stream()
                .mapToLong(l -> l)
                .sum();
    }
}
