package com.example.weatherproject.data

import io.reactivex.Single

interface WeatherForecastRemote {

    fun getWeatherForecast(lat : Double,lon : Double) : Single<WeatherForecastEntity>
}