package kz.android.animalsapi.api

import kz.android.animalsapi.Animal
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {
    @Headers("X-Api-Key:TKHFBWsN9Ow7y5oB4J07Jg==WuFyIZa2jm0x6nPK")
    @GET("animals")
    fun getAnimalsByName(@Query("name") name: String): Call<List<Animal>>
}