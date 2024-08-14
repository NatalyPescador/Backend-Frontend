package com.GanApp.viewsganapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TokenManager {

    private const val PREFS_NAME = "MyAppPrefs"
    private const val TOKEN_KEY = "jwt_token"
    private const val EXPIRATION_TIME_KEY = "jwt_expiration_time"

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun getToken(context: Context): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val token = sharedPreferences.getString(TOKEN_KEY, null)
        Log.d("TokenManager-getToken", "Get token: $token")
        return token
    }

    fun getExpirationTime(context: Context): LocalDateTime? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val expirationTimeString = sharedPreferences.getString(EXPIRATION_TIME_KEY, null) ?: return null
        return try {
            LocalDateTime.parse(expirationTimeString, dateTimeFormatter).also {
                Log.d("TokenManager", "Hora de expiración obtenida: $it")
            }
        } catch (e: Exception) {
            Log.e("TokenManager", "Error parsing expiration time", e)
            null
        }
    }

    fun resetToken(context: Context) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().clear().apply()
        Log.d("TokenManager", "Token and user data reset")
    }

}