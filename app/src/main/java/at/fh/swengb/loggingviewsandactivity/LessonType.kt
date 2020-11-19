package at.fh.swengb.loggingviewsandactivity

enum class LessonType(val description: String) {
    LECTURE("Lecture"),
    PRACTICAL("Practical")
}
class LessonRating(val ratingValue:Double, val feedback: String){}

class Lesson(val id:String, val name:String,val date:String,val topic:String,val type:LessonType, val lecturers:List<Lecturer>,val ratings:MutableList<LessonRating>){
    fun ratingAverage(): Double{
        var sum: Double = 0.0
        for (element in ratings){
            sum += element.ratingValue
        }
        if (ratings.size != 0){
            sum/ratings.size
        }else{
            sum
        }
        return sum
    }
}
class Lecturer(val name:String){}