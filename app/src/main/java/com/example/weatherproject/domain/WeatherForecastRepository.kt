package com.example.weatherproject.domain

import android.arch.lifecycle.LiveData
import com.example.weatherproject.data.WeatherForecastEntity

interface WeatherForecastRepository {

    fun getWeatherForecast() : LiveData<WeatherForecastEntity>

    fun updateWeatherForecast(lat : Double, lon : Double)
}