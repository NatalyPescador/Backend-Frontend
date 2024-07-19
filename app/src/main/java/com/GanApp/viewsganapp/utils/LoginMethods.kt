package com.GanApp.viewsganapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.GanApp.viewsganapp.models.LoginDto
import com.GanApp.viewsganapp.models.UserLoginDto

fun saveLoginData(context: Context, loginDto: LoginDto) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("jwt_token", loginDto.token)
    editor.putString("jwt_expiration_time", loginDto.expirationTime)
    editor.apply()
    Log.d("saveLoginData", "Token guardado: ${loginDto.token}, Expiración: ${loginDto.expirationTime}")
}

fun saveUserData(context: Context, userData: UserLoginDto?) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    userData?.let {
        editor.putLong("user_id", it.userId)
        editor.putString("user_nombre_completo", it.nombreCompleto)
        editor.putString("user_correo", it.correo)
        editor.putString("user_numero_telefono", it.numeroTelefono)
    }
    editor.apply()
    Log.d("saveUserData", "Datos del usuario guardados: $userData")
}

fun getUserData(context: Context): UserLoginDto? {
    val sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("user_id", -1)
    val nombreCompleto = sharedPreferences.getString("user_nombre_completo", null)
    val correo = sharedPreferences.getString("user_correo", null)
    val numeroTelefono = sharedPreferences.getString("user_numero_telefono", null)

    return if (userId != -1L && nombreCompleto != null && correo != null && numeroTelefono != null) {
        UserLoginDto(
            userId = userId,
            nombreCompleto = nombreCompleto,
            correo = correo,
            numeroTelefono = numeroTelefono
        )
    } else {
        null
    }
}