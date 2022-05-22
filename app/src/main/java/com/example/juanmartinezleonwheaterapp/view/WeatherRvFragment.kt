package com.example.juanmartinezleonwheaterapp.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.juanmartinezleonwheaterapp.R
import com.example.juanmartinezleonwheaterapp.databinding.RvCityWeatherListBinding
import com.example.juanmartinezleonwheaterapp.helper.GlobalUtilites
import com.example.juanmartinezleonwheaterapp.model.WeatherInfo
import com.example.juanmartinezleonwheaterapp.repository.WeatherRepositoryImplementation
import com.example.juanmartinezleonwheaterapp.viewmodel.WeatherViewModel

class WeatherRvFragment: Fragment(){
    private var _binding: RvCityWeatherListBinding? = null
    private val binding: RvCityWeatherListBinding get() = _binding!!
    private val globalUtilities: GlobalUtilites = GlobalUtilites()
    lateinit var weatherRecyclerAdapter: WeatherRecyclerAdapter


    private val viewModel: WeatherViewModel by lazy {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return WeatherViewModel(WeatherRepositoryImplementation()) as T
            }
        }.create(WeatherViewModel::class.java)
    }

    companion object {
        const val KEY = "KEY"
        fun newInstance(input: String): WeatherRvFragment {
            val fragment = WeatherRvFragment()
            val bundle = Bundle()
            bundle.putString(KEY, input)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RvCityWeatherListBinding.inflate(layoutInflater)
        val cityName = globalUtilities.stringToCapitalFirst(arguments?.getString(KEY).toString())
        parentFragmentManager.beginTransaction()
            .replace(R.id.frag_header, HeaderFragment.newInstance(cityName!!))
            .commit()
        viewModel.getWeather(cityName)
        configureObserver()
        return binding.root
    }

    private fun configureObserver() {
        weatherRecyclerAdapter = WeatherRecyclerAdapter(openDetails = ::openDetails)

        viewModel.weatherLivedata.observe(viewLifecycleOwner) { response ->
            if (response.isEmpty()) {
                binding.tvErrorText.text = "Network call failed"
            } else {
                weatherRecyclerAdapter.setWeatherList(response)
                var mLayoutManager = LinearLayoutManager(context);
                binding.apply {
                    rvWeather.layoutManager = mLayoutManager;
                    rvWeather.adapter = weatherRecyclerAdapter
                    pbLoading.visibility = View.GONE
                    tvErrorText.visibility = View.GONE
                }
            }
        }
    }

    private fun openDetails(weatherObj: WeatherInfo) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(weatherObj))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}