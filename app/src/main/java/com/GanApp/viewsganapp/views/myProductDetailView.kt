package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.models.ProductDataDto
import com.GanApp.viewsganapp.models.UpdateProductDto
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import com.GanApp.viewsganapp.viewModels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
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

    // Variables para editar los campos
    var precio by remember { mutableStateOf(selectedProduct?.precio?.toString() ?: "") }
    var descripcion by remember { mutableStateOf(selectedProduct?.descripcion ?: "") }
    var raza by remember { mutableStateOf(selectedProduct?.raza ?: "") }
    var uom by remember { mutableStateOf(selectedProduct?.uom ?: "") }
    var edad by remember { mutableStateOf(selectedProduct?.edad?.toString() ?: "") }
    var cantidad by remember { mutableStateOf(selectedProduct?.cantidad?.toString() ?: "") }

    // Actualiza los valores cuando selectedProduct cambia
    LaunchedEffect(selectedProduct) {
        precio = selectedProduct?.precio?.toString() ?: ""
        descripcion = selectedProduct?.descripcion ?: ""
        raza = selectedProduct?.raza ?: ""
        uom = selectedProduct?.uom ?: ""
        edad = selectedProduct?.edad?.toString() ?: ""
        cantidad = selectedProduct?.cantidad?.toString() ?: ""
    }

    Scaffold( topBar = {
        TopAppBar(title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(205.dp)
                    .padding(5.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)

                )
                Image(
                    painter = painterResource(id = R.drawable.vaca_titulo),
                    contentDescription = "Vaca",
                    modifier = Modifier
                        .height(60.dp)
                        .width(85.dp)
                        .offset(y = 65.dp)
                        .offset(x = 45.dp)

                )

            }

        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    modifier = Modifier.size(35.dp),
                    tint = Color(2, 115, 10),
                    contentDescription = "Volver"
                )
            }
        },  colors = TopAppBarDefaults.topAppBarColors(Color(255, 255, 255))

        )
    }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Text(
                text = "Editar Producto",
                fontFamily = Utendo,
                fontSize = 35.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .offset(y = (-20).dp) // Ajusta la posición del título
            )
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
                        .offset(y = (-20).dp) // Ajuste de la posición del botón
                ) {
                    Text(
                        text = if (showDescription) "Ocultar edición" else "Editar información del producto",
                        fontSize = 18.sp
                    )
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
                                        .background(
                                            Color(
                                                226, 227, 226
                                            )
                                        ), // Color de fondo de la tarjeta
                                    colors = CardDefaults.cardColors(
                                        containerColor = Color(
                                            226, 227, 226
                                        ) // Color de fondo de la tarjeta
                                    )
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
                                                    contentDescription = "Cerrar",
                                                    tint = Color.Black // Asegúrate de que el ícono sea visible con el fondo verde
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(16.dp))

                                        OutlinedTextField(
                                            value = precio,
                                            onValueChange = { precio = it
                                                val filteredText = it.filter { char -> char.isDigit() }
                                                precio = filteredText },
                                            label = { Text("Precio", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        OutlinedTextField(
                                            value = descripcion,
                                            onValueChange = { descripcion = it },
                                            label = { Text("Descripción", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        OutlinedTextField(
                                            value = raza,
                                            onValueChange = { raza = it },
                                            label = { Text("Raza", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        OutlinedTextField(
                                            value = uom,
                                            onValueChange = { uom = it },
                                            label = { Text("Peso", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        OutlinedTextField(
                                            value = edad,
                                            onValueChange = { edad = it },
                                            label = { Text("Edad", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(5.dp))

                                        OutlinedTextField(
                                            value = cantidad,
                                            onValueChange = { cantidad = it },
                                            label = { Text("Cantidad", color=Color.Gray) },
                                            textStyle = TextStyle(color = Color.Black),
                                            modifier = Modifier.fillMaxWidth(),
                                            shape = RoundedCornerShape(20.dp)
                                        )

                                        Spacer(modifier = Modifier.height(16.dp))

                                        Button(
                                            onClick = {
                                                productViewModel.updateProduct(
                                                    productId,
                                                    UpdateProductDto(
                                                        precio = precio,
                                                        descripcion = descripcion,
                                                        raza = raza,
                                                        uom = uom,
                                                        edad = edad,
                                                        cantidad = cantidad
                                                    )
                                                )
                                            },
                                            colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
                                            modifier = Modifier
                                                .align(Alignment.CenterHorizontally)
                                        ) {
                                            Text(
                                                text = "Guardar Cambios",
                                                fontSize = 18.sp,
                                                color = Color.Black
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



