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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.components.loadReviews
import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import kotlinx.coroutines.delay


var showErrorReview by mutableStateOf(false)
var errorMessageReview by mutableStateOf("")

@Composable
fun VerDetalle(navController: NavController, productId: Long, productViewModel: ProductViewModel = viewModel()) {
    productViewModel.getProductById(productId)
    val selectedProduct by remember { productViewModel.selectedProduct }
    val filename = selectedProduct?.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = "http://192.168.1.13:8080/GanApp/uploads/$filename"

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /* Handle filter button click */ },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
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

//        LazyRow(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(100.dp)
//        ) {
//            items(additionalImages) { imageUrl ->
//                Image(
//                    painter = painterResource(id = imageUrl),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .padding(end = 8.dp)
//                        .size(100.dp)
//
//                )
//            }
//        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(
                onClick = { /* Handle filter button click */ },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(imageVector = Icons.Default.Person, contentDescription = "Contactar al Vendedor")

                Text(text = "Contactar al Vendedor", fontSize = 18.sp)

            }

        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Nombre del ejemplar: ${selectedProduct?.nombre ?: ""}",
            fontSize = 18.sp
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
        
        //PublishReview(navController = navController, onSubmit = )

    }
}

// Datos de ejemplo para las imágenes adicionales
//val additionalImages = listOf(
//    R.drawable.logo,
//    R.drawable.gannap_cabeza,
//    R.drawable.gmail_logo,
//    R.drawable.imagenes_icn,
//    R.drawable.gannap_cabeza
//)

@Composable
fun PublishReview(navController: NavController, onSubmit: (ReviewData) -> Unit) {
    var resena by remember { mutableStateOf("") }
    var reviewsState = remember { mutableStateOf(listOf<ReviewEntity>()) }
    val reviews by reviewsState
    val coroutineScope = rememberCoroutineScope()
    var reloadPage = remember { mutableStateOf(false) }

    LaunchedEffect(reloadPage) {
        loadReviews(coroutineScope, reviewsState)
    }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            //.verticalScroll(rememberScrollState())
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier.offset(y = 35.dp)
            )
        }

        Text(
            text = "Reseñas",
            fontSize = 40.sp,
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

        Box(
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Button( onClick = {
                onSubmit(ReviewData(resena))
                reloadPage.value = true
            },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                )
            )
            {
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
            Reviews(reviews = reviews)
        }
    }
}

data class ReviewData(
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
