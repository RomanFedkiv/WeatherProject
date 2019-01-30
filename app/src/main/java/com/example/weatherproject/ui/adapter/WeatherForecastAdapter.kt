package com.example.weatherproject.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.weatherproject.R
import com.example.weatherproject.data.WeatherTempEntity

class WeatherForecastAdapter: RecyclerView.Adapter<WeatherForecastHolder>(){

    private var listNews : List<WeatherTempEntity> = listOf()

    fun updateList(list : List<WeatherTempEntity>){
        listNews = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherForecastHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_weather_forecast, parent, false)
        return WeatherForecastHolder(view)
    }
    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: WeatherForecastHolder, position: Int) {
        holder.bind(listNews[position])
    }
}