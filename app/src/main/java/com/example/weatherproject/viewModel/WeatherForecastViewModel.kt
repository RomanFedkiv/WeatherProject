package com.example.weatherproject.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.weatherproject.data.WeatherForecastEntity
import com.example.weatherproject.domain.WeatherForecastCacheUseCase
import com.example.weatherproject.domain.WeatherForecastRemoteUseCase

class WeatherForecastViewModel(private val useCaseCache: WeatherForecastCacheUseCase,
                               private val useCaseRemote: WeatherForecastRemoteUseCase) : ViewModel() {

    fun getWeatherForecast()
        = useCaseCache.getWeatherForecast()


    fun updateWeatherForecast(lat: Double, lon : Double) =
        useCaseRemote.updateWeatherForecast(lat,lon)

}