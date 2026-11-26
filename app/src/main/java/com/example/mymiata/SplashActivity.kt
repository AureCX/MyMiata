package com.example.mymiata

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private var navigated = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val videoView: VideoView = findViewById(R.id.splashVideoView)
        videoView.setVideoURI("android.resource://$packageName/${R.raw.splash_video}".toUri())
        videoView.setOnCompletionListener { navigateToNextScreen() }
        // Never leave the user stuck on a blank screen if the video can't be played.
        videoView.setOnErrorListener { _, _, _ ->
            navigateToNextScreen()
            true
        }
        // Let the user skip the intro.
        videoView.setOnClickListener { navigateToNextScreen() }
        videoView.start()
    }

    private fun navigateToNextScreen() {
        if (navigated) return
        navigated = true
        val target = if (UserPrefs(this).isLoggedIn) MainActivity::class.java else LoginActivity::class.java
        startActivity(Intent(this, target))
        finish()
    }
}
