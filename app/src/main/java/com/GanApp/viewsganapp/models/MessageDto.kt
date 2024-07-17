package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class MessageDto(

    @SerializedName("chatId")
    val chatId: Long,

    @SerializedName("message")
    val message: String,

    @SerializedName("senderId")
    val senderId: Long,

    @SerializedName("status")
    val status: String,

)
