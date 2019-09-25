package tourist.taxcalculations

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

// Here we will see how to use extension functions and data classes

// Extension functions are recommended in the Kotlin Coding Conventions (https://kotlinlang.org/docs/reference/coding-conventions.html#source-code-organization)
private val Int.percent: BigDecimal
    get() {
        return this.toBigDecimal().divide(BigDecimal(100))
    }

// Now we can write 25% like this
val VAT = 25.percent


class Kroner private constructor(private val totalOerer: BigInteger) {

    val totalKroner: BigDecimal
        get() = this.totalOerer.toBigDecimal().movePointLeft(2)

    override fun hashCode(): Int = totalOerer.hashCode()
    override fun equals(other: Any?): Boolean =
        if (other != null && other is Kroner)
            totalOerer == other.totalOerer
        else false

    override fun toString(): String {
        return "Kroner(${this.totalKroner})"
    }

    companion object {
        fun fromKroner(amount: BigDecimal): Kroner {
            return Kroner(amount.setScale(2, RoundingMode.HALF_UP).movePointRight(2).toBigInteger())
        }

        fun fromKroner(amount: Int): Kroner {
            return Kroner(BigInteger.valueOf(amount.toLong()).multiply(BigInteger.valueOf(100)))
        }

        fun fromOerer(oerer: Int): Kroner {
            return Kroner(BigInteger.valueOf(oerer.toLong()))
        }
    }
}

fun vatFor(amount: Kroner) = Kroner.fromKroner(VAT * amount.totalKroner)

