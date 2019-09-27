package tourist.patternmatching.withvisitors

// The Gang of Four Pattern matching mechanism is using the visitor pattern
// It breaks the open-closed principle, but for exhaustive matching that is what we want


// In Kotlin, a "sealed" class shows the compiler that there are no other subclasses
// So, the Shape subclasses here are essentially like a closed set of enums.

sealed class Shape {
    abstract fun accept(visitor:ShapeVisitor)

    data class Point(val x: Int, val y: Int) : Shape() {
        override fun accept(visitor: ShapeVisitor) = visitor.visit(this)
    }

    data class Circle(val centre: Point, val r: Int) : Shape() {
        override fun accept(visitor: ShapeVisitor) = visitor.visit(this)
    }

    data class Triangle(val a: Point, val b: Point, val c: Point) : Shape() {
        override fun accept(visitor: ShapeVisitor) = visitor.visit(this)
    }
}

interface ShapeVisitor {
    fun visit(point: Shape.Point)
    fun visit(circle:Shape.Circle)
    fun visit(triangle:Shape.Triangle)
}

fun formatPoint(point: Shape.Point) =
    "(${point.x},${point.y})"

class ShapeStringifier(var result : String) : ShapeVisitor {
    override fun visit(point: Shape.Point) {
        result = "Point at ${formatPoint(point)}"
    }
    override fun visit(circle: Shape.Circle) {
        result = "Circle at ${formatPoint(circle.centre)} with radius ${circle.r}"
    }
    override fun visit(triangle: Shape.Triangle) {
        val (a, b, c) = triangle
        result = "Triangle with corners ${formatPoint(a)}, ${formatPoint(b)} and ${formatPoint(c)}"
    }
}

fun stringifyShape(shape:Shape) : String {
    val visitor = ShapeStringifier("Unknown shape")
    shape.accept(visitor)
    return visitor.result
}

// As you can see, this is really verbose, so thankfully there are other ways to do this in Kotlin