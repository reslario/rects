import geometry.Intersection;
import geometry.Rectangle;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class RectangleTest {

    @Test
    public void intersectionConstellation1() {
        // +-------------+ <- 1
        // |             |
        // |      +------|-----+
        // |      |      |     |
        // +------|------+     |
        //        |            |
        //        +------------+ <- 2

        Rectangle rect1 = new Rectangle(0, 0, 100, 100);
        Rectangle rect2 = new Rectangle(50, 50, 100, 100);
        Rectangle expected = new Rectangle(50, 50, 50, 50);

        assertEquals(expected, rect1.intersection(rect2).get());
    }

    @Test
    public void intersectionConstellation2() {
        //        +------------+ <- 1
        //        |            |
        // +------|------+     |
        // |      |      |     |
        // |      +------|-----+
        // |             |
        // +-------------+ <- 2

        Rectangle rect1 = new Rectangle(50, 0, 100, 100);
        Rectangle rect2 = new Rectangle(0, 50, 100, 100);
        Rectangle expected = new Rectangle(50, 50, 50, 50);

        assertEquals(expected, rect1.intersection(rect2).get());
    }

    @Test
    public void intersectionConstellation3() {
        // +------------------------+ <- 2
        // |                        |
        // |      +----------+ <- 1 |
        // |      |          |      |
        // |      |          |      |
        // |      |          |      |
        // |      +----------+      |
        // |                        |
        // +------------------------+

        Rectangle rect1 = new Rectangle(50, 50, 50, 50);
        Rectangle rect2 = new Rectangle(0, 0, 100, 100);
        Rectangle expected = new Rectangle(50, 50, 50, 50);

        assertEquals(expected, rect1.intersection(rect2).get());
    }

    @Test
    public void intersectionConstellation4() {
        // +----------+ <- 1
        // |          |
        // |          |     +----------+ <- 2
        // |          |     |          |
        // +----------+     |          |
        //                  |          |
        //                  +----------+

        Rectangle rect1 = new Rectangle(0, 0, 100, 100);
        Rectangle rect2 = new Rectangle(125, 50, 100, 100);
        Optional<Rectangle> expected = Optional.empty();

        assertEquals(expected, rect1.intersection(rect2));
    }

    @Test
    public void intersections() {
        // +------------------------------------------------------+ <- 1
        // |                                                      |
        // |                                                      |
        // |                                                      |
        // |                  +-----------------------------------|----------------------------+ <- 4
        // |                  |                                   |                            |
        // |            +-----|-----------------------------------|--------+ <- 3              |
        // +------------|-----------------------------------------+        |                   |
        //              |     |                                            |                   |
        //       +----- |-----|---------------------------------------+    |                   |
        //       |      |     |                                  2 -> |    |                   |
        //       |      |     |                                       |    |                   |
        //       |      |     |                                       |    |                   |
        //       |      +--------------------------------------------------+                   |
        //       |            |                                       |                        |
        //       |            |                                       |                        |
        //       |            |                                       |                        |
        //       |            |                                       |                        |
        //       |            +----------------------------------------------------------------+
        //       |                                                    |
        //       +----------------------------------------------------+

        List<Rectangle> rects = Arrays.asList(
            new Rectangle(100, 100, 250, 80),
            new Rectangle(120, 200, 250, 150),
            new Rectangle(140, 160, 250, 100),
            new Rectangle(160, 140, 350, 190)
        );

        Set<Intersection> expected = new HashSet<>(Arrays.asList(
            new Intersection(
                new Rectangle(140, 160, 210, 20),
                new HashSet<>(Arrays.asList(0L, 2L))
            ),
            new Intersection(
                new Rectangle(160, 140, 190, 40),
                new HashSet<>(Arrays.asList(0L, 3L))
            ),
            new Intersection(
                new Rectangle(140, 200, 230, 60),
                new HashSet<>(Arrays.asList(1L, 2L))
            ),
            new Intersection(
                new Rectangle(160, 200, 210, 130),
                new HashSet<>(Arrays.asList(1L, 3L))
            ),
            new Intersection(
                new Rectangle(160, 160, 230, 100),
                new HashSet<>(Arrays.asList(2L, 3L))
            ),
            new Intersection(
                new Rectangle(160, 160, 190, 20),
                new HashSet<>(Arrays.asList(0L, 2L, 3L))
            ),
            new Intersection(
                new Rectangle(160, 200, 210, 60),
                new HashSet<>(Arrays.asList(1L, 2L, 3L))
            )
        ));

        assertEquals(expected, Rectangle.intersections(rects));
    }
}