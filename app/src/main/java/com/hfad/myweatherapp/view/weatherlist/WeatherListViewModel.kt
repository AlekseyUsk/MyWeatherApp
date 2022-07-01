package com.hfad.myweatherapp.view.weatherlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherapp.model.InMemoryRepositoryLocal
import com.hfad.myweatherapp.model.InMemoryRepositoryRemote
import com.hfad.myweatherapp.model.Repository
import com.hfad.myweatherapp.viewModel.AppState
import java.lang.Thread.sleep

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
) :
    ViewModel() {

    lateinit var repository: Repository

    fun getLiveData(): MutableLiveData<AppState> {
        // choiseRepository()
        return liveData
    }

    private fun choiseRepository() {
        if (isConnected()) {
            repository = InMemoryRepositoryRemote()
        } else {
            repository = InMemoryRepositoryLocal()
        }
    }


    fun sentRequest() {
        choiseRepository()
        liveData.value = AppState.Loading
        liveData.value =
            AppState.Success(repository.getWeather(82.9346, 55.0415)) //тут пока что заглушка
    }

    fun isConnected(): Boolean {
        return false
    }


}