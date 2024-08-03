package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import com.GanApp.viewsganapp.viewModels.ProductViewModel

@Composable
fun MisProdDetalles(navController: NavController, productId: Long) {
    val context = LocalContext.current
    val productViewModel: ProductViewModel = viewModel()
    productViewModel.getProductById(productId)
    val selectedProduct by remember { productViewModel.selectedProduct }
    val filename = selectedProduct?.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = BaseUrlConstant.BASE_URL + "uploads/$filename"

    // Variables de ventana emergente
    var showDescription by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 90.dp) // Espacio para el botón fijo
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = imageUrl),
                    contentDescription = selectedProduct?.nombre ?: "Sin nombre",
                    modifier = Modifier
                        .width(300.dp)
                        .height(350.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Nombre del ejemplar: ${selectedProduct?.nombre ?: ""}",
                fontSize = 20.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { showDescription = !showDescription },
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
                modifier = Modifier
                    .padding(25.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(text = if (showDescription) "Ocultar Descripción" else "Descripción", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (showDescription) {
                Dialog(onDismissRequest = { showDescription = false }) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { showDescription = false })
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable(onClick = { /* Evitar el cierre de la tarjeta al hacer clic en el fondo */ }),
                            contentAlignment = Alignment.Center
                        ) {
                            Card(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp)
                                    .background(Color(0xFFF2F2F2))
                                    .clickable(onClick = {}), // Para evitar el cierre de la tarjeta al hacer clic en ella
                            ) {
                                Column(
                                    modifier = Modifier
                                        .padding(16.dp)
                                        .fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        IconButton(onClick = { showDescription = false }) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = "Cerrar"
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(16.dp))

                                    Text(
                                        text = "Precio: ${selectedProduct?.precio ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Descripción: ${selectedProduct?.descripcion ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Raza: ${selectedProduct?.raza ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Sexo: ${selectedProduct?.sexo ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Peso: ${selectedProduct?.uom ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Edad: ${selectedProduct?.edad ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Cantidad: ${selectedProduct?.cantidad ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Departamento: ${selectedProduct?.departamento ?: ""}",
                                        fontSize = 18.sp
                                    )

                                    Spacer(modifier = Modifier.height(8.dp))

                                    Text(
                                        text = "Municipio: ${selectedProduct?.municipio ?: ""}",
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Botón fijo en la parte inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp) // Ajusta la distancia desde el borde inferior
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(top = 20.dp) // Espacio superior para ajustar la posición vertical del botón
            ) {
                Button(
                    onClick = {
                        navController.navigate("edit_product") // Navegar a la vista de edición del producto
                    },
                    colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
                    modifier = Modifier
                        .align(Alignment.Center)
                ) {
                    Text(text = "Editar Producto", fontSize = 18.sp, color = Color.Black)
                }
            }
        }
    }
}
