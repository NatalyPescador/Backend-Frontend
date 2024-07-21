package com.GanApp.viewsganapp.views

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.viewModels.ButtonCreateChatViewModel
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import com.GanApp.viewsganapp.viewModels.ReviewViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var showErrorReview by mutableStateOf(false)
var errorMessageReview by mutableStateOf("")

@Composable
fun VerDetalle(navController: NavController, productId: Long) {
    val productViewModel: ProductViewModel = viewModel()
    productViewModel.getProductById(productId)
    val selectedProduct by remember { productViewModel.selectedProduct }
    val filename = selectedProduct?.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = "https://w9rrr6mq-8080.use2.devtunnels.ms/GanApp/uploads/$filename"

    // Variables de reseña
    val reviewViewModel: ReviewViewModel = viewModel()
    reviewViewModel.getReviewByProductId(productId)
    val selectedReview by remember { reviewViewModel.selectedReviews }
    var resena by remember { mutableStateOf("") }

    // Variables de ventana emergente
    var showDescription by remember { mutableStateOf(false) }

    // Variables de contactar al vendedor
    val buttonCreateChatViewModel: ButtonCreateChatViewModel = viewModel()
    val coroutineScope = rememberCoroutineScope()
    val chatId by buttonCreateChatViewModel.chatId.collectAsState()

    LaunchedEffect(chatId) {
        chatId?.let {
            navController.navigate("chat_message/$it")
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            buttonCreateChatViewModel.reserDate()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 70.dp) // Espacio para el botón fijo
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
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        //.background(Color.Black.copy(alpha = 0.5f))
                        .clickable(onClick = { showDescription = false })
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(onClick = { /* Evitar el cierre de la tarjeta al hacer clic en el fondo */ }),
                        contentAlignment = Alignment.Center // Centrar contenido en el Box
                    ) {
                        Card(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .offset(y = (-50).dp) // Ajustar esta línea para mover la tarjeta hacia arriba
                                .padding(16.dp)
                                .clickable(onClick = {}) // Para evitar el cierre de la tarjeta al hacer clic en ella
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()

                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
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

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Reseñas",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .offset(y = 20.dp),
                color = Color.Black
            )

            OutlinedTextField(
                value = resena,
                onValueChange = {
                    resena = it
                },
                label = { Text("Reseña") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "telefono")
                },
                shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
                modifier = Modifier.offset(y = 20.dp),
            )

            Spacer(modifier = Modifier.height(16.dp)) // Añade espacio entre el formulario y el botón

            Box(modifier = Modifier.padding(16.dp)) {
                Button(
                    onClick = {
                        val reviewData = ReviewData(productoId = productId, resena = resena)
                        reviewViewModel.publishReview(reviewData)
                    },
                    colors = ButtonDefaults.buttonColors(Color(10, 191, 4))
                ) {
                    Text("Publicar reseña", color = Color.Black)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LaunchedEffect(showErrorReview) {
                if (showErrorReview) {
                    delay(5000)
                    showErrorReview = false
                }
            }

            if (showErrorReview) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        errorMessageReview,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(selectedReview) { review ->
                    Cards(review)
                }
            }
        }


        // Botón fijo en la parte inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        try {
                            buttonCreateChatViewModel.createOrFetchChat(productId = productId, userId = 15L, receiverId = 8L)
                            Log.d("chatView", "Botón presionado para crear el chat")
                        } catch (e: Exception) {
                            Log.e("chatView", "Error al crear el chat: ${e.message}", e)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = (-40).dp)
            ) {
                Icon(imageVector = Icons.Default.Person, contentDescription = "Contactar al Vendedor")
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Contactar al Vendedor", fontSize = 18.sp)
            }
        }
    }
}

data class ReviewData(
    val productoId: Long,
    val resena: String,
)

@Composable
fun Reviews(reviews: List<ReviewEntity>) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(reviews) { review ->
            Cards(reviews = review)
        }
    }
}


@Composable
fun Cards(reviews: ReviewEntity) {
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Usuario: ${reviews.usuarioId}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Text(
                text = "Reseña: ${reviews.resena}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}
