package com.GanApp.viewsganapp.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Column as Column


@Composable
fun UserInputForm(onSubmit: (UserData) -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
        horizontalAlignment = Alignment.CenterHorizontally, // Centrar los elementos horizontalmente
        verticalArrangement = Arrangement.Center // Centrar los elementos verticalmente
    ) {
        
        Text(text = "Registrate",
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = { nombreCompleto = it },
            label = { Text("Nombre") },
            shape = RoundedCornerShape(30.dp) // Ajusta el radio del borde según tus preferencias
        )


        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") },
            shape = RoundedCornerShape(30.dp)
        )

        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = { numeroTelefono = it },
            label = { Text("Número de Teléfono") },
            shape = RoundedCornerShape(30.dp)

        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            shape = RoundedCornerShape(30.dp)
        )

        Spacer(modifier = Modifier.height(16.dp)) // Añade espacio entre el formulario y el botón

        Button(onClick = { onSubmit(UserData(nombreCompleto, correo, password, numeroTelefono)) }) {
            Text("Registrate")
        }

        Box {
            Row {
                Text(text = "¿Ya tienes una cuenta?")
                Text(text = "Inicia Sesión")
            }
        }

    }
}

data class UserData(
    val nombreCompleto: String,
    val correo: String,
    val password: String,
    val numeroTelefono:String
)