package com.example.weatherproject.data

import android.arch.lifecycle.LiveData
import android.util.Log
import com.example.weatherproject.domain.WeatherForecastRepository
import io.reactivex.schedulers.Schedulers

class WeatherForecastRepositoryImpl(
    private val cache : WeatherForecastCache,
    private val remote: WeatherForecastRemote) : WeatherForecastRepository {

    override fun getWeatherForecast(): LiveData<WeatherForecastEntity> = cache.getWeatherForecast()

    override fun updateWeatherForecast(lat : Double, lon : Double) {
        remote.getWeatherForecast(lat,lon)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .flatMap {
                cache.deleteWeatherForecast()
                    .andThen (cache.saveWeatherForecast(it))
                    .toSingle{it}
            }.subscribe()



    }
}