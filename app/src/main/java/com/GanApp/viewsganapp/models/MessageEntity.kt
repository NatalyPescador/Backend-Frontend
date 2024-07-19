package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class MessageEntity(

    @SerializedName("idMessage")
    val idMessage: Long,

    @SerializedName("chatId")
    val chatId: Long,

    @SerializedName("message")
    val message: String,

    @SerializedName("timeStamp")
    val timeStamp: LocalDateTime,

    @SerializedName("status")
    val status: String,

    @SerializedName("senderId")
    val senderId: Long
)
