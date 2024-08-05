// SplashActivity.kt
package com.example.mymiata

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
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.splash_video)

        videoView.setVideoURI(videoUri)
        videoView.setOnCompletionListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        videoView.start()
    }
    private fun isUserAuthenticated(): Boolean {
        // Implement your logic to check if the user is authenticated
        // For example, check if a user token is saved in SharedPreferences
        val sharedPref = getSharedPreferences("MyApp", MODE_PRIVATE)
        return sharedPref.contains("userToken")
    }
}



