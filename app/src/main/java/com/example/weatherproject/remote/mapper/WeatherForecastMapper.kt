package com.example.weatherproject.remote.mapper

import com.example.weatherproject.data.WeatherForecastEntity
import com.example.weatherproject.remote.WeatherForecastEntityRemote

class WeatherForecastMapper : Mapper<WeatherForecastEntityRemote,WeatherForecastEntity> {

    var mapper = WeatherTempMapper()

    override fun mapFromRemote(remote: WeatherForecastEntityRemote): WeatherForecastEntity {

        val list = remote.weatherList.map {
            mapper.mapFromRemote(it)
        }

        return WeatherForecastEntity(0, remote.city.name, list)
    }


}