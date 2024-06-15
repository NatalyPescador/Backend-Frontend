package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class ChatEntity(

    @SerializedName("chatId")
    val chatId: Long,

    @SerializedName("productId")
    val productId: Long,

    @SerializedName("userId")
    val userId: Long,

    @SerializedName("receiverId")
    val receiverId: Long,

    @SerializedName("createdAt")
    val createdAt: String,

    @SerializedName("updatedAt")
    val updatedAt: String,

)
