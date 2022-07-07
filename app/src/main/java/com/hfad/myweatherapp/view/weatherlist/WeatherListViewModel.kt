package com.hfad.myweatherapp.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherapp.model.*
import com.hfad.myweatherapp.viewModel.AppState

class WeatherListViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData<AppState>(),
) :
    ViewModel() {

    lateinit var repositoryMany: RepositoryMany
    lateinit var repositoryOfOne: RepositoryOfOne

    fun getLiveData(): MutableLiveData<AppState> {
        choiseRepository()
        return liveData
    }

    private fun choiseRepository() {
        repositoryOfOne = if (isConnected()) {
            InMemoryRepositoryRemote()
        } else {
            InMemoryRepositoryLocal()
        }
        repositoryMany = InMemoryRepositoryLocal()
    }

    fun getWeatherListForRussia() {
        sentRequest(Continents.Russia)
    }

    fun getWeatherListForWorld() {
        sentRequest(Continents.World)
    }


    private fun sentRequest(location: Continents) {
        choiseRepository()
        liveData.value = AppState.Loading
        if (false) {
            liveData.postValue(AppState.Error(throw IllegalStateException("что то пошло не так")))
        } else {
            liveData.postValue(AppState.SuccessMany(repositoryMany.getWeatherList(location)))
        }

    }

    fun isConnected(): Boolean {
        return false
    }


}