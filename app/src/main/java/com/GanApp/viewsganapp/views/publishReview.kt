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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Column as Column


var showErrorReview by mutableStateOf(false)
var errorMessageReview by mutableStateOf("")

@Composable
fun PublishReview(navController: NavController, onSubmit: (ReviewData) -> Unit) {
    var resena by remember { mutableStateOf("") }


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
                onSubmit(ReviewData(resena)) },
                colors = buttonColors(
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
    }
}




data class ReviewData(
    val resena: String,
)