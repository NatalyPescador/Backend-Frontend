package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.style.TextAlign
import com.GanApp.viewsganapp.models.ReviewEntity
import kotlinx.coroutines.delay
import androidx.compose.foundation.lazy.items
import com.GanApp.viewsganapp.components.loadReviews
import androidx.compose.foundation.layout.Column as Column


//var showErrorReview by mutableStateOf(false)
//var errorMessageReview by mutableStateOf("")

//@Composable
//fun PublishReview(navController: NavController, onSubmit: (ReviewData) -> Unit) {
//    var resena by remember { mutableStateOf("") }
//    var reviewsState = remember { mutableStateOf(listOf<ReviewEntity>()) }
//    val reviews by reviewsState
//    val coroutineScope = rememberCoroutineScope()
//    var reloadPage = remember { mutableStateOf(false) }
//
//    LaunchedEffect(reloadPage) {
//            loadReviews(coroutineScope, reviewsState)
//    }
//
//
//    Column(
//        modifier = Modifier
//            .background(color = Color.White)
//            .padding(16.dp)
//            //.verticalScroll(rememberScrollState())
//            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//
//        Row(
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth()
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
//                modifier = Modifier.offset(y = 35.dp)
//            )
//        }
//
//        Text(
//            text = "Reseñas",
//            fontSize = 40.sp,
//            modifier = Modifier
//                .padding(bottom = 16.dp)
//                .offset(y = 20.dp)
//
//        )
//
//        OutlinedTextField(
//            value = resena,
//            onValueChange = {
//                resena = it
//            },
//            label = { Text("Reseña") },
//            textStyle = TextStyle(color = Color.Black),
//            leadingIcon = {
//                Icon(imageVector = Icons.Default.Person, contentDescription = "telefono")
//            },
//            shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
//            modifier = Modifier.offset(y = 20.dp),
//
//            )
//
//        Spacer(modifier = Modifier.height(16.dp)) // Añade espacio entre el formulario y el botón
//
//        Box(
//            modifier = Modifier.offset(y = 20.dp)
//        ) {
//            Button( onClick = {
//                onSubmit(ReviewData(resena))
//                reloadPage.value = true
//                              },
//                colors = buttonColors(
//                    Color(10, 191, 4)
//                )
//            )
//            {
//                Text("Publicar reseña", color = Color.Black)
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LaunchedEffect(showErrorRegister) {
//            if (showErrorRegister) {
//                delay(5000)
//                showErrorRegister = false
//            }
//        }
//
//        if (showErrorRegister) {
//            Box(
//                modifier = Modifier
//                    .padding(16.dp)
//                    .fillMaxWidth()
//            ) {
//                Text(
//                    errorMessageRegister,
//                    color = Color.Red,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(horizontal = 8.dp)
//                )
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//
//        Column {
//            Reviews(reviews = reviews)
//        }
//    }
//}
//
//data class ReviewData(
//    val resena: String,
//)
//
//@Composable
//fun Reviews(reviews: List<ReviewEntity>) {
//    LazyRow(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        items(reviews) { review ->
//            Cards(reviews = review)
//        }
//    }
//}
//
//
//@Composable
//fun Cards(reviews: ReviewEntity) {
//    Surface(
//        modifier = Modifier
//            .padding(end = 8.dp)
//            .width(120.dp)
//            .height(200.dp),
//        shape = RoundedCornerShape(8.dp),
//    ) {
//        Column(
//            modifier = Modifier.clickable { /* Handle click event */ }
//        ) {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Usuario: ${reviews.usuarioId}",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//            Text(
//                text = "Reseña: ${reviews.resena}",
//                modifier = Modifier.padding(horizontal = 8.dp)
//            )
//        }
//    }
//}