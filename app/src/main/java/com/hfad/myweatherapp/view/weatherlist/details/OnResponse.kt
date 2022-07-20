package com.hfad.myweatherapp.view.weatherlist.details


import com.hfad.myweatherapp.model.dto.WeatherDTO

interface OnResponse {
    fun onResponse(weather: WeatherDTO){

    }
}