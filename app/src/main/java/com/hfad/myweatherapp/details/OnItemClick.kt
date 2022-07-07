package com.hfad.myweatherapp.details

import com.hfad.myweatherapp.domain.Weather

fun interface OnItemClick {
    fun onItemClick(weather: Weather)
}