package com.example.mymiata

import android.content.Context
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IRRemoteActivity : AppCompatActivity() {

    private lateinit var irManager: ConsumerIrManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ir_remote)

        irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

        val powerButton: Button = findViewById(R.id.powerButton)
        val volumeUpButton: Button = findViewById(R.id.volumeUpButton)
        val volumeDownButton: Button = findViewById(R.id.volumeDownButton)
        val channelUpButton: Button = findViewById(R.id.channelUpButton)
        val channelDownButton: Button = findViewById(R.id.channelDownButton)

        powerButton.setOnClickListener {
            sendIrSignal(0x10EFD827) // Replace with your device's IR code
        }

        volumeUpButton.setOnClickListener {
            sendIrSignal(0x10EF58A7) // Replace with your device's IR code
        }

        volumeDownButton.setOnClickListener {
            sendIrSignal(0x10EFA857) // Replace with your device's IR code
        }

        channelUpButton.setOnClickListener {
            sendIrSignal(0x10EF807F) // Replace with your device's IR code
        }

        channelDownButton.setOnClickListener {
            sendIrSignal(0x10EF40BF) // Replace with your device's IR code
        }
    }

    private fun sendIrSignal(irCode: Int) {
        if (irManager.hasIrEmitter()) {
            val frequency = 38000 // Set the frequency of your IR signal
            val pattern = intArrayOf(9000, 4500, 560, 560) // Example pattern, replace with actual pattern

            try {
                irManager.transmit(frequency, pattern)
                Toast.makeText(this, "IR Signal Sent: $irCode", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to send IR signal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "No IR emitter found on this device", Toast.LENGTH_SHORT).show()
        }
        val backToMainButton: Button = findViewById(R.id.backToMainButton)
        backToMainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: close the current activity
        }
    }

}
