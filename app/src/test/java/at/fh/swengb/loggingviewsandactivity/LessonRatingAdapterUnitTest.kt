package at.fh.swengb.loggingviewsandactivity

import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

class LessonRatingAdapterUnitTest {
    val items = (1..10).map {
        Lesson(
            "Lesson ${it}",
            "test Lesson",
            "25.11.2020",
            "testing",
            LessonType.PRACTICAL,
            listOf(Lecturer("Tester")),
            mutableListOf()
        )
    }
    @Test
    fun itemCount_isCorrect() {
        val lessonAdapter = LessonAdapter({println(it)})
        var size = lessonAdapter.itemCount;
        assertThat(size).isEqualTo(0);
        lessonAdapter.lessonList = items
        size = lessonAdapter.itemCount;
        assertThat(size).isEqualTo(10);
    }
    @Test
    fun binding_isCorrect() {
        // make sure that onBindViewHolder(...) works as expected
        val adapter = LessonAdapter({ print(it) })
        adapter.lessonList = items
        val mockHolder:LessonViewHolder = Mockito.mock(LessonViewHolder::class.java)
        adapter.onBindViewHolder(mockHolder, 5)
        verify(mockHolder, times(1)).bindItem(items[5])
    }
}