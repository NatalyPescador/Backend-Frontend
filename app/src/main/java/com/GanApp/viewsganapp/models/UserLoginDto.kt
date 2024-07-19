package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class UserLoginDto(

    @SerializedName("userId")
    val userId: Long,
    @SerializedName("nombreCompleto")
    val nombreCompleto: String,
    @SerializedName("correo")
    val correo: String,
    @SerializedName("numeroTelefono")
    val numeroTelefono: String

)
