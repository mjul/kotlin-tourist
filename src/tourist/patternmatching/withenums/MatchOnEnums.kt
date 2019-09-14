package tourist.patternmatching.withenums

// Pattern matching in Kotlin is done with the when function
enum class Shape {
    POINT,
    CIRCLE,
    TRIANGLE,
    RECTANGLE
}

fun stringifyShape(shape:Shape) =
    when(shape) {
        // when matches the first match only (unlike switch it does not fall through if break; is missing)
        // when on enums must be exhaustive or have a default
        // if you remove one of the matches below it will not compile
        // also, if you add another Shape to the enum it will not compile until you add a matching case
        Shape.POINT -> "point"
        Shape.CIRCLE -> "circle"
        Shape.TRIANGLE -> "triangle"
        Shape.RECTANGLE -> "rectangle"
    }

fun stringifyShapeWithDefault(shape: Shape) =
    when(shape) {
        // if you add an else case it does not have to be exhaustive
        Shape.CIRCLE -> "circle"
        else -> "unknown"
    }

