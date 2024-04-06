package kz.android.animalsapi.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    val apiService: Service by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }
}