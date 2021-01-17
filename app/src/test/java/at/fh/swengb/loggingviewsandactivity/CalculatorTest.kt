package at.fh.swengb.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class CalculatorTest{
    @Test
    fun testMultiply2By2(){
        val calculator = Calculator()
        val result = calculator.parse("2 * 2")
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun testDevide2By2(){
        val calculator = Calculator()
        val result = calculator.parse("2 / 2")
        assertThat(result).isEqualTo(1)
    }

    @Test
    fun testPlus2By2(){
        val calculator = Calculator()
        val result = calculator.parse("2 + 2")
        assertThat(result).isEqualTo(4)
    }

    @Test
    fun testSub2By2(){
        val calculator = Calculator()
        val result = calculator.parse("2 - 2")
        assertThat(result).isEqualTo(0)
    }

}
