package com.example.mymiata

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity

class IRRemoteActivity : AppCompatActivity() {

    /** Null on devices without an IR service (most phones). */
    private var irManager: ConsumerIrManager? = null

    /**
     * Remote buttons and their 32-bit NEC codes. A null code means the code for that
     * function hasn't been captured yet: fill them in from your LED controller's remote.
     */
    private val buttons: List<RemoteButton> = listOf(
        RemoteButton(R.id.button_power, R.string.button_power, 0x10EFD827L),
        RemoteButton(R.id.button_speed_plus, R.string.button_speed_plus, null),
        RemoteButton(R.id.button_speed_minus, R.string.button_speed_minus, null),
        RemoteButton(R.id.button_mode_plus, R.string.button_mode_plus, null),
        RemoteButton(R.id.button_mode_minus, R.string.button_mode_minus, null),
        RemoteButton(R.id.button_color_plus, R.string.button_color_plus, null),
        RemoteButton(R.id.button_color_minus, R.string.button_color_minus, null),
        RemoteButton(R.id.button_bright_plus, R.string.button_bright_plus, null),
        RemoteButton(R.id.button_bright_minus, R.string.button_bright_minus, null),
        RemoteButton(R.id.button_demo, R.string.button_demo, null),
        RemoteButton(R.id.white_button, R.string.color_white, null),
        RemoteButton(R.id.red_button, R.string.color_red, null),
        RemoteButton(R.id.green_button, R.string.color_green, null),
        RemoteButton(R.id.dark_blue_button, R.string.color_dark_blue, null),
        RemoteButton(R.id.yellow_button, R.string.color_yellow, null),
        RemoteButton(R.id.ocean_blue_button, R.string.color_ocean_blue, null),
        RemoteButton(R.id.purple_button, R.string.color_purple, null),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ir_remote)

        irManager = getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager

        for (button in buttons) {
            findViewById<View>(button.viewId).setOnClickListener { onRemoteButton(button) }
        }
        // MainActivity is still on the back stack: just return to it.
        findViewById<View>(R.id.button_back).setOnClickListener { finish() }
    }

    private fun onRemoteButton(button: RemoteButton) {
        val label = getString(button.label)
        if (button.necCode == null) {
            showToast(getString(R.string.ir_code_not_configured, label))
        } else {
            sendIrSignal(button.necCode, label)
        }
    }

    private fun sendIrSignal(necCode: Long, label: String) {
        val manager = irManager
        if (manager == null || !manager.hasIrEmitter()) {
            showToast(getString(R.string.no_ir_emitter))
            return
        }
        try {
            manager.transmit(NecIrEncoder.CARRIER_FREQUENCY_HZ, NecIrEncoder.encode(necCode))
            showToast(getString(R.string.ir_signal_sent, label))
        } catch (e: RuntimeException) {
            showToast(getString(R.string.ir_signal_failed, e.message.orEmpty()))
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private data class RemoteButton(
        @IdRes val viewId: Int,
        @StringRes val label: Int,
        val necCode: Long?,
    )
}
