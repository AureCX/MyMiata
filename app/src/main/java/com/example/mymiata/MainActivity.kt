package com.example.mymiata

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog


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
        val button: Button = findViewById(R.id.button)
        val bubbleMenu: LinearLayout = findViewById(R.id.bubbleMenu)

        button.setOnClickListener {
            if (bubbleMenu.visibility == View.GONE) {
                bubbleMenu.visibility = View.VISIBLE
            } else {
                bubbleMenu.visibility = View.GONE
            }
        }
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
private fun showInputDialog(bubbleText: TextView) {
    // Inflate the dialog with a custom layout
    val inflater: LayoutInflater = layoutInflater
    val dialogView: View = inflater.inflate(R.layout.dialog_input, null)

    // Create an AlertDialog
    val builder: AlertDialog.Builder = AlertDialog.Builder(this)
    builder.setTitle("Enter Text")
    builder.setView(dialogView)

    // Get the EditText from the custom layout
    val inputEditText: EditText = dialogView.findViewById(R.id.inputEditText)

    // Set up the buttons
    builder.setPositiveButton("OK") { _, _ ->
        val userInput = inputEditText.text.toString()
        bubbleText.text = userInput
    }
    builder.setNegativeButton("Cancel") { dialog, _ ->
        dialog.cancel()
    }

    // Show the dialog
    builder.show()
}

