package com.example.mymiata

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
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
import android.widget.Toast
import androidx.compose.ui.platform.ComposeView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_MyMiata) // Set the main theme after splash screen
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Now you can access your views using findViewById
        val welcomeTextView = findViewById<TextView>(R.id.welcomeTextView)
        val licensePlateTextView: TextView = findViewById(R.id.licensePlateTextView)
        val carModelTextView: Button = findViewById(R.id.carModelTextView)
        // val carImageView = findViewById<ImageView>(R.id.carImageView) OLD CAR VISUAL
        val carModelComposeView = findViewById<ComposeView>(R.id.carModelComposeView)
        val carLogoImageView = findViewById<ImageView>(R.id.carLogoImageView)
        val mapView = findViewById<MapView>(R.id.mapView)
        val button: Button = findViewById(R.id.license_plate_button)
        val bubbleMenu: LinearLayout = findViewById(R.id.bubbleMenu)
        val bubbleMenu2: LinearLayout = findViewById(R.id.bubbleMenu2)
        val irRemoteButton: Button = findViewById(R.id.irRemoteButton)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        val sharedPref = getSharedPreferences("MyMiataPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", "User")
        val licensePlate = sharedPref.getString("licensePlate", "")
        val phoneNumber = sharedPref.getString("phone_number", "") // Retrieve the stored phone number

        carModelComposeView.setContent {
            CarModelView()
        }

        welcomeTextView.text = "Welcome ${username ?: "User"}!"
        licensePlateTextView.text = licensePlate

        irRemoteButton.setOnClickListener {
            val intent = Intent(this, IRRemoteActivity::class.java)
            startActivity(intent)
        }

        carModelTextView.setOnClickListener {
            if (bubbleMenu2.visibility == View.GONE) {
                bubbleMenu2.visibility = View.VISIBLE
            } else {
                bubbleMenu2.visibility = View.GONE
            }
        }

        button.setOnClickListener {
            if (bubbleMenu.visibility == View.GONE) {
                bubbleMenu.visibility = View.VISIBLE
            } else {
                bubbleMenu.visibility = View.GONE
            }
        }

        logoutButton.setOnClickListener {
            logout()
        }
        val dialButton: Button = findViewById(R.id.button_dial) // Ensure this ID matches your XML
        dialButton.setOnClickListener {
            openPhoneDialer(phoneNumber)
        }
        // Initialize map view
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync { googleMap ->
            val carLocation = LatLng(-34.0, 151.0) // Replace with dynamic car location data
            googleMap.addMarker(MarkerOptions().position(carLocation).title("Car Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(carLocation, 15f))
        }
    }

    private fun logout() {
        val sharedPref = getSharedPreferences("MyMiataPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            remove("username")
            remove("licensePlate")
            apply()

        }
        Toast.makeText(this, "Successfully logged out", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun openPhoneDialer(phoneNumber: String?) {
        if (!phoneNumber.isNullOrEmpty()) {
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$phoneNumber")
            startActivity(dialIntent)
        } else {
            Toast.makeText(this, "Phone number not available", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun showInputDialog(activity: AppCompatActivity, bubbleText: TextView) {
    // Inflate the dialog with a custom layout
    val inflater: LayoutInflater = activity.layoutInflater
    val dialogView: View = inflater.inflate(R.layout.dialog_input, null)

    // Create an AlertDialog
    val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
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

