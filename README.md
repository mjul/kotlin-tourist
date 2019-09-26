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

There is also a more extensive open-source test DSL called [KotlinTest](https://github.com/kotlintest/kotlintest)

### JUnit5
JUnit version 5 is supported. See for example [test for pattern matching](src/tourist-test/patternmatching/withdataclasses/MatchOnDataClassesKtTest.kt).

### kotlin.test
This uses the `kotlin.test` core library.
See for example [test for tax calculation](src/tourist-test/taxcalculations/TaxCalculationsKtTest.kt)

### KotlinTest
[KotlinTest](https://github.com/kotlintest/kotlintest) is an extensive open-source test DSL for Kotlin 
with a lot of matchers. It is very similar to [ScalaTest](http://www.scalatest.org/) for Scala.

Note that to avoid the warning from the log framework, SLF4J, every time we run these tests, 
we need to include a log binder for the simple log framework:

    SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
    SLF4J: Defaulting to no-operation (NOP) logger implementation
    SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

To avoid this the project has a dependency to `org.slf4j:slf4j-simple:1.7.2`

#### Property-Based Testing
KotlinTest also supports property-based testing. This is similar to QuickCheck in Haskell.

Property-based testign allows you to specify the invariants ("properties") of the code and have the test framework
check them across a lot of examples without writing a lot of code.

You can write custom generators for generating instances of your own types, 
see [KronerKotlinTest.kt](src/tourist-test/taxcalculations/KronerKotlinTest.kt)

## LICENSE
MIT License. See [LICENSE](LICENSE) file for details.