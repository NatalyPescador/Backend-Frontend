package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

class UserDto(
    @SerializedName("userId") val userId: Long,
    @SerializedName("nombreCompleto") var nombreCompleto: String,
    @SerializedName("correo") var correo: String,
    @SerializedName("numeroTelefono") var numeroTelefono: String
) {


    val id: Long = 1

    fun copy(
        userId: Long = this.userId,
        nombreCompleto: String = this.nombreCompleto,
        correo: String = this.correo,
        numeroTelefono: String = this.numeroTelefono
    ): UserDto {
        return UserDto(
            userId = userId,
            nombreCompleto = nombreCompleto,
            correo = correo,
            numeroTelefono = numeroTelefono
        )
    }
}