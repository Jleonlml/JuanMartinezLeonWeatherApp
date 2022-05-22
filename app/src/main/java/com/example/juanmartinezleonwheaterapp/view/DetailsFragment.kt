package com.example.juanmartinezleonwheaterapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.juanmartinezleonwheaterapp.R
import com.example.juanmartinezleonwheaterapp.databinding.WeatherDetailsBinding
import com.example.juanmartinezleonwheaterapp.helper.GlobalUtilites
import com.example.juanmartinezleonwheaterapp.model.WeatherInfo
import java.math.RoundingMode
import java.text.DecimalFormat

class DetailsFragment: Fragment() {
    private var _binding : WeatherDetailsBinding? = null
    private val binding: WeatherDetailsBinding get() = _binding!!
    private var degreesUnits: Int = DegreesUnits.KELVIN.type
    private val globalUtilities: GlobalUtilites = GlobalUtilites()

    enum class DegreesUnits(val type: Int) {
        KELVIN(1),
        CELCIUS(2),
        FARENHEIT(3)
    }

    companion object {
        const val KEY = "weatherObjKey"
        fun newInstance(weatherInfo: WeatherInfo): DetailsFragment {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(KEY, weatherInfo)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = WeatherDetailsBinding.inflate(layoutInflater)

        val weatherInfo: WeatherInfo? = arguments?.getParcelable(KEY)

        parentFragmentManager.beginTransaction()
            .replace(R.id.frag_header, HeaderFragment.newInstance(weatherInfo?.dt_txt?.substring(0, 10)!!))
            .commit()

        binding.apply {
            tvTemp.text = weatherInfo?.main?.temp.toString() + "°K"
            tvFeelsLike.text = "Feels Like: " + weatherInfo?.main?.feels_like.toString() + "°K"
            tvWeather.text = weatherInfo?.weather?.get(0)?.main
            tvWeatherDesc.text = weatherInfo?.weather?.get(0)?.description

            ivChangeUnits.setOnClickListener {
                when(degreesUnits)
                {
                    1->{
                        tvTemp.text = globalUtilities.convertToCelcius(weatherInfo?.main?.temp) + "°C"
                        tvFeelsLike.text = "Feels Like: " + globalUtilities.convertToCelcius(weatherInfo?.main?.feels_like) + "°C"
                        degreesUnits = DegreesUnits.CELCIUS.type
                    }
                    2->{
                        tvTemp.text = globalUtilities.convertToFarenheit(weatherInfo?.main?.temp) + "°F"
                        tvFeelsLike.text = "Feels Like: " + globalUtilities.convertToFarenheit(weatherInfo?.main?.feels_like) + "°F"
                        degreesUnits = DegreesUnits.FARENHEIT.type
                    }
                    3->{
                        tvTemp.text = weatherInfo?.main?.temp.toString() + "°K"
                        tvFeelsLike.text = "Feels Like: " + weatherInfo?.main?.feels_like.toString() + "°K"
                        degreesUnits = DegreesUnits.KELVIN.type
                    }
                }
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}