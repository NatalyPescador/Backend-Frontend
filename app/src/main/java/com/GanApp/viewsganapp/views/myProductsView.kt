package com.GanApp.viewsganapp.views

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import com.GanApp.viewsganapp.utils.getUserData
import java.text.NumberFormat
import java.util.Locale
import androidx.compose.ui.zIndex

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MisProductos(navController: NavController) {
    val context = LocalContext.current
    val userId = remember { getUserData(context)?.userId ?: 0L }
    val productViewModel: ProductViewModel = viewModel()
    val products by remember { mutableStateOf(productViewModel.userProducts) }

    LaunchedEffect(userId) {
        productViewModel.getProductsByUserId(userId)
    }

    BackHandler {
        navController.navigate("homePage") {
            popUpTo(0) { inclusive = true } // Elimina toda la pila de navegación
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Tus Productos",
                        fontFamily = Utendo,
                        fontSize = 35.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .offset(y = 20.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center // Asegura que el texto esté centrado
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 255, 255) // Fondo blanco para la barra superior
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp)) // Añade espacio debajo de la barra superior
            MyProducts(productos = products, navController = navController)
        }
    }
}

@Composable
fun MyProducts(productos: List<ProductoEntity>, navController: NavController) {
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
    val imageUrl = producto.imagen
    val productViewModel: ProductViewModel = viewModel()
    val numberFormat = NumberFormat.getInstance(Locale("es", "CO")).apply {
        maximumFractionDigits = 0
    }

    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(150.dp)
            .height(260.dp)
    ) {
        Surface(
            modifier = Modifier
                .padding(end = 8.dp)
                .width(150.dp)
                .height(260.dp),
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 3.dp
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .clickable {
                        navController.navigate("my_product_detail/${producto.productoId}")
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

        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Eliminar producto",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
                .size(24.dp)
                .clickable {
                    showDialog = true // Mostrar el diálogo de confirmación
                },
            tint = Color.Red
        )
    }

    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog = false }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                shadowElevation = 8.dp
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Eliminar producto",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Text(
                        text = "¿Estás seguro que deseas eliminar este producto?",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .padding(8.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                showDialog = false
                            }
                        ) {
                            Text("No", color = Color.Gray)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        TextButton(
                            onClick = {
                                productViewModel.deleteProductById(producto.productoId)
                                showDialog = false // Cierra el diálogo
                            }
                        ) {
                            Text("Sí", color = Color.Red)
                        }
                    }
                }
            }
        }
    }
}

