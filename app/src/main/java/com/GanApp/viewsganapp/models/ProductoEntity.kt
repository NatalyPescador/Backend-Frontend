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

        @SerializedName("raza")
        val raza: String? = null,

        @SerializedName("sexo")
        val sexo: String? = null,

        @SerializedName("uom")
        val uom: String? = null,

        @SerializedName("edad")
        val edad: String? = null,

        @SerializedName("cantidad")
        val cantidad: String? = null,

        @SerializedName("departamento")
        val departamento: String? = null,

        @SerializedName("municipio")
        val municipio: String? = null,

        @SerializedName("imagen")
        val imagen: String? = null,

        @SerializedName("tipoServicioId")
        val tipoServicioId: Long,

        @SerializedName("categoriaId")
        val categoriaId: Long,

        @SerializedName("usuarioId")
        val usuarioId: Long,

)
