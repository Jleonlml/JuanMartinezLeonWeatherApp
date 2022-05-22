package com.example.juanmartinezleonwheaterapp.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class WeatherResponse(
    val list: List<WeatherInfo>,
    val city: CityInfo?
) : Parcelable

// Serialization -> reflection
// Parcelable -> no reflection
@Parcelize
data class WeatherInfo(
    var dt_txt: String,
    val main: MainInfo,
    val weather: List<Weather>,
): Parcelable

@Parcelize
data class MainInfo(
    val temp: Double,
    val feels_like: Double,
    val temp_min:  Double,
    val temp_max:  Double,
    val humidity: Int
): Parcelable

@Parcelize
data class Weather(
    val main: String,
    val description: String
): Parcelable

@Parcelize
data class CityInfo(
    val name: String,
    val country: String
): Parcelable


