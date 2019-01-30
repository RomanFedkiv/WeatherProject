package com.example.weatherproject.remote

import com.example.weatherproject.data.WeatherForecastEntity
import com.example.weatherproject.data.WeatherForecastRemote
import com.example.weatherproject.remote.mapper.WeatherForecastMapper
import io.reactivex.Single

class WeatherForecastRemoteImpl(
    private val api : WeatherForecastApi,
    private val mapper : WeatherForecastMapper) : WeatherForecastRemote {

    override fun getWeatherForecast(lat : Double, lon: Double): Single<WeatherForecastEntity> =
            api.getWeatherForecastWithLatLng(lat, lon, units , apiKey)
                .map { mapper.mapFromRemote(it) }

    private companion object {
        const val  units = "metric"
        const val apiKey = "df95e79d9772c855a4304305ad00371c"
    }
}