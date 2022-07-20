package com.hfad.myweatherapp.view.weatherlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.myweatherapp.model.*
import com.hfad.myweatherapp.viewModel.AppState
import kotlin.random.Random

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
        Thread {
            Thread.sleep(1000L)
            if ((0..3).random(Random(System.currentTimeMillis())) == 1) {
                liveData.postValue(AppState.Error(IllegalStateException("что то пошло не так")))
            } else {
                liveData.postValue(AppState.SuccessMany(repositoryMany.getWeatherList(location)))
            }
        }.start()

    }

    fun isConnected(): Boolean {
        return false
    }


}