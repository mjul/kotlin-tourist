package taxcalculations

import io.kotlintest.shouldBe
import io.kotlintest.specs.FunSpec
import tourist.taxcalculations.Kroner

// Try testing Kroner using KotlinTest, an open-source DSL for Kotlin unit testing
// https://github.com/kotlintest/kotlintest

// KotlinTest offers many styles of test specifications


// Try using the FunSpec variant
class KronerKotlinTestFunSpec : FunSpec({
    test("Kroner .totalOerer should be a 100 times .totalKroner for integer kroner amounts") {
        val kr = 2
        val sut = Kroner.fromKroner(kr)
        // BigDecimal is a bit special because equals does not compare the value but also the representation
        // compareTo is the one we want
        sut.totalOerer.toBigDecimal().compareTo(sut.totalKroner*(100.toBigDecimal())) shouldBe 0
    }
})