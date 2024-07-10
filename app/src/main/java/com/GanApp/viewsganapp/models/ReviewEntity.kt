package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class ReviewEntity(

    @SerializedName("resenasId")
    val resenasId: Long,

    @SerializedName("resena")
    val resena: String,

    @SerializedName("productoId")
    val productoId: Long,

    @SerializedName("usuarioId")
    val usuarioId: String,
)
