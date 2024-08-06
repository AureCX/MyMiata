package com.example.mymiata

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView: VideoView = findViewById(R.id.splashVideoView)
        val videoUri = Uri.parse("android.resource://${packageName}/${R.raw.splash_video}")

        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            navigateToNextScreen()
        }
        videoView.start()
    }

    private fun navigateToNextScreen() {
        val sharedPref = getSharedPreferences("MyMiataPrefs", Context.MODE_PRIVATE)
        val username = sharedPref.getString("username", null)

        if (username != null) {
            // If username is saved, go directly to MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // Otherwise, go to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }
        finish()
    }
}
