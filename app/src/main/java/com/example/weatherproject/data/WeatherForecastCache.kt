package com.example.weatherproject.data

import android.arch.lifecycle.LiveData
import io.reactivex.Completable

interface WeatherForecastCache {

    fun saveWeatherForecast(weatherForecastEntity: WeatherForecastEntity) : Completable

    fun getWeatherForecast() : LiveData<WeatherForecastEntity>

    fun deleteWeatherForecast() : Completable
}