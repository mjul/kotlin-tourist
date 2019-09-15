package patternmatching.withtaggedunions

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import tourist.patternmatching.withtaggedunions.*

internal class MatchOnTaggedUnionsKtTest {
    @Test
    fun shapesHaveCorrespondingTags() {
        assertEquals(ShapeTag.Point, Point(0,0).tag)
        assertEquals(ShapeTag.Circle, Circle(Point(0,0),1).tag)
    }

    @Test
    fun stringifyShapeTest() {
        assertEquals("Point at (1,0)", stringifyShape(Point(1,0)))
        assertEquals("Circle at (1,2) with radius 3", stringifyShape(Circle(Point(1,2), 3)))
    }
}