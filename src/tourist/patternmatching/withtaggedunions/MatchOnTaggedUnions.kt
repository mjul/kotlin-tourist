package tourist.patternmatching.withtaggedunions

// Since enums are singletons we cannot add instance data to them; however, the compile can check match expressions
// to verify that we have considered all cases without putting in a default (else) case.
// Therefore we try modelling the type of as an enum "Tag" and the instance data as additional data on a parallel class hierarchy.
// This allows us to have a point, match on the Point tag yet also store its coordinates in the instance.
// The disadvantage is that we now have to parallel hierarchies of tags and data classes to maintain.

// As you will see below, this approach is clumsy

/**
 * Shape tags represents the tags for the various shapes.
 */
enum class ShapeTag {
    Point,
    Circle
}

/**
 * Represents a tagged Shape.
 * */
interface Shape {
    /** Get the tag for this Shape. */
    val tag: ShapeTag
}

/** A Point */
data class Point(val x: Int, val y: Int) : Shape {
    override val tag: ShapeTag
        get() = ShapeTag.Point
}

/** A Circle */
data class Circle(val centre: Point, val radius: Int) : Shape {
    override val tag: ShapeTag
        get() = ShapeTag.Circle
}


fun stringifyShape(shape: Shape) : String {
    // Here the compiler will recommend the when to have exhaustive cases
    // But we also have to do some casting that would be nice to be without
    when (shape.tag) {
        ShapeTag.Point -> {
            val pt = shape as Point
            return "Point at (${pt.x},${pt.y})"
        }
        ShapeTag.Circle -> {
            val c = shape as Circle
            return "Circle at (${c.centre.x},${c.centre.y}) with radius ${c.radius}"
        }
    }
}
