package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class LoginDto(

    @SerializedName("token")
    val token: String,
    @SerializedName("expirationTime")
    val expirationTime: String,
    @SerializedName("errorMessage")
    val errorMessage: String

)
