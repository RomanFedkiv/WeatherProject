package com.example.weatherproject.domain

class WeatherForecastRemoteUseCase(private val repository: WeatherForecastRepository) {

    fun updateWeatherForecast(lat : Double,lng : Double) = repository.updateWeatherForecast(lat,lng)
}