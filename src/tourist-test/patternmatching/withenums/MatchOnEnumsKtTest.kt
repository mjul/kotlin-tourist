package patternmatching.withenums

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import tourist.patternmatching.withenums.Shape
import tourist.patternmatching.withenums.stringifyShape
import tourist.patternmatching.withenums.stringifyShapeWithDefault

internal class MatchOnEnumsKtTest {

    @Test
    fun stringifyShapeTest() {
        // Shape.values() or enumValues<Shape>() iterates over all values of the Shape enum
        for (shape in Shape.values())
        {
            val actual = stringifyShape(shape)
            assert(actual.isNotEmpty())
        }
    }

    @Test
    fun stringifyShapeWithDefaultTest() {
        for (shape in enumValues<Shape>())
        {
            val actual = stringifyShapeWithDefault(shape)
            assert(actual.isNotEmpty())
        }
    }
}