package com.hfad.myweatherapp.view.weatherlist.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.hfad.myweatherapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.model.request.WeatherLoader


class FragmentDetails : Fragment(), OnResponse {


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


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// 2 - проверяем если что то в arguments(бутылке)
        val weather =
            (arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA))


        weather?.let { weatherLocal ->
// 3 - если он не null  то говорим у этого города есть широта и долгота
            WeatherLoader.requestLoader(
                weatherLocal.city.lat,
                weatherLocal.city.lon
            ) { weatherDTO ->  // 8 - приходит ответ

                requireActivity().runOnUiThread {    // говорим активити в главном потоке запустить код -->
// 9 - отрисовка ответа -->
                    renderData(weatherLocal.apply {
                        weatherLocal.feelsLike = weatherDTO.fact.feelsLike

                        weatherLocal.temperature = weatherDTO.fact.temp

                    })

                }
            }


        }
    }

    private fun renderData(weather: Weather) {
        with(binding) {
            cityName.text = weather.city.CityName
            temperatureValue.text = weather.temperature.toString()
            feelsLikeValue.text = weather.feelsLike.toString()
            cityCoordinates.text = "${weather.city.lat}/${weather.city.lon}"
        }
    }

// 1 - по клику на город открывается город с широтой и долготой
    companion object bundleExtra {
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