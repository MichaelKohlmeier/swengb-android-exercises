package at.fh.swengb.loggingviewsandactivity

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_lesson.view.*

class LessonAdapter(val clickListener: (lesson: Lesson) -> Unit): RecyclerView.Adapter<LessonViewHolder>() {


    var lessonList = listOf<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val inflater = LayoutInflater.from(parent.context)
        val lessonItemView = inflater.inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(lessonItemView, clickListener)
    }

    override fun getItemCount(): Int {
      //  TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return lessonList.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.bindItem(lessonList[position])
    }
    fun updateList(newList: List<Lesson>) {
        lessonList = newList
        notifyDataSetChanged()
    }
}

class LessonViewHolder(itemView: View, val clickListener:(lesson: Lesson)->Unit): RecyclerView.ViewHolder(itemView) {
    fun bindItem(lesson: Lesson) {
        itemView.item_lesson_name.text = lesson.name
        itemView.item_lesson_date.text = lesson.date
        itemView.setOnClickListener {
            clickListener(lesson)
        }
        itemView.item_lesson_topic.text = lesson.topic
        itemView.item_lesson_lecturers.text = lesson.lecturers.joinToString(", "){it.name}
        itemView.item_lesson_avg_rating_bar.rating = lesson.ratingAverage().toFloat()
        itemView.item_lesson_avg_rating_value.text = lesson.ratingAverage().toString()
        itemView.item_lesson_rating_count.text = lesson.ratings.size.toString()
    }
}