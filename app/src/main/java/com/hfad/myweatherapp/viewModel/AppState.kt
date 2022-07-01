package com.hfad.myweatherapp.viewModel

import com.hfad.myweatherapp.domain.Weather

sealed class AppState {

    data class Success(val weatherDate: Weather) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()


}
