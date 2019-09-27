package patternmatching.withvisitors

import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import tourist.patternmatching.withvisitors.*

class StringifyShapeStringSpec : StringSpec({
    "point" {
        stringifyShape(Shape.Point(1, 0)) shouldBe "Point at (1,0)"
    }
    "circle" {
        stringifyShape(Shape.Circle(Shape.Point(1, 2), 3)) shouldBe "Circle at (1,2) with radius 3"
    }
    "triangle" {
        stringifyShape(
            Shape.Triangle(
                Shape.Point(0, 0),
                Shape.Point(1, 0),
                Shape.Point(0, 1)
            )
        ) shouldBe "Triangle with corners (0,0), (1,0) and (0,1)"
    }
})
