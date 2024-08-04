package com.example.mymiata

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.anastr.speedviewlib.SpeedView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Now you can access your views using findViewById
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val fuelGauge = findViewById<SpeedView>(R.id.fuelGauge)
        mapView = findViewById(R.id.mapView)

        // Set welcome message
        val username = "User" // Replace with dynamic username retrieval logic
        welcomeTextView.text = "Welcome $username!"

        // Initialize fuel gauge
        fuelGauge.speedTo(50F) // Example fuel level, replace with dynamic data

        // Initialize map view
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map
        val carLocation = LatLng(-34.0, 151.0) // Replace with dynamic car location data
        googleMap.addMarker(MarkerOptions().position(carLocation).title("Car Location"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15f))
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
}
