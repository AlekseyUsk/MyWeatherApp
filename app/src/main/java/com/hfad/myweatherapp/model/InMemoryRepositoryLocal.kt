package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather

class InMemoryRepositoryLocal : Repository {
    override fun getWeatherList(): List<Weather> {
        TODO("Not yet implemented")
    }

    override fun getWeather(lat: Double, lon: Double): Weather {
        TODO("Not yet implemented")
    }
}