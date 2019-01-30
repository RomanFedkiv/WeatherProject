package com.example.weatherproject.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.weatherproject.cache.WeatherForecastDao

@Entity(tableName = WeatherForecastDao.TABLE_NAME)
data class WeatherForecastEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    val city : String,
    val weatherTempList : List<WeatherTempEntity>
)


data class WeatherTempEntity(
    val temp : Double,
    val time : String
)