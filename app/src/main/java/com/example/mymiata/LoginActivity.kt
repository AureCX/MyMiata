package com.example.mymiata

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val usernameEditText: EditText = findViewById(R.id.usernameEditText)
        val licensePlateEditText: EditText = findViewById(R.id.licensePlateEditText)
        val phoneNumberEditText: EditText = findViewById(R.id.phoneNumberEditText)
        val loginButton: Button = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val licensePlate = licensePlateEditText.text.toString()
            val phoneNumber = phoneNumberEditText.text.toString().trim()
            if (username.isNotEmpty() && licensePlate.isNotEmpty()) {
                // Save username to SharedPreferences
                val sharedPref = getSharedPreferences("MyMiataPrefs", Context.MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putString("username", username)
                    putString("licensePlate", licensePlate)
                    putString("phone_number", phoneNumber)
                    apply()
                }

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Handle empty username case, e.g., show a Toast message
            }
        }
    }
}