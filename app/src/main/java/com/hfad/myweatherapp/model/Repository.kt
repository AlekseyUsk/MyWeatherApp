package com.hfad.myweatherapp.model

import com.hfad.myweatherapp.domain.Weather

interface Repository {

    fun getWeatherList():List<Weather>
    fun getWeather(lat: Double, lon: Double):Weather


}