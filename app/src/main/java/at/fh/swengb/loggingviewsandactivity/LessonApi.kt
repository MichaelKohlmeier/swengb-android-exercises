package at.fh.swengb.loggingviewsandactivity

import com.squareup.moshi.Moshi
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

object LessonApi {
    const val accessToken = "8ea9047b-5f73-4dc5-979a-6147c2e8657a"
    val retrofit: Retrofit
    val retrofitService: LessonApiService
    init {
        val moshi = Moshi.Builder().build()
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl("https://lessons.bloder.xyz")
            .build()
        retrofitService = retrofit.create(LessonApiService::class.java)

    }
}
interface LessonApiService {
    @GET("/lessons")
    @Headers("X-API-KEY: ${LessonApi.accessToken}")
    fun lessons(): Call<List<Lesson>>
    @POST("/lessons/{id}/rate")
    @Headers("X-API-KEY: ${LessonApi.accessToken}")
    fun rateLesson(@Path("id") lessonId: String, @Body rating: LessonRating): Call<Unit>

    @GET("/lessons/{id}/")
    @Headers("X-API-KEY: ${LessonApi.accessToken}")
    fun lessonById(@Path("id")lessonId:String): Call<Lesson>
}