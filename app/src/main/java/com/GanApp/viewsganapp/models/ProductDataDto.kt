package com.GanApp.viewsganapp.models

data class ProductDataDto(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val raza: String,
    val sexo: String,
    val uom: String,
    val edad: String,
    val cantidad: String,
    val departamento: String,
    val municipio: String,
    val imagen: String,
    val tipoServicioId: Long,
    val categoriaId: Long,
    val usuarioId: Long
)
