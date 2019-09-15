package tourist.patternmatching.withdataclasses


abstract class Shape

data class Point(val x: Int, val y: Int) : Shape()
data class Circle(val centre: Point, val r: Int) : Shape()
data class Triangle(val a: Point, val b: Point, val c: Point) : Shape()

fun formatPoint(point: Point) =
    "(${point.x},${point.y})"

fun describeShape(shape: Shape) =
    when (shape) {
        is Point -> "Point at ${formatPoint(shape)}"
        is Circle -> "Circle at ${formatPoint(shape.centre)} with radius ${shape.r}"
        is Triangle -> {
            val (a, b, c) = shape
            "Triangle with corners ${formatPoint(a)}, ${formatPoint(b)} and ${formatPoint(c)}"
        }
        // the compiler does not know that the above is exhaustive so we need to add an else clause
        else -> "Unknown shape"
    }


