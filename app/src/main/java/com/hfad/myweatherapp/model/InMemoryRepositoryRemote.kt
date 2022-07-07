package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.domain.getRussianCities
import com.hfad.myweatherapp.domain.getWorldCities
import com.hfad.myweatherapp.viewModel.AppState

class InMemoryRepositoryRemote : RepositoryOfOne {
    fun getWeatherList(location: Continents): List<Weather> {
        return when (location) {
            Continents.Russia -> {
                getRussianCities()
            }
            Continents.World -> {
                getWorldCities()
            }

        }

    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        //добавил сам поток тк ошибка была - Заглушка
        Thread {
            Thread.sleep(300L)


        }.start()
        return Weather()
    }
}

