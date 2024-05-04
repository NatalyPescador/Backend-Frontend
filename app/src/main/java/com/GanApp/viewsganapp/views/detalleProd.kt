package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.GanApp.viewsganapp.R


data class Prod(
    val tipoServicioId: Long,
    val nombre: String,
    val precio: Double,
    val imageUrl: Int,
    val usuarioId: Long,
    val categoriaId: Long,
    val descripcion: String
)

val Detalle = listOf(
    Producto(1, "Producto 1", 100.00, R.drawable.gmail_logo, 1, 1, "Descripción"),
)

@Composable
fun Caracteristica(producto: Producto) {
    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(120.dp)
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
        //elevation = 4.dp
    ) {
        Column(
            modifier = Modifier.clickable { /* Handle click event */ }
        ) {
            Image(
                painter = painterResource(id = R.drawable.gmail_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),


                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.nombre,
                //style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "$${producto.precio}",
                //style = MaterialTheme.typography.subtitle1,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "por ${producto.usuarioId}",
                //style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Categoría: ${producto.categoriaId}",
                //style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Descripción: ${producto.descripcion}",
                //style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

