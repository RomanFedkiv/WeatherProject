package com.example.weatherproject.ui


import android.content.Intent
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weatherproject.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.andref.rx.network.RxNetwork
import kotlinx.android.synthetic.main.activity_maps.*
import rx.subscriptions.CompositeSubscription

class MapsActivity : AppCompatActivity(),  OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var latlng: LatLng
    private val compositeSubscription =  CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        button.setOnClickListener {
            val intent = Intent(this, WeatherForecastActivity::class.java)
            intent.putExtra(LatLngKey, latlng)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        compositeSubscription.add(
            RxNetwork.connectivityChanges(this, connectivityManager)
                .subscribe {
                        t -> if (!t!!) Toast.makeText(this@MapsActivity,"not internet connection",Toast.LENGTH_LONG).show() })
    }

    override fun onPause() {
        super.onPause()
        compositeSubscription.unsubscribe()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        latlng = LatLng(-34.0, 151.0)
        var marker = mMap.addMarker(MarkerOptions().position(latlng))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng))

        mMap.setOnMapClickListener {
            marker.remove()
            latlng = it
            marker = mMap.addMarker(MarkerOptions().position(latlng))
        }
    }

    companion object {
         const val LatLngKey = "LatLng"
    }
}
