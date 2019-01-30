package com.example.weatherproject.domain

class WeatherForecastCacheUseCase(private val repository: WeatherForecastRepository) {

    fun getWeatherForecast() = repository.getWeatherForecast()
}