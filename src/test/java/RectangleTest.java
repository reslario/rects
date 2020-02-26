import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

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
}