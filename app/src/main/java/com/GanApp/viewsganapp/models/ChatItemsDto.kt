package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChatItemsDto(
    val chatId: Long,
    val userId: Long,
    val nombreUsuario: String,
    val nombreReceiver: String,
    val imagen: String
)
