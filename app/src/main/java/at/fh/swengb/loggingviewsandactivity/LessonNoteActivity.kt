package at.fh.swengb.loggingviewsandactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import at.fh.swengb.loggingviewsandactivity.LessonListActivity.Companion.EXTRA_LESSON_ID

import at.fh.swengb.loggingviewsandactivity.LessonRatingActivity.Companion.EXTRA_LESSON_NAME
import kotlinx.android.synthetic.main.activity_lesson_note.*


class LessonNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson_note)

        val lessonID = intent.getStringExtra(LessonRatingActivity.EXTRA_LESSON_ID)
        val lessonName = intent.getStringExtra(LessonRatingActivity.EXTRA_LESSON_NAME)?:""

        //enter_note.text = findLessonNoteById(applicationContext, lessonId)?.lessonName?
        textView_lesson.text = lessonName.toString()
        val newnote =  LessonRepository.findSameID(applicationContext, lessonID.toString())
        multiline_lesson.setText(newnote?.text)

        if (lessonID.isNullOrEmpty()){
            Log.e("on create", "lessonId is null or empty")
            finish()
        }

        button_note_save.setOnClickListener() {
            val note = LessonNote(lessonID?:"", lessonName,multiline_lesson.text.toString())
            LessonRepository.addLessonNote(applicationContext,note)
            //val newnote =  LessonRepository.findSameID(applicationContext, lessonID.toString())
            multiline_lesson.setText(newnote?.text)
            finish()
        }
    }
}