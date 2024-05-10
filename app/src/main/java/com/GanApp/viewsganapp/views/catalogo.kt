package com.GanApp.viewsganapp.views

//import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R


// Data class representing a product
data class Producto(
    val tipoServicioId: Long,
    val nombre: String,
    val precio: Double,
    val imageUrl: Int,
    val usuarioId: Long,
    val categoriaId: Long

)

// Datos de muestra para demostración
val productList = listOf(
    Producto(1, "Producto 1", 100.00, R.drawable.gmail_logo, 1, 1),
    Producto(2, "Producto 2", 200.00, R.drawable.gmail_logo, 2, 1),
    Producto(3, "Producto 3", 300.00, R.drawable.gmail_logo, 3, 1),
    Producto(1, "Producto 1", 100.00, R.drawable.gmail_logo, 1, 1),
    Producto(2, "Producto 2", 200.00, R.drawable.gmail_logo, 2, 1),
    Producto(3, "Producto 3", 300.00, R.drawable.gmail_logo, 3, 1),
    // Add more products as needed
)


        @Composable
        fun Catalogo(productos: List<Producto>) {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            )
            {

                items(productos.size / 3) { rowIndex ->
                    Row(modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                        for (i in 0 until 2) {
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
                    text = "Category: ${producto.categoriaId}",
                    //style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }

@Composable
fun CatalogoPrincipal(navController: NavController) {
    Column {
        Button(
            onClick = { /* Handle filter button click */ },
            colors = ButtonDefaults.buttonColors(
                Color(10, 191, 4)
            ),
            modifier = Modifier.padding(16.dp)
        ) {

            Icon(imageVector = Icons.Default.Person, contentDescription = "telefono")

            Text(text = "Filtrar", fontSize = 21.sp)

        }

        Catalogo(productos = productList)

    }
}



