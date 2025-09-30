package sv.edu.udb.climaexpress

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OneCallService {
    @GET("data/3.0/onecall")
    suspend fun getOneCallWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "es"
    ): Response<OneCallResponse>
}