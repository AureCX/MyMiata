package com.example.mymiata

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var userPrefs: UserPrefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userPrefs = UserPrefs(this)
        if (!userPrefs.isLoggedIn) {
            openLogin()
            return
        }
        setContentView(R.layout.activity_main)

        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)
        val licensePlateTextView: TextView = findViewById(R.id.licensePlateTextView)
        val carModelButton: Button = findViewById(R.id.carModelTextView)
        val licensePlateButton: Button = findViewById(R.id.license_plate_button)
        val bubbleMenu: View = findViewById(R.id.bubbleMenu)
        val bubbleMenu2: View = findViewById(R.id.bubbleMenu2)
        val irRemoteButton: Button = findViewById(R.id.irRemoteButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)
        val dialButton: Button = findViewById(R.id.button_dial)
        mapView = findViewById(R.id.mapView)

        welcomeTextView.text = getString(R.string.welcome_username, userPrefs.username)
        licensePlateTextView.text = userPrefs.licensePlate

        irRemoteButton.setOnClickListener {
            startActivity(Intent(this, IRRemoteActivity::class.java))
        }
        carModelButton.setOnClickListener { bubbleMenu2.toggleVisibility() }
        licensePlateButton.setOnClickListener { bubbleMenu.toggleVisibility() }
        logoutButton.setOnClickListener { logout() }
        dialButton.setOnClickListener { openPhoneDialer(userPrefs.emergencyNumber) }

        // MapView requires every lifecycle callback to be forwarded (see overrides below).
        mapView.onCreate(savedInstanceState?.getBundle(MAP_VIEW_STATE_KEY))
        mapView.getMapAsync { googleMap ->
            // TODO: replace with the car's real location once a data source exists.
            val carLocation = LatLng(-34.0, 151.0)
            googleMap.addMarker(
                MarkerOptions().position(carLocation).title(getString(R.string.car_location))
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15f))
        }
    }

    private val mapReady get() = ::mapView.isInitialized

    override fun onStart() {
        super.onStart()
        if (mapReady) mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        if (mapReady) mapView.onResume()
    }

    override fun onPause() {
        if (mapReady) mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        if (mapReady) mapView.onStop()
        super.onStop()
    }

    override fun onDestroy() {
        if (mapReady) mapView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        if (mapReady) mapView.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (mapReady) {
            val mapState = Bundle()
            mapView.onSaveInstanceState(mapState)
            outState.putBundle(MAP_VIEW_STATE_KEY, mapState)
        }
    }

    private fun logout() {
        userPrefs.clear()
        Toast.makeText(this, R.string.logged_out, Toast.LENGTH_SHORT).show()
        openLogin()
    }

    private fun openLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun openPhoneDialer(phoneNumber: String?) {
        if (phoneNumber.isNullOrEmpty()) {
            Toast.makeText(this, R.string.phone_number_unavailable, Toast.LENGTH_SHORT).show()
            return
        }
        try {
            startActivity(Intent(Intent.ACTION_DIAL, "tel:${Uri.encode(phoneNumber)}".toUri()))
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, R.string.no_dialer, Toast.LENGTH_SHORT).show()
        }
    }

    private fun View.toggleVisibility() {
        isVisible = !isVisible
    }

    private companion object {
        const val MAP_VIEW_STATE_KEY = "map_view_state"
    }
}
