package taxcalculations

import io.kotlintest.properties.Gen
import io.kotlintest.properties.assertAll
import io.kotlintest.properties.forAll
import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.kotlintest.specs.StringSpec
import org.junit.jupiter.api.assertAll
import tourist.taxcalculations.Kroner
import java.math.BigDecimal

// Try testing Kroner using KotlinTest, an open-source DSL for Kotlin unit testing
// https://github.com/kotlintest/kotlintest

// KotlinTest offers many styles of test specifications
// See https://github.com/kotlintest/kotlintest/blob/master/doc/styles.md

// Here is an example of the StringSpec style:

class KronerKotlinTestStringSpec : StringSpec(
    {
        "Kroner .totalOerer and .totalKroner must represent the same amount" {
            val sut = Kroner.fromOerer(108)
            sut.totalOerer shouldBe 108.toBigInteger()
            sut.totalKroner shouldBe 1.08.toBigDecimal()
        }
    }
)

// Try using the FunSpec variant
class KronerKotlinTestFunSpec : FunSpec({
    test("Kroner .totalOerer should be a 100 times .totalKroner for integer kroner amounts") {
        val kr = 2
        val sut = Kroner.fromKroner(kr)
        // BigDecimal is a bit special because equals does not compare the value but also the representation
        // compareTo is the one we want
        sut.totalOerer.toBigDecimal().compareTo(sut.totalKroner * (100.toBigDecimal())) shouldBe 0
    }
})

// We can also do property-based testing
class KronerSumOverloadStringSpec : StringSpec() {
    init {
        "sum must match" {
            assertAll { a: Long, b: Long ->
                (Kroner.fromKroner(a.toBigDecimal()) + Kroner.fromKroner(b.toBigDecimal())) shouldBe Kroner.fromKroner(a.toBigDecimal() + b.toBigDecimal())
            }
        }
    }
}

// Now to make things cleaner, we can add a Generator for Kroner
// This is the mechanism for generating the different values for the
// property-based testing
class KronerGenerator : Gen<Kroner> {
    // Typical edge cases
    override fun constants() = listOf<Kroner>(
        Kroner.fromKroner(0),
        Kroner.fromKroner(1)
    )
    // generate a random sequence of Kroner instances
    override fun random(): Sequence<Kroner>  = generateSequence {
        Kroner.fromKroner(Gen.bigInteger().random().first().toBigDecimal().movePointLeft(2))
    }
}
private fun Gen.Companion.kroner(): Gen<Kroner> = KronerGenerator()

// Now we can implement it again with a Generator
class KronerSumOverloadWithCustomGeneratorStringSpec : StringSpec() {
    init {
        "sum must match" {
            forAll(Gen.kroner(), Gen.kroner()) { a: Kroner, b: Kroner ->
                a+b == Kroner.fromKroner(a.totalKroner + b.totalKroner)
            }
        }
    }
}
