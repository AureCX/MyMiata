package com.example.mymiata

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginButton = findViewById<Button>(R.id.loginButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        loginButton.setOnClickListener {
            val loginUrl = "https://yourwebsite.com/login"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(loginUrl))
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val signUpUrl = "https://yourwebsite.com/signup"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(signUpUrl))
            startActivity(intent)
        }
    }
}
