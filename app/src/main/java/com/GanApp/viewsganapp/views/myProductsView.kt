package com.GanApp.viewsganapp.views

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import java.text.NumberFormat
import java.util.Locale

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisProductos(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    val products by remember { mutableStateOf(productViewModel.products) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = Color.White) // Cambiar el fondo a blanco
        ) {
            Catalogo1(productos = products, navController = navController)
        }
    }
}

@Composable
fun Catalogo1(productos: List<ProductoEntity>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Iterar sobre los productos en pasos de 2
        items((productos.size + 1) / 2) { rowIndex ->
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until 2) {
                    val index = rowIndex * 2 + i
                    if (index < productos.size) {
                        Tarjeta1(producto = productos[index], navController = navController)
                    } else {
                        // Si no hay un segundo producto, añade un espacio para mantener la consistencia
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun Tarjeta1(producto: ProductoEntity, navController: NavController) {
    val filename = producto.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = BaseUrlConstant.BASE_URL + "uploads/$filename"
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
                navController.navigate("my_product_detail")
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
        }
    }
}
