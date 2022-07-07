package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather

fun interface RepositoryOfOne {
    fun getWeather(lat: Double, lon: Double): Weather
}

fun interface RepositoryMany {
    fun getWeatherList(location: Continents): List<Weather>
}


sealed class Continents {
    object Russia : Continents()
    object World : Continents()
}