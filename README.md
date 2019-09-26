# Kotlin Tourist
Learning Kotlin by taking a tour.

## Hello, World
See [app.kt](src/tourist/app.kt) for the canonical example.

## Pattern Matching
Kotlin does not have pattern matching as we know it from the `match x with ...` expression in F# or OCaml which combines pattern matching and destructuring bindings.

It does have something similar in the form of the `when` expression.
See pattern matching examples:
- with [enums](src/tourist/patternmatching/withenums/MatchOnEnums.kt)
- with [classes](src/tourist/patternmatching/withdataclasses/MatchOnDataClasses.kt)
- with [tagged unions](src/tourist/patternmatching/withtaggedunions/MatchOnTaggedUnions.kt) 

## Extension Functions
Idiomatic Kotlin uses _extension functions_ a lot. 
It is used to extend the enemy's code (including your own), or for convenient notation.
For example you can extend Int so you can write `25.percent` instead of `.25`.

See examples in the [Tax Calculator](src/tourist/taxcalculations/TaxCalculations.kt).

## Operator Overloading
This is another thing borrowed from C#. It is convenient for some types, see examples in the
[Kroner class](src/tourist/taxcalculations/Kroner.kt).

### Classes
Kotlin classes can be defined easily as `data` classes with value semantics `data class Foo(a: Int)`.
You can also use the primary constructor syntax to easily define fields and trivial constructors for initializing them.
See examples in the [Kroner class](src/tourist/taxcalculations/Kroner.kt).

## Unit Testing
Kotlin supports unit testing like any other JVM based language.
You can use the Java unit test libraries directly or or use `kotlin.test` from 
the Kotlin core library.

### JUnit5
JUnit version 5 is supported. See for example [test for pattern matching](src/tourist-test/patternmatching/withdataclasses/MatchOnDataClassesKtTest.kt).

### kotlin.test
This uses the `kotlin.test` core library.
See for example [test for tax calculation](src/tourist-test/taxcalculations/TaxCalculationsKtTest.kt)

## LICENSE
MIT License. See [LICENSE](LICENSE) file for details.