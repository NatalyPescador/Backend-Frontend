package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import com.GanApp.viewsganapp.viewModels.ReviewViewModel
import kotlinx.coroutines.delay



var showErrorReview by mutableStateOf(false)
var errorMessageReview by mutableStateOf("")

@Composable
fun VerDetalle(navController: NavController, productId: Long) {
    val productViewModel: ProductViewModel = viewModel()
    productViewModel.getProductById(productId)
    val selectedProduct by remember { productViewModel.selectedProduct }
    val filename = selectedProduct?.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = "http://10.175.144.22:8080/GanApp/uploads/$filename"

    //Variables de reseña
    val reviewViewModel : ReviewViewModel = viewModel()
    reviewViewModel.getReviewByProductId(productId)
    val selectedReview by remember { reviewViewModel.selectedReviews }
    var resena by remember { mutableStateOf("") }


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
                .padding(bottom = 70.dp) // Espacio para el botón fijo
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Handle filter button click */ },
                    colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(text = "Filtrar", fontSize = 18.sp)
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
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
        }

        if (showDescription) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .clickable(onClick = { showDescription = false })
            ) {
                Card(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .offset(y = (-50).dp) // Ajustar esta línea para mover la tarjeta hacia arriba
                        .padding(16.dp)
                        .clickable(onClick = {}) // Para evitar el cierre de la tarjeta al hacer clic en ella
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column {
                            Box(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                IconButton(
                                    onClick = { showDescription = false },
                                    modifier = Modifier.align(Alignment.TopEnd)
                                ) {
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

        // Botón fijo en la parte inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Button(
                onClick = { /* Handle contact button click */ },
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
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre del ejemplar: ${selectedProduct?.nombre ?: ""}",
            fontSize = 18.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Reseñas",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

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

        LaunchedEffect(showErrorRegister) {
            if (showErrorRegister) {
                delay(5000)
                showErrorRegister = false
            }
        }

        if (showErrorRegister) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    errorMessageRegister,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Column {
            Reviews(reviews = selectedReview)
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
