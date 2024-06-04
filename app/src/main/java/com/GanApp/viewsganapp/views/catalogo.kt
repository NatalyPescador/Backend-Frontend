package com.GanApp.viewsganapp.views

import android.util.Log
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch

@Composable
fun CatalogoPrincipal(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    val products by remember { mutableStateOf(productViewModel.products) }

    Column {
        Button(
            onClick = { /* Handle filter button click */ },
            colors = ButtonDefaults.buttonColors(
                Color(10, 191, 4)
            ),
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Call, contentDescription = "telefono")
            Text(text = "Filtrar", fontSize = 21.sp)
        }

        Catalogo(productos = products)
    }
}

@Composable
fun Catalogo(productos: List<ProductoEntity>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(productos.size / 2) { rowIndex ->
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until 2) {
                    val index = rowIndex * 2 + i
                    if (index < productos.size) {
                        Tarjeta(producto = productos[index])
                    }
                }
            }
        }
    }
}

@Composable
fun Tarjeta(producto: ProductoEntity) {
    val filename = producto.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = "http://192.168.1.64:8080/GanApp/uploads/$filename"

    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(120.dp)
            .height(200.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.clickable { /* Handle click event */ }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = producto.nombre ?: "Sin nombre",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.nombre ?: "Sin nombre",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "$${producto.precio}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "por ${producto.usuarioId}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Categoría: ${producto.categoriaId}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
