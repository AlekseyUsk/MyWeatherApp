package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather
import com.hfad.myweatherapp.domain.getRussianCities
import com.hfad.myweatherapp.domain.getWorldCities

class InMemoryRepositoryLocal : RepositoryMany,RepositoryOfOne {
    override fun getWeatherList(location: Continents): List<Weather> {
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
        return Weather()
    }
}