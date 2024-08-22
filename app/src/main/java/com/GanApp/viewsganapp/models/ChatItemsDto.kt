package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChatItemsDto(
    val chatId: Long,
    val nombreCompleto: String,
    val imagen: String
)
