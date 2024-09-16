package com.GanApp.viewsganapp.views

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.filled.AddComment
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
import com.GanApp.viewsganapp.components.Cards
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import com.GanApp.viewsganapp.utils.getUserData
import com.GanApp.viewsganapp.viewModels.ButtonCreateChatViewModel
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import com.GanApp.viewsganapp.viewModels.ReviewViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerDetalle(navController: NavController, productId: Long) {
    val context = LocalContext.current
    val userId = remember { getUserData(context)?.userId ?: 0L }
    val productViewModel: ProductViewModel = viewModel()
    productViewModel.getProductById(productId)
    val selectedProduct by remember { productViewModel.selectedProduct }
    //val filename = selectedProduct?.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = selectedProduct?.imagen

    // Variables de reseña
    val reviewViewModel: ReviewViewModel = viewModel()
    reviewViewModel.getReviewByProductId(productId)
    val selectedReview by remember { reviewViewModel.selectedReviews }
    var resena by remember { mutableStateOf("") }
    val loading by reviewViewModel.loading.collectAsState()

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

    LaunchedEffect(reviewViewModel.snackbarMessage) {
        reviewViewModel.snackbarMessage.collect { message ->
            message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                reviewViewModel.clearSnackbarMessage()
            }
        }
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
            IconButton(onClick = { navController.navigate("homePage") }) {
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

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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

                Text(
                    text = selectedProduct?.nombre ?: "",
                    fontSize = 20.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
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
                    Text(
                        text = if (showDescription) "Ocultar Descripción" else "Descripción",
                        fontSize = 18.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (showDescription) {
                    Dialog(onDismissRequest = { showDescription = false }) {
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
                                contentAlignment = Alignment.Center
                            ) {
                                Card(
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(16.dp)
                                        .clickable(onClick = {}), // Para evitar el cierre de la tarjeta al hacer clic en ella
                                    //shape = RoundedCornerShape(32.dp),
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .background(Color.White)
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

                                        Spacer(modifier = Modifier.height(16.dp))
                                    }
                                }
                            }
                        }
                    }
                }

                Text(
                    text = "Reseñas",
                    fontSize = 20.sp,
                    color = Color(0xFF02730A), // Cambia el color a #02730A
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .offset(y = 20.dp),
                )

                OutlinedTextField(
                    value = resena,
                    onValueChange = {
                        resena = it
                    },
                    label = { Text("Escribe tu reseña") },
                    textStyle = TextStyle(color = Color.Black),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AddComment,
                            contentDescription = "telefono"
                        )
                    },
                    shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
                    modifier = Modifier
                        .offset(y = 20.dp)
                        .align(Alignment.CenterHorizontally),
                )

                Spacer(modifier = Modifier.height(16.dp)) // Añade espacio entre el formulario y el botón

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Button(
                        onClick = {
                            coroutineScope.launch {

                                val reviewData = ReviewData(
                                    usuarioId = userId,
                                    productoId = productId,
                                    resena = resena
                                )
                                reviewViewModel.publishReview(reviewData)

                                resena = ""

                                delay(500L)

                                reviewViewModel.getReviewByProductId(productId)
                            }
                        },
                        colors = ButtonDefaults.buttonColors(Color(10, 191, 4))
                    ) {
                        Text("Publicar reseña", fontSize = 18.sp, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                if (loading) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                } else {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        items(selectedReview) { review ->
                            Cards(review)
                        }
                    }

                    Spacer(modifier = Modifier.height(30.dp))
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
                                val receiverId = selectedProduct?.usuarioId ?: 0L
                                buttonCreateChatViewModel.createOrFetchChat(
                                    productId = productId,
                                    userId = userId,
                                    receiverId = receiverId
                                )
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
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Contactar al Vendedor"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Contactar al Vendedor", fontSize = 18.sp)
                }
            }
        }
    }
}

data class ReviewData(
    val usuarioId: Long,
    val productoId: Long,
    val resena: String,
)