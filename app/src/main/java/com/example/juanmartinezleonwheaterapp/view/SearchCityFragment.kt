package com.example.juanmartinezleonwheaterapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.juanmartinezleonwheaterapp.R
import com.example.juanmartinezleonwheaterapp.databinding.SearchCityBinding


class SearchCityFragment: Fragment(){
    private var _binding: SearchCityBinding? = null
    private val binding: SearchCityBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = SearchCityBinding.inflate(layoutInflater)

        binding.btnLookup.setOnClickListener {
            if (binding.tiCityName.text.toString().trim() != "")
            {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WeatherRvFragment.newInstance(binding.tiCityName.text.toString()))
                    .addToBackStack(null)
                    .commit()
            }
            binding.tiCityName.text?.clear()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}