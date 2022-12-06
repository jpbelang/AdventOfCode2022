package advent2022.day1

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.StringReader

class CalorieReaderTest {

    @Test
    fun readOneBunch() {

        val stringReader = StringReader(listOf("10", "20").joinToString("\r\n"))
        val cr = CalorieReader(stringReader)

        assertThat(cr.fetch()).isNotNull.contains(10, 20)
    }

    @Test
    fun readTwoBunches() {

        val stringReader = StringReader(listOf("10", "20", "", "20", "30").joinToString("\r\n"))
        val cr = CalorieReader(stringReader)

        assertThat(cr.fetch()).isNotNull().contains(10, 20)
        assertThat(cr.fetch()).isNotNull().contains(20, 30)
    }

    @Test
    fun bunchCouldBeNull() {

        val stringReader = StringReader("")
        val cr = CalorieReader(stringReader)
        val actual = cr.fetch()
        assertThat(actual).isNull()
    }

    @Test
    fun asSequence() {

        val stringReader = StringReader(listOf("10", "20", "", "20", "30").joinToString("\r\n"))
        val cr = CalorieReader(stringReader)

        assertThat(cr.asSequence().take(2).toList()).hasSize(2)
    }

    @Test
    fun asNullEndingSequence() {

        val stringReader = StringReader(listOf("10", "20", "", "20", "30").joinToString("\r\n"))
        val cr = CalorieReader(stringReader)

        assertThat(cr.asSequence().toList()).hasSize(2)
    }


}