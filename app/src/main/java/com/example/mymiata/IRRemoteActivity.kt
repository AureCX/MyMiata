package com.example.mymiata

import android.content.Context
import android.content.Intent
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.widget.Button
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
        val speedPlusButton: Button = findViewById(R.id.button_speed_plus)
        val modePlusButton: Button = findViewById(R.id.button_mode_plus)
        val speedMinusButton: Button = findViewById(R.id.button_speed_minus)
        val colorPlusButton: Button = findViewById(R.id.button_color_plus)
        val demoButton: Button = findViewById(R.id.button_demo)
        val modeMinusButton: Button = findViewById(R.id.button_mode_minus)
        val colorMinusButton: Button = findViewById(R.id.button_color_minus)
        val brightPlusButton: Button = findViewById(R.id.button_bright_plus)
        val brightMinusButton: Button = findViewById(R.id.button_bright_minus)
        val backButton: Button = findViewById(R.id.button_back)
        val whiteButton: Button = findViewById(R.id.white_button)
        val redButton: Button = findViewById(R.id.red_button)
        val greenButton: Button = findViewById(R.id.green_button)
        val darkBlueButton: Button = findViewById(R.id.dark_blue_button)
        val yellowButton: Button = findViewById(R.id.yellow_button)
        val oceanBlueButton: Button = findViewById(R.id.ocean_blue_button)
        val purpleButton: Button = findViewById(R.id.purple_button)

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

        whiteButton.setOnClickListener {
            // Handle white button click
            showToast("White button clicked")
            // Implement your action here
        }

        redButton.setOnClickListener {
            // Handle red button click
            showToast("Red button clicked")
            // Implement your action here
        }

        greenButton.setOnClickListener {
            // Handle green button click
            showToast("Green button clicked")
            // Implement your action here
        }

        darkBlueButton.setOnClickListener {
            // Handle dark blue button click
            showToast("Dark Blue button clicked")
            // Implement your action here
        }

        yellowButton.setOnClickListener {
            // Handle yellow button click
            showToast("Yellow button clicked")
            // Implement your action here
        }

        oceanBlueButton.setOnClickListener {
            // Handle ocean blue button click
            showToast("Ocean Blue button clicked")
            // Implement your action here
            }

        purpleButton.setOnClickListener {
            // Handle purple button click
            showToast("Purple button clicked")
            // Implement your action here
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish the current activity to remove it from the back stack
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
        val backButton: Button = findViewById(R.id.button_back)
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
