package com.example.juanmartinezleonwheaterapp.api

import com.example.juanmartinezleonwheaterapp.model.WeatherResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast/")
    suspend fun getWeather(
        @Query("q") q: String? = null,
        @Query("appid") appid: String? = "5e242b9f4dc7847912878264a7e06841"
    ): Response<WeatherResponse>


    companion object {
        private var instance: ApiService? = null
        fun getApiService(): ApiService {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
            return instance!!
        }
    }
}