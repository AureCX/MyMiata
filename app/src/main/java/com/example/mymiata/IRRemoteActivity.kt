package com.example.mymiata

import android.content.Context
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class IRRemoteActivity : AppCompatActivity() {

    private lateinit var irManager: ConsumerIrManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ir_remote)

        irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as ConsumerIrManager

        val powerButton: ImageButton = findViewById(R.id.button_power)
        val speedPlusButton: ImageButton = findViewById(R.id.button_speed_plus)
        val modePlusButton: ImageButton = findViewById(R.id.button_mode_plus)
        val speedMinusButton: ImageButton = findViewById(R.id.button_speed_minus)
        val colorPlusButton: ImageButton = findViewById(R.id.button_color_plus)
        val demoButton: ImageButton = findViewById(R.id.button_demo)
        val modeMinusButton: ImageButton = findViewById(R.id.button_mode_minus)
        val colorMinusButton: ImageButton = findViewById(R.id.button_color_minus)
        val brightPlusButton: ImageButton = findViewById(R.id.button_bright_plus)
        val brightMinusButton: ImageButton = findViewById(R.id.button_bright_minus)
        val backButton: ImageButton = findViewById(R.id.button_back)

        powerButton.setOnClickListener {
            sendIrSignal(0x10EFD827) // Replace with your device's IR code
        }

        powerButton.setOnClickListener {
            // Handle power button click
            showToast("Power button clicked")
            // Implement your action here
        }

        speedPlusButton.setOnClickListener {
            // Handle speed plus button click
            showToast("Speed Plus button clicked")
            // Implement your action here
        }

        modePlusButton.setOnClickListener {
            // Handle mode plus button click
            showToast("Mode Plus button clicked")
            // Implement your action here
        }

        speedMinusButton.setOnClickListener {
            // Handle speed minus button click
            showToast("Speed Minus button clicked")
            // Implement your action here
        }

        colorPlusButton.setOnClickListener {
            // Handle color plus button click
            showToast("Color Plus button clicked")
            // Implement your action here
        }

        demoButton.setOnClickListener {
            // Handle demo button click
            showToast("Demo button clicked")
            // Implement your action here
        }

        modeMinusButton.setOnClickListener {
            // Handle mode minus button click
            showToast("Mode Minus button clicked")
            // Implement your action here
        }

        colorMinusButton.setOnClickListener {
            // Handle color minus button click
            showToast("Color Minus button clicked")
            // Implement your action here
        }

        brightPlusButton.setOnClickListener {
            // Handle bright plus button click
            showToast("Bright Plus button clicked")
            // Implement your action here
        }

        brightMinusButton.setOnClickListener {
            // Handle bright minus button click
            showToast("Bright Minus button clicked")
            // Implement your action here
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
        val backButton: ImageButton = findViewById(R.id.button_back)
        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}
