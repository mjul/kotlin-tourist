package patternmatching.withdataclasses

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tourist.patternmatching.withdataclasses.*

internal class MatchOnDataClassesKtTest {

    @Test
    fun formatPoint_shouldFormatAsTuple() {
        val actual = formatPoint(Point(0, 0))
        assertEquals("(0,0)", actual)
    }

    @Test
    fun describeShape_shouldStringifyKnownShapes() {
        assertEquals("Point at (1,0)", describeShape(Point(1, 0)))
        assertEquals("Circle at (1,2) with radius 3", describeShape(Circle(Point(1, 2), 3)))
        assertEquals("Triangle with corners (0,0), (1,0) and (0,1)",
            describeShape(Triangle(Point(0, 0), Point(1,0), Point(0,1))))
    }
}