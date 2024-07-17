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
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Call
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale

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
            Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = "telefono")
            Text(text = "Filtrar", fontSize = 21.sp)
        }

        Catalogo(productos = products, navController = navController)
    }
}

@Composable
fun Catalogo(productos: List<ProductoEntity>, navController: NavController) {
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
                        Tarjeta(producto = productos[index], navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
fun Tarjeta(producto: ProductoEntity, navController: NavController) {
    val filename = producto.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = "http://10.175.145.214:8080/GanApp/uploads/$filename"
    val numberFormat = NumberFormat.getInstance(Locale("es", "CO")).apply {
        maximumFractionDigits = 0
    }

    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(150.dp)
            .height(260.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier.clickable {
                navController.navigate("menuDetalleProd/${producto.productoId}")
            }
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
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${numberFormat.format(producto.precio.toDouble())}",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "${producto.descripcion}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            /*Text(
                text = "por ${producto.usuarioId}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Categoría: ${producto.categoriaId}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )*/
        }
    }
}


