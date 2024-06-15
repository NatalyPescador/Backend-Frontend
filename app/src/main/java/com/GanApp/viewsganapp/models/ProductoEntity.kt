package com.GanApp.viewsganapp.models

import com.google.gson.annotations.SerializedName

data class ProductoEntity(

        @SerializedName("productoId")
        val prodcutoId: Long,

        @SerializedName("nombre")
        val nombre: String? = null,

        @SerializedName("precio")
        val precio: Int,

        @SerializedName("descripcion")
        val descripcion: String? = null,

        @SerializedName("imagen")
        val imagen: String? = null,

        @SerializedName("tipoServicioId")
        val tipoServicioId: Long,

        @SerializedName("categoriaId")
        val categoriaId: Long,

        @SerializedName("usuarioId")
        val usuarioId: Long,

)
