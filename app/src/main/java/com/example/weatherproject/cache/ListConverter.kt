package com.example.weatherproject.cache

import android.arch.persistence.room.TypeConverter
import com.example.weatherproject.data.WeatherTempEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    private val gson = Gson()

    @TypeConverter
    fun toList(data : String) : List<WeatherTempEntity>{
        val type = object : TypeToken<List<WeatherTempEntity>>() {}.type
        return gson.fromJson(data, type)
    }

    @TypeConverter
    fun fromList(list: List<WeatherTempEntity>) = gson.toJson(list)!!
}