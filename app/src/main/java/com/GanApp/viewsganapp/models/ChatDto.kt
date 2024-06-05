package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class ChatDto(

    val productId: Long,
    val userId: Long,
    val receiverId: Long,
)
