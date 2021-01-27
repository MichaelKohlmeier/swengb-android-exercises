package at.fh.swengb.loggingviewsandactivity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

class LessonNoteViewModel(application: Application): AndroidViewModel(application) {

    private val noteId = MutableLiveData<String>()

    val note: LiveData<LessonNote?> = Transformations.switchMap(noteId){
        LessonRepository.findLessonNoteByIdLiveData(getApplication(), it)
    }

    val noteText = Transformations.map(note){
        it?.text
    }

    fun findLessonNoteById(note: String){
        noteId.value = note
    }

}