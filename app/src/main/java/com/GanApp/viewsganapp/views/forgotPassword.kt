package com.GanApp.viewsganapp.views

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R

@Composable
fun ForgotPassword(navController: NavController, onSubmit: (ForgotPasswordData) -> Unit) {
    var correo by remember { mutableStateOf("") }

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
                modifier = Modifier.offset(y = 35.dp))
        }

        Text(
            text = "¿Tienes problemas para ingresar a tu cuenta?",
            fontSize = 30.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

        )

        Text(
            text = "Introduce tu correo electrónico y te enviaremos un codigo para que puedas ingresar de nuevo",
            fontSize = 20.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                correo = filteredText
            },
            label = { Text("Ingrese su correo electronico") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

        Box(
            modifier = Modifier.offset(y = 20.dp)

        ) {
            Button(  onClick = { navController.navigate("resetPassword")
                onSubmit(ForgotPasswordData(correo)) },
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4),
                    contentColor = Color.Black)
            )
            {
                Text("Enviar", color = Color.Black)
            }
        }


        Spacer(modifier = Modifier.height(5.dp))
    }
}



data class ForgotPasswordData (
    val correo: String,
)