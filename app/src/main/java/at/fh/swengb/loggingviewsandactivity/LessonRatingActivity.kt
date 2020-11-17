package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lesson_rating.*

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)
        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)?: "no ID"
        LessonRepository.lessonById(lessonId)
        lesson_rating_header.text = lessonId
    }
}