package com.hfad.myweatherapp.view.weatherlist.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.myweatherapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherapp.domain.Weather


class FragmentDetails : Fragment() {


    var isRussia = true

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val weather =
            (arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA)) // ОШИБКА! BUNDLE_WEATHER_EXTRA - ГОРИТ КРАССНЫМ
        if (weather != null)
            renderData(weather)
    }

    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.CityName
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }


    companion object bundleExtra {  //ОШИБКА! companion  - Подчеркнуто крассным
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER_EXTRA"
        fun newInstance(weather: Weather): FragmentDetails {

            return FragmentDetails().apply {
                arguments = Bundle().apply {
                    this.putParcelable(BUNDLE_WEATHER_EXTRA, weather)
                }

            }

        }
    }

}