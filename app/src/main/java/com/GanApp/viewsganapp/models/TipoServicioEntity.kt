package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class TipoServicioEntity(

    @SerializedName("tipoServicioId")
    val tipoServicioId: Long,

    @SerializedName("nombre")
    val nombre: String? = null,

)
