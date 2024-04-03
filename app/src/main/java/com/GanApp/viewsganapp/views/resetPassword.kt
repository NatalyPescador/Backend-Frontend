package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import androidx.compose.foundation.layout.Column as Column


@Composable
fun ResetPassword(navController: NavController, onSubmit: (ResetPasswordData) -> Unit) {
    var token by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            //.verticalScroll(rememberScrollState())
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
                modifier = Modifier.offset(y = (-65).dp))
        }

        Text(
            text = "Restablecer Contraseña",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = (-70).dp)

        )

        OutlinedTextField(
            value = token,
            onValueChange = { //Elimina las lineas no deseadas
                val filteredText = it.replace("\n", "")
                token = filteredText
            },
            label = { Text("Ingrese el código recibido") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "candado")
            },
            shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
            modifier = Modifier.offset(y = (-65).dp)

        )


        OutlinedTextField(
            value = newPassword,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                newPassword = filteredText
            },
            label = { Text("Nueva Contraseña") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = (-65).dp)

        )

        OutlinedTextField(
            value = confirmedPassword,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                confirmedPassword = filteredText
            },
            label = { Text("Confirmar Contraseña") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = (-65).dp)


        )

        Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

        Box(
            modifier = Modifier.offset(y = (-65).dp)

        ) {
            Button(
                { onSubmit(ResetPasswordData(token, newPassword, confirmedPassword)) },
                colors = buttonColors(Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 65.dp, vertical = 5.dp)
            )
            {
                Text("Guardar cambios",
                    color = Color.Black,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center)
            }
        }

    }
}



data class ResetPasswordData(
    val token: String,
    val newPassword: String,
    val confirmedPassword: String,
)