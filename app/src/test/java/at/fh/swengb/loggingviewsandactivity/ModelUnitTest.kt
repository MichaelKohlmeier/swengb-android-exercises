package at.fh.swengb.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

class ModelUnitTest {
    @Test
    fun averageForEmptyRates_isCorrect() {
        // test whether the average is 0.0 when ratings are empty
        val lesson = Lesson(
            "4711",
            "test Lesson",
            "25.11.2020",
            "testing",
            LessonType.PRACTICAL,
            listOf(Lecturer("Tester")),
            mutableListOf(),
            "test"
        )
        val rating: Double = lesson.ratingAverage()


// using google's truth library
        //assertThat(rating).isWithin(1.0e-10).of(0.0)
        assertThat(rating).isWithin(1.0e-10).of(0.0)
// or using JUnit's assertEquals
        assertEquals(rating, 0.0, 1.0e-10)
    }
    @Test
    fun averageForNonEmptyRates_isCorrect() {
        // check whether the average is correct for a non-empty list of ratings
        val testRatings :List<LessonRating> = (1..10).map { LessonRating(it.toDouble(), "Rating ${it}") }
        val lesson = Lesson("4711",
            "test Lesson",
            "25.11.2020",
            "testing",
            LessonType.PRACTICAL,
            mutableListOf(),
            testRatings.toMutableList(),
            "test"
        )
        val rating :Double = lesson.ratingAverage()
        val correctRating :Double = testRatings.map {it.ratingValue}.average();

        assertThat(rating).isWithin(1.0e-10).of(correctRating)

        assertEquals(rating, correctRating, 1.0e-10)
    }

}