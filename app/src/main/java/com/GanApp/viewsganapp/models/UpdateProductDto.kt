package com.GanApp.viewsganapp.models

data class UpdateProductDto(
    val precio: String,
    val descripcion: String,
    val raza: String,
    val uom: String,
    val edad: String,
    val cantidad: String
)