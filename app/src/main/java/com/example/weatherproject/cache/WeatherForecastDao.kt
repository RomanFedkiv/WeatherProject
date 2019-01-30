package com.example.weatherproject.cache

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.example.weatherproject.data.WeatherForecastEntity

@Dao
interface WeatherForecastDao {

    companion object {
        const val TABLE_NAME = "weather_forecast"
    }

    @Insert
    fun saveWeatherForecast( weatherForecast : WeatherForecastEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    fun getWeatherForecast() : LiveData<WeatherForecastEntity>

    @Query("DELETE  FROM $TABLE_NAME")
    fun deleteAll()
}