package com.example.weatherproject.remote.mapper

import com.example.weatherproject.data.WeatherTempEntity
import com.example.weatherproject.remote.WeatherListRemote
import com.example.weatherproject.remote.WeatherTempRemote

class WeatherTempMapper : Mapper<WeatherListRemote,WeatherTempEntity> {
    override fun mapFromRemote(remote: WeatherListRemote): WeatherTempEntity {
        return WeatherTempEntity(remote.weatherTemp.temp,remote.time)
    }

}