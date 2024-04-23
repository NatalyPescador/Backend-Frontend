package com.GanApp.viewsganapp.views

import android.util.Patterns
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import kotlinx.coroutines.delay

var showErrorForgotPassword by mutableStateOf(false)
var errorMessageForgotPassword by mutableStateOf("")

@Composable
fun ForgotPassword(navController: NavController, onSubmit: (ForgotPasswordData) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var isCorreoValido by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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


        Row(
            modifier = Modifier
                .padding(120.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.candado), contentDescription = "Logo",
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
                    .offset(y = (-90).dp)
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "¿Tienes problemas para ingresar a tu cuenta?",
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = (-220).dp)

        )

        Text(
            text = "Introduce tu correo electrónico y te enviaremos un codigo para que puedas ingresar de nuevo.",
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = (-220).dp)

        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                correo = filteredText
                isCorreoValido = isValidEmail(filteredText) // Verificar si el correo tiene un formato válido
            },
            label = { Text("Ingrese su correo electronico") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = (-220).dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        LaunchedEffect(showErrorForgotPassword) {
            if (showErrorForgotPassword) {
                delay(5000)
                showErrorForgotPassword = false
            }
        }

        if (showErrorForgotPassword) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    errorMessageForgotPassword,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.offset(y = (-220).dp)

        ) {
            Button(
                onClick = {
                    onSubmit(ForgotPasswordData(correo))
                },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 65.dp, vertical = 10.dp),
                enabled = isCorreoValido // Habilitar el botón solo si el correo es válido
            )
            {
                Text("Enviar", color = Color.Black,
                    fontSize = 17.sp)
            }

        }

        Box(
            modifier = Modifier.offset(y = (-230).dp)
        ) {
            Button( onClick = { navController.navigate("loginUser_screens") },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp, vertical = 10.dp)
            )
            {
                Text("Cancelar", color = Color.Black,
                    fontSize = 17.sp)
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


data class ForgotPasswordData (
    val correo: String,
)