package com.example.mymiata

import android.os.Bundle
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val carLogoImageView = findViewById<ImageView>(R.id.carLogoImageView)
        val carImageView = findViewById<ImageView>(R.id.carImageView)
        mapView = findViewById(R.id.mapView)

        val username = "User" // Replace with dynamic username retrieval logic
        welcomeTextView.text = getString(R.string.welcome_username, username)

        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
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
