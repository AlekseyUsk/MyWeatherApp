package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.viewModel.AppState

class InMemoryRepositoryRemote : Repository {
    override fun getWeatherList(): List<Weather> {
        Thread {
            Thread.sleep(300L)


        }.start()
        return listOf(Weather())
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        TODO("Not yet implemented")
    }

}