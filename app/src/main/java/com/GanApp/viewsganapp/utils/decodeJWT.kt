package com.GanApp.viewsganapp.utils

import com.GanApp.viewsganapp.models.UserLoginDto
import com.auth0.android.jwt.JWT

fun decodeJWT(token: String): UserLoginDto? {
    return try {
        val jwt = JWT(token)
        val userId = jwt.getClaim("userId").asLong()
        val nombreCompleto = jwt.getClaim("nombreCompleto").asString()
        val correo = jwt.getClaim("correo").asString()
        val numeroTelefono = jwt.getClaim("numeroTelefono").asString()

        if (userId != null && nombreCompleto != null && correo != null && numeroTelefono != null) {
            UserLoginDto(
                userId = userId,
                nombreCompleto = nombreCompleto,
                correo = correo,
                numeroTelefono = numeroTelefono
            )
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}