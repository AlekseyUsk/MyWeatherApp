package com.hfad.myweatherapp.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hfad.myweatherapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherapp.domain.Weather


class FragmentDetails : Fragment() {

//    companion object {
//        fun newInstance(weather: Weather): FragmentDetails {
//            return FragmentDetails()
//        }
//    }

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

        binding.cityName.text = weather.city.CityName
        binding.temperatureValue.text = weather.temperature.toString()
        binding.feelsLikeValue.text = weather.feelsLike.toString()
        binding.cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
    }


    companion object bundleExtra {  //ОШИБКА! companion  - Подчеркнуто крассным
        const val BUNDLE_WEATHER_EXTRA = "BUNDLE_WEATHER_EXTRA"
        fun newInstance(weather: Weather): FragmentDetails {
            val bundle = Bundle()
            bundle.putParcelable(BUNDLE_WEATHER_EXTRA, weather)

            val fr = FragmentDetails()
            fr.arguments = bundle

            return fr
        }
    }

}