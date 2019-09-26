package taxcalculations

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import io.kotlintest.specs.StringSpec
import tourist.taxcalculations.Kroner

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
