package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class MessageDto(

    @SerializedName("chatId")
    val chatId: Long,

    @SerializedName("senderId")
    val senderId: Long,

    @SerializedName("receiverId")
    val receiverId: Long,

    @SerializedName("message")
    val message: String,

)
