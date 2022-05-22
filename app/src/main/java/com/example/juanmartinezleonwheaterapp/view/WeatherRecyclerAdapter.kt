package com.example.juanmartinezleonwheaterapp.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.juanmartinezleonwheaterapp.databinding.WeatherListItemBinding
import com.example.juanmartinezleonwheaterapp.model.WeatherInfo

class WeatherRecyclerAdapter(
    private val weatherList: MutableList<WeatherInfo> = mutableListOf(),
    private val openDetails: (WeatherInfo) -> Unit
): RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder>() {

    fun setWeatherList(newList: List<WeatherInfo>) {
        weatherList.clear()
        weatherList.addAll(newList)
        notifyDataSetChanged()
    }

    inner class WeatherViewHolder(
        private val binding:WeatherListItemBinding
    ):RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherInfo: WeatherInfo) {
            binding.tvWeather.text = weatherInfo.weather[0].description
            binding.tvTemperature.text = weatherInfo.main.temp.toString()

            binding.root.setOnClickListener {
                openDetails(weatherInfo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WeatherViewHolder(
            WeatherListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount() = weatherList.size


}