package at.fh.swengb.loggingviewsandactivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_lesson_list.*
import kotlinx.android.synthetic.main.activity_rating.*

class LessonListActivity : AppCompatActivity() {
    companion object {
        val EXTRA_LESSON_ID = "LESSON_ID_EXTRA"
        val ADD_OR_EDIT_RATING_REQUEST = 1
    }

    val lessonAdapter = LessonAdapter() {
        //Toast.makeText(this, "Lesson with name: ${it.name} has been clicked", Toast.LENGTH_LONG).show()
        val implicitIntent = Intent(this, LessonRatingActivity::class.java)
        implicitIntent.putExtra(EXTRA_LESSON_ID, it.id)
        startActivityForResult(implicitIntent, ADD_OR_EDIT_RATING_REQUEST)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_list)
        //Thread.sleep(5000)
        SleepyAsyncTask().execute()

        LessonRepository.lessonsList(
            success = {
                // handle success
                lessonAdapter.updateList(it)
                Log.i("onCreate", "Sucess")
            },
            error = {
                // handle error
                "Error"
                Log.e("onCreate", "Failed to fetsch (ListActivity): $it")
            }
        )
        fun parseJson() {
            val moshi = Moshi.Builder().build()
            val jsonAdapter = moshi.adapter<Lesson>(Lesson::class.java)
            val lesson = jsonAdapter.fromJson("""
            {
                "id": "1",
                "name": "Lecture 0",
                "date": "09.10.2019",
                "topic": "Introduction",
                "type": "LECTURE",
                "lecturers": [
                {
                    "name": "Lukas Bloder"
                },
                {
                    "name": "Peter Salhofer"
                }
                ],
                "ratings": [],
                "imageUrl": ""
            }
            """)
            Log.e("JsonLog", lesson?.name.toString())
        }

    //    lessonAdapter.updateList(LessonRepository.lessonsList())
        lesson_recycler_view.layoutManager = LinearLayoutManager(this)
        lesson_recycler_view.adapter = lessonAdapter
    }
    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
       // if (requestCode == ADD_OR_EDIT_RATING_REQUEST && resultCode == RESULT_OK) {
          //  lessonAdapter.updateList(LessonRepository.lessonsList())
          //  val resultExtra = data?.getStringExtra(RatingActivity.EXTRA_ADDED_OR_EDITED_RESULT) ?: return
//do something with the returned data
           // Log.e("RESULT_EXTRA", "${resultExtra}")
        //}
        LessonRepository.lessonsList(
            success = {
                // handle success
                lessonAdapter.updateList(it)
                Log.i("onActivityResult", "Success")
            },
            error = {
                // handle error
                "Error"
                Log.e("onActivityResult","Failed update")
            }
        )
    }
}