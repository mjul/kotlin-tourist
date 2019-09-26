package tourist.taxcalculations

import java.math.BigDecimal


// Extension functions are recommended in the Kotlin Coding Conventions
// (https://kotlinlang.org/docs/reference/coding-conventions.html#source-code-organization)
private val Int.percent: BigDecimal
    get() {
        return this.toBigDecimal().divide(BigDecimal(100))
    }

// Now we can write 25% like this
val VAT = 25.percent


fun vatFor(amount: Kroner) = Kroner.fromKroner(VAT * amount.totalKroner)

