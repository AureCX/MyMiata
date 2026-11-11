package com.example.mymiata

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
            val username = usernameEditText.text.toString().trim()
            val licensePlate = licensePlateEditText.text.toString().trim().uppercase()
            val phoneNumber = phoneNumberEditText.text.toString().trim()

            if (username.isEmpty()) usernameEditText.error = getString(R.string.error_required)
            if (licensePlate.isEmpty()) licensePlateEditText.error = getString(R.string.error_required)
            if (username.isEmpty() || licensePlate.isEmpty()) return@setOnClickListener

            UserPrefs(this).save(username, licensePlate, phoneNumber)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
