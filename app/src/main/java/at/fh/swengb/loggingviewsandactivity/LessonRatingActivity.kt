package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
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

        rate_lesson.setOnClickListener{
            val ratingValue = lesson_rating_bar.rating.toDouble()
            val ratingFeedback = lesson_feedback.text.toString()

            val rating = LessonRating(
                    ratingValue,
                    ratingFeedback
            )
            LessonRepository.rateLesson(lessonId,rating)
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
        }
    }
}