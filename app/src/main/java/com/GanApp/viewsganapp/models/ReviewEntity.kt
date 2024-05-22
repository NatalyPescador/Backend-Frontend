package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class ReviewEntity(

    @SerializedName("resenas_id")
    val resenasId: Long,

    @SerializedName("resenna")
    val resena: String,

    @SerializedName("producto_id")
    val productoId: String,

    @SerializedName("usuario_id")
    val usuarioId: String,
)
