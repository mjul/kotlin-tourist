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

## Unit Testing
Kotlin supports unit testing like any other JVM based language.
It also has a DSL, Kotlin Test, for more elegant test formulation.

### JUnit5
JUnit version 5 is supported. See for example [test for pattern matching](src/tourist-test/patternmatching/withdataclasses/MatchOnDataClassesKtTest.kt).

## LICENSE
MIT License. See [LICENSE](LICENSE) file for details.