package com.example.weatherproject.remote

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherForecastApi {

    @GET("forecast")
    fun getWeatherForecastWithLatLng(@Query("lat")latitude : Double, @Query("lon") longitude : Double,
                                     @Query("units") units: String,@Query("APPID") apiKey : String)
    : Single<WeatherForecastEntityRemote>

}

