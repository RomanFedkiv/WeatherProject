package com.example.weatherproject.remote

import com.google.gson.annotations.SerializedName


data class WeatherForecastEntityRemote(
    @SerializedName("list") val weatherList : List<WeatherListRemote>,
    @SerializedName("city") val city : CityRemote)

data class CityRemote (val name : String)

data class WeatherListRemote(
    @SerializedName("dt_txt") val time : String,
    @SerializedName("main") val weatherTemp : WeatherTempRemote)

data class WeatherTempRemote (val temp : Double)



