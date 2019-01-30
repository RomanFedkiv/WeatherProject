package com.example.weatherproject.cache

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.weatherproject.data.WeatherForecastEntity


@Database(entities = [WeatherForecastEntity::class],version = 1)
@TypeConverters(ListConverter::class)
abstract class DbAbstract : RoomDatabase() {

    abstract fun weatherForecastDao() : WeatherForecastDao
}