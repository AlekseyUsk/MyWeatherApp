package com.hfad.myweatherapp.view.weatherlist.details

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.hfad.myweatherapp.databinding.FragmentDetailsBinding
import com.hfad.myweatherapp.domain.Weather
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


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
            (arguments?.getParcelable<Weather>(BUNDLE_WEATHER_EXTRA))

        weather?.let {
            val uri =
                URL("https://api.weather.yandex.ru/v2/forecast?lat=${it.city.lat}&lon=${it.city.lon}")


            var myConnection: HttpURLConnection? = null

            myConnection = uri.openConnection() as HttpURLConnection
            myConnection.readTimeout = 30
            myConnection.addRequestProperty(
                "X-Yandex-API-Key",
                "dcc58378-25de-4d0c-abc7-a2f5d79cabc1"
            )
            val handler =
                Handler(Looper.myLooper()!!)
            Thread {
                val reader = BufferedReader(InputStreamReader(myConnection.inputStream))

                val weatherDTO = Gson().fromJson(
                    getLines(reader),      // ! getLines - горит красным
                    WeatherDTO::class.java  // ! WeatherDTO - горит красным (только если тут класс добавляешь в FragmentDetails перестает гореть но это не то я так понял)
                )

                requireActivity().runOnUiThread {

                    renderData(it.apply {
                        feelsLike =
                            weatherDTO.Fact.feelsLike  // ! fact.feelsLike - они тут автоподсказкой ненаходились такое чувство что невидит класс
                        temperature =
                            weatherDTO.Fact.temp  // ! тут тоже самое (Fact пробовал и с маленькой и с большой писать)
                    })

                }

            }.start()

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
