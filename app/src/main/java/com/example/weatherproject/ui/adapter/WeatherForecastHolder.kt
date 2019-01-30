package com.example.weatherproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.weatherproject.data.WeatherTempEntity
import kotlinx.android.synthetic.main.item_weather_forecast.view.*

class WeatherForecastHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(weatherTemp: WeatherTempEntity): Unit = with(itemView) {
        dateWeatherForecast.text = weatherTemp.time
        temperature.text = weatherTemp.temp.toString() + "°"

    }
}