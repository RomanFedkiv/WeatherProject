package com.example.weatherproject.cache

import android.arch.lifecycle.LiveData
import com.example.weatherproject.data.WeatherForecastCache
import com.example.weatherproject.data.WeatherForecastEntity
import io.reactivex.Completable

class WeatherForecastCacheImpl(private val db : WeatherForecastDao) : WeatherForecastCache {

    override fun saveWeatherForecast(weatherForecastEntity: WeatherForecastEntity) : Completable {
       return Completable.fromCallable { db.saveWeatherForecast(weatherForecastEntity) }
    }

    override fun getWeatherForecast(): LiveData<WeatherForecastEntity> = db.getWeatherForecast()

    override fun deleteWeatherForecast() = Completable.fromCallable{db.deleteAll()}
}