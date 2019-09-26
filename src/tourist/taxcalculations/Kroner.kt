package tourist.taxcalculations

import java.math.BigDecimal
import java.math.BigInteger
import java.math.RoundingMode

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

    operator fun plus(other: Kroner): Kroner =
        Kroner(this.totalOerer + other.totalOerer)

    companion object {
        fun fromKroner(amount: BigDecimal): Kroner {
            return Kroner(
                amount.setScale(
                    2,
                    RoundingMode.HALF_UP
                ).movePointRight(2).toBigInteger()
            )
        }

        fun fromKroner(amount: Int): Kroner {
            return Kroner(
                BigInteger.valueOf(amount.toLong()).multiply(
                    BigInteger.valueOf(
                        100
                    )
                )
            )
        }

        fun fromOerer(oerer: Int): Kroner {
            return Kroner(BigInteger.valueOf(oerer.toLong()))
        }
    }
}