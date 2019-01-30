package com.example.weatherproject.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.arch.lifecycle.Observer
import android.net.ConnectivityManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.weatherproject.R
import com.example.weatherproject.data.WeatherForecastEntity
import com.example.weatherproject.ui.adapter.WeatherForecastAdapter
import com.example.weatherproject.viewModel.WeatherForecastViewModel
import com.google.android.gms.maps.model.LatLng
import io.andref.rx.network.RxNetwork
import kotlinx.android.synthetic.main.activity_weather_forecast.*
import org.koin.android.viewmodel.ext.android.viewModel
import rx.subscriptions.CompositeSubscription

class WeatherForecastActivity : AppCompatActivity(){


    private val viewModel by viewModel<WeatherForecastViewModel>()

    private val compositeSubscription =  CompositeSubscription()

    private lateinit var latLng : LatLng

    private var internetConnection = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_forecast)

        weatherRecyclerView.layoutManager = LinearLayoutManager(this)
        weatherRecyclerView.adapter = WeatherForecastAdapter()

        latLng = intent.getParcelableExtra<LatLng>(MapsActivity.LatLngKey)

        viewModel.getWeatherForecast().observe(this, Observer<WeatherForecastEntity>
        { t ->
            if (t!=null) {
                progressBar.visibility = View.INVISIBLE
                if (t.city != null) cityTextView.text = t.city
                else cityTextView.text = "Unknown Place"
                tempNowTextView.text = t.weatherTempList.get(0).temp.toString() + "°"
                (weatherRecyclerView.adapter as WeatherForecastAdapter).updateList(t.weatherTempList)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        compositeSubscription.add(
            RxNetwork.connectivityChanges(this, connectivityManager)
                .subscribe {
                        t -> if (!t!!) {Toast.makeText(this,"not internet connection", Toast.LENGTH_LONG).show()
                internetConnection = false}
                else {
                    internetConnection = true
                    if (internetConnection) {
                        progressBar.visibility = View.VISIBLE
                        viewModel.updateWeatherForecast(latLng.latitude, latLng.longitude)
                    }
                }})
    }

    override fun onPause() {
        super.onPause()
        compositeSubscription.unsubscribe()

    }


}
