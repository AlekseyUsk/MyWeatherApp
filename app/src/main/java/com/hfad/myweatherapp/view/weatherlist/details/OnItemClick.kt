package com.hfad.myweatherapp.view.weatherlist.details

import com.hfad.myweatherapp.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}