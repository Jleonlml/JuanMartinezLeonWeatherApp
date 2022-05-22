package com.example.juanmartinezleonwheaterapp.repository

import android.util.Log
import com.example.juanmartinezleonwheaterapp.api.ApiService
import com.example.juanmartinezleonwheaterapp.model.WeatherResponse

interface WeatherRepository {
suspend fun getWeather(city: String?): WeatherResponse
}

class WeatherRepositoryImplementation(
    private val service:ApiService = ApiService.getApiService()
    ):WeatherRepository {
    override suspend fun getWeather(city: String?): WeatherResponse {
        val response = service.getWeather(city)

        return if(response.isSuccessful) {
            response.body()!!
        }else {
            WeatherResponse(emptyList(), null)
        }
    }
}