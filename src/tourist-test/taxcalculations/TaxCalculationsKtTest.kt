package taxcalculations

import java.math.BigDecimal
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import kotlin.test.*

import tourist.taxcalculations.Kroner
import tourist.taxcalculations.vatFor

internal class KronerKtTest {

    private val oerer100 = Kroner.fromOerer(100)
    private val oerer101 = Kroner.fromOerer(101)

    @Test
    fun equalsAndHashCode() {
        val a = Kroner.fromOerer(100)
        val b = Kroner.fromOerer(100)
        val c = Kroner.fromOerer(100)

        val otherOerer = Kroner.fromOerer(101)

        // reflexive
        assertTrue(a == a)
        assertEquals(a, a)
        assertEquals(oerer100, oerer100)
        assertEquals(oerer100, Kroner.fromOerer(100))

        // symmetric
        assertEquals(a, b)
        assertEquals(b, a)

        // transitive
        assertEquals(a, b)
        assertEquals(b, c)
        assertEquals(a, c)

        // Not equal to different amount
        assertFalse(a == otherOerer)
        assertNotEquals(a, otherOerer)

        // hash code must be equal for equal instances
        assertEquals(a.hashCode(), b.hashCode())
        assertEquals(a.hashCode(), c.hashCode())
    }

    @Test
    fun toStringShouldShowClassAndAmount() {
        assertEquals("Kroner(1.05)", Kroner.fromOerer(105).toString())
        assertEquals("Kroner(1.00)", Kroner.fromOerer(100).toString())
        assertEquals("Kroner(1.00)", Kroner.fromKroner(1).toString())
    }

    @Test
    fun fromOererWithIntegerMustConstructKroner() {
        assertEquals(Kroner.fromKroner(1), Kroner.fromOerer(100))
    }

    @Test
    fun fromKronerWithIntegerMustConstructKroner() {
        assertEquals(oerer100, Kroner.fromKroner(1))
    }

    @Test
    fun fromKronerWithBigDecimalMustConstructKroner() {
        assertEquals(oerer100, Kroner.fromKroner(BigDecimal.valueOf(100, 2)))
        assertEquals(oerer101, Kroner.fromKroner(BigDecimal.valueOf(101, 2)))
    }

    @TestFactory
    fun fromKronerWithBigDecimalMustRoundToTwoDecimals() =
        listOf(
            BigDecimal.valueOf(1.004) to oerer100,
            BigDecimal.valueOf(1.0049) to oerer100,
            BigDecimal.valueOf(1.005) to oerer101,
            BigDecimal.valueOf(1.01) to oerer101
        ).map { (input, expected) ->
            DynamicTest.dynamicTest("when rounding $input kr. we should get $expected") {
                assertEquals(expected, Kroner.fromKroner(input))
            }
        }

    @Test
    fun kronerCanBeAddedUsingPlusOperator() {
        val actual = Kroner.fromKroner(1) + Kroner.fromKroner(2)
        assertEquals(Kroner.fromKroner(3), actual)
    }
}


internal class TaxCalculationsKtTest {

    @Test
    fun vatForShouldCalculateVat() {
        assertEquals(Kroner.fromOerer(25), vatFor(Kroner.fromKroner(1)))
    }
}