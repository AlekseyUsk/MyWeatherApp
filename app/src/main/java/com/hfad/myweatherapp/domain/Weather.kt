package com.hfad.myweatherapp.domain

data class Weather(

    val city: City = getDefaultCity(),
    val temp: Int = 0,     // температура
    val feelsLike: Int = 0 // ощущается как
)

data class City(

    val CityName: String,
    val lat: Double, // широта
    val lon: Double  // долгота


)

fun getDefaultCity() = City("Новосибирск", 82.9346, 55.0415)
