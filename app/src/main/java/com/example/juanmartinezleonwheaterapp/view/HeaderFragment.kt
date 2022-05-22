package com.example.juanmartinezleonwheaterapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.juanmartinezleonwheaterapp.databinding.HeaderBinding

class HeaderFragment: Fragment() {
    private var _binding: HeaderBinding? = null
    private val binding: HeaderBinding get() = _binding!!

    companion object {
        const val KEY = "KEY"
        fun newInstance(input: String): HeaderFragment {
            val fragment = HeaderFragment()
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
        _binding = HeaderBinding.inflate(layoutInflater)
        val cityName = arguments?.getString(WeatherRvFragment.KEY)
        binding.apply {
            tvTitleCityName.text = cityName
            ivBack.setOnClickListener {
                activity?.onBackPressed()
            }
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}