package sv.edu.udb.climaexpress

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object OneCallClient {
    val service: OneCallService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(OneCallService::class.java)
    }
}