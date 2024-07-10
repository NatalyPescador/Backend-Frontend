package com.GanApp.viewsganapp.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import java.text.NumberFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favoritos(navController: NavController) {
    val favoriteItems = listOf(
        ProductoEntity(1, "Producto 1", "Descripción 1", 10000.0, "url1"),
        ProductoEntity(2, "Producto 2", "Descripción 2", 20000.0, "url2"),
        ProductoEntity(3, "Producto 3", "Descripción 3", 30000.0, "url3"),
        ProductoEntity(4, "Producto 4", "Descripción 4", 40000.0, "url4")
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Favoritos", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("homePage") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(152, 255, 150),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Red
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            items(favoriteItems.chunked(2)) { rowItems ->
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    rowItems.forEach { item ->
                        Tarjeta(producto = item, navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Tarjeta(producto: ProductoEntity, navController: NavController) {
    val numberFormat = NumberFormat.getInstance(Locale("es", "CO")).apply {
        maximumFractionDigits = 0
    }

    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(150.dp)
            .height(230.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.clickable {
                navController.navigate("detalleProd/${producto.productoId}")
            }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = "http://10.175.145.205:8080/GanApp/uploads/${producto.imagen}"),
                contentDescription = producto.nombre ?: "Sin nombre",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.nombre ?: "Sin nombre",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${numberFormat.format(producto.precio)}",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontStyle = FontStyle.Italic
            )
        }
    }
}

data class ProductoEntity(
    val productoId: Int,
    val nombre: String?,
    val descripcion: String?,
    val precio: Double,
    val imagen:String?
)