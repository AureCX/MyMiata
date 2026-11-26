package com.example.mymiata

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

/** Single place for the locally stored user profile. */
class UserPrefs(context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    val username: String? get() = prefs.getString(KEY_USERNAME, null)
    val licensePlate: String? get() = prefs.getString(KEY_LICENSE_PLATE, null)
    val emergencyNumber: String? get() = prefs.getString(KEY_EMERGENCY_NUMBER, null)

    val isLoggedIn: Boolean get() = !username.isNullOrEmpty()

    fun save(username: String, licensePlate: String, emergencyNumber: String) {
        prefs.edit {
            putString(KEY_USERNAME, username)
            putString(KEY_LICENSE_PLATE, licensePlate)
            putString(KEY_EMERGENCY_NUMBER, emergencyNumber)
        }
    }

    fun clear() {
        prefs.edit { clear() }
    }

    private companion object {
        const val FILE_NAME = "MyMiataPrefs"
        const val KEY_USERNAME = "username"
        const val KEY_LICENSE_PLATE = "licensePlate"
        const val KEY_EMERGENCY_NUMBER = "phone_number"
    }
}
