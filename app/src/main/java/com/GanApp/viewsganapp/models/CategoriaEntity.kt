package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class CategoriaEntity(

    @SerializedName("categoriaId")
    val categoriaId: Long,

    @SerializedName("nombre")
    val nombre: String? = null,

    @SerializedName("tipoServicioId")
    val tipoServicioId: Long? = null

)
