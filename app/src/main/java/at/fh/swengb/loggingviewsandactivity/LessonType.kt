package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.JsonClass
import java.math.BigDecimal
import java.math.RoundingMode


enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}
@JsonClass(generateAdapter = true)
class LessonRating(val ratingValue:Double, val feedback: String){}

@JsonClass(generateAdapter = true)
class Lesson(val id:String, val name:String,val date:String,val topic:String,val type:LessonType, val lecturers:List<Lecturer>,val ratings:MutableList<LessonRating>, val imageUrl: String){
    fun ratingAverage(): Double{
        return BigDecimal(ratings.map { it.ratingValue }.average().takeIf { !it.isNaN() } ?: 0.0).setScale(2, RoundingMode.HALF_EVEN).toDouble()
       /* var sum: Double = 0.0
        for (element in ratings){
            sum += element.ratingValue
        }
        if (ratings.size != 0){
            sum/ratings.size
        }else{
            sum
        }
        return sum*/
    }
}
@JsonClass(generateAdapter = true)
class Lecturer(val name:String){}

//test--------------------------------------------------------------------------------------------------------

