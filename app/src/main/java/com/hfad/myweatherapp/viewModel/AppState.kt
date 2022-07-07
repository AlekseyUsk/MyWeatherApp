package com.hfad.myweatherapp.viewModel

import com.hfad.myweatherapp.domain.Weather

sealed class AppState {

    data class SuccessOfOne(val weatherDate: Weather) : AppState()
    data class SuccessMany(val weatherList: List<Weather>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()


}
