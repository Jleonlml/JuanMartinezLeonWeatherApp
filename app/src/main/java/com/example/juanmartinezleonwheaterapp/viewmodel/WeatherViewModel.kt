package com.example.juanmartinezleonwheaterapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.juanmartinezleonwheaterapp.model.Weather
import com.example.juanmartinezleonwheaterapp.model.WeatherInfo
import com.example.juanmartinezleonwheaterapp.repository.WeatherRepositoryImplementation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel (
    private val repositoryImpl:WeatherRepositoryImplementation
    ) : ViewModel() {
    init {
        //getWeather(null)
    }

    private val _weatherLivedata = MutableLiveData<List<WeatherInfo>>()
    val weatherLivedata:LiveData<List<WeatherInfo>> get() =_weatherLivedata

    fun getWeather(city: String?){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repositoryImpl.getWeather(city = city)
            _weatherLivedata.postValue(response.list)
        }
    }
}

