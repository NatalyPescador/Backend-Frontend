package com.GanApp.viewsganapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.GanApp.viewsganapp.models.ProductoEntity

@Composable
fun ProductItem(product: ProductoEntity) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp, pressedElevation = 4.dp, focusedElevation = 4.dp, hoveredElevation = 3.dp)// Establece la elevación por defecto en 2.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = product.nombre ?: "Sin nombre", style = MaterialTheme.typography.titleMedium)
            Text("Precio: ${product.precio}")
            Text("Descripción: ${product.descripcion ?: "Sin descripción"}")
            // Puedes añadir un componente de imagen si tienes una URL
        }
    }
}