package com.GanApp.viewsganapp.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


// Data class representing a product
data class Producto(
    val tipoServicioId: Long,
    val nombre: String,
    val precio: Double,
    val imagen: String,
    val usuarioId: Long,
    val categoriaId: Long

)

// Datos de muestra para demostración
val productList = listOf(
    Producto(1, "Producto 1", 100.00, "imagen1", 1, 1),
    Producto(2, "Producto 2", 200.00, "imagen2", 2, 1),
    Producto(3, "Producto 3", 300.00, "imagen3", 3, 1),
    Producto(1, "Producto 1", 100.00, "imagen1", 1, 1),
    Producto(2, "Producto 2", 200.00, "imagen2", 2, 1),
    Producto(3, "Producto 3", 300.00, "imagen3", 3, 1),
    // Add more products as needed
)


        @Composable
        fun Catalogo(productos: List<Producto>) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(productos.size / 3) { rowIndex ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    ) {
                        for (i in 0 until 3) {
                            val index = rowIndex * 3 + i
                            if (index < productos.size) {
                                Tarjeta(producto =productos[index] )
                            }
                        }
                    }
                }
            }
        }


    @Composable
    fun Tarjeta(producto: Producto) {
        Surface(
            modifier = Modifier
                .padding(end = 8.dp)
                //.width(120.dp)
                .height(200.dp),
            shape = RoundedCornerShape(8.dp),
            //elevation = 4.dp
        ) {
            Column(
                modifier = Modifier.clickable { /* Handle click event */ }
            ) {
                /*Image(
                    painter = painterResource(id = R.drawable.promocion_icn),
                    //contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Crop
                )*/
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
                    text = "Category: ${producto.categoriaId}",
                    //style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

@Composable
fun CatalogoPrincipal(navController: NavController) {
    // Usage
    //@Composable
    //fun CatalogoScreen() {
        Catalogo(productos = productList)
    //}
}
