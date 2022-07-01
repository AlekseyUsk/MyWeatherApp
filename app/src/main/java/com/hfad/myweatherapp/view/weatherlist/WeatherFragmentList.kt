package com.hfad.myweatherapp.view.weatherlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.myweatherapp.databinding.FragmentWeatherListBinding
import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.viewModel.AppState

class WeatherFragmentList : Fragment() {

    companion object {
        fun newInstance(): WeatherFragmentList {
            return WeatherFragmentList()
        }
    }

    lateinit var binding: FragmentWeatherListBinding
    lateinit var viewModel: WeatherListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWeatherListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(
            WeatherListViewModel::class.java
        )
        viewModel.getLiveData().observe(viewLifecycleOwner, object : Observer<AppState> {
            override fun onChanged(t: AppState) {
                renderData(t)
            }

        })
        viewModel.sentRequest() // запрос
    }

    private fun renderData(appState: AppState) { // вот этот метод я могу использовать вместо Toast
        when (appState) {
            is AppState.Error -> TODO()
            AppState.Loading -> TODO()
            is AppState.Success -> {
                val result = appState.weatherDate
                binding.cityName.text = result.city.CityName
                binding.temperatureValue.text = result.temp.toString()
                binding.feelsLikeValue.text = result.feelsLike.toString()
                binding.cityCoordinates.text = "${result.city.lat}  ${result.city.lon}"
            }


        }

    }
}

