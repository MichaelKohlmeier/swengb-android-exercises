package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_lesson_rating.*

class LessonRatingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)?: "no ID"
        LessonRepository.lessonById(lessonId,
            success = {
                lesson_rating_header.text = it.name
                Log.i("onCreate", "Success")
            },
            error = {
                "Error"
                Log.e("onCreate","Fail to fetsch lesson")
            }
        )
        rate_lesson.setOnClickListener{
            val ratingValue = lesson_rating_bar.rating.toDouble()
            val ratingFeedback = lesson_feedback.text.toString()

            val rating = LessonRating(
                    ratingValue,
                    ratingFeedback
            )
            LessonRepository.rateLesson(lessonId?: "",rating)
            val resultIntent = Intent()
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    fun share(){
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT,lesson_rating_header.text)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    inline fun consume(f: () -> Unit): Boolean {
        f()
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.share -> consume{share()}
            else -> super.onOptionsItemSelected(item)
        }
    }
}