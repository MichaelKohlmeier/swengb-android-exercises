package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_lesson_rating.*
import kotlinx.android.synthetic.main.activity_rating.*
import java.math.RoundingMode

class LessonRatingActivity : AppCompatActivity() {

    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val EXTRA_LESSON_NAME = "LESSON_NAME_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_rating)

        val lessonId = intent.getStringExtra(LessonListActivity.EXTRA_LESSON_ID)
       // var lesson:Lesson? = null

        lesson_rating.setOnClickListener {
            val intent = Intent(this, LessonNoteActivity::class.java)
            intent.putExtra(EXTRA_LESSON_ID, lessonId)
            intent.putExtra(EXTRA_LESSON_NAME, lesson_rating_header.text)
            startActivity(intent)
        }

        LessonRepository.lessonById(lessonId?:"",
            success = {
                lesson_rating_header.text = it.name
                Log.i("onCreate", "Success")
                val ratingAvg = it.ratingAverage().toBigDecimal().setScale(2,RoundingMode.HALF_UP).toDouble()
                ratingBar.rating = ratingAvg.toFloat()
                textView3.text = ratingAvg.toString()
                if(it.imageUrl != ""){
                    Glide.with(this).load(it.imageUrl).into(imageView2)
                }
                feedback_text.text = it.ratings.firstOrNull{it.feedback.isNotBlank()}?.feedback
                //lesson = it
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

            R.id.share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
                    type = "text/plain"
                }

                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)

                //Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}