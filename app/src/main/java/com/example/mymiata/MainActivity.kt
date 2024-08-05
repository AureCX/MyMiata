package com.example.mymiata

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyMiata) // Set the main theme after splash screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Now you can access your views using findViewById
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val carImageView = findViewById<ImageView>(R.id.carImageView)
        val carLogoImageView = findViewById<ImageView>(R.id.carLogoImageView)
        val mapView = findViewById<MapView>(R.id.mapView)

        // Set welcome message
        val username = "User" // Replace with dynamic username retrieval logic
        welcomeTextView.text = getString(R.string.welcome_username, username)

        // Initialize map view
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            val carLocation = LatLng(-34.0, 151.0) // Replace with dynamic car location data
            googleMap.addMarker(MarkerOptions().position(carLocation).title("Car Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15f))
        }
    }
}
