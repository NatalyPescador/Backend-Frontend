package com.GanApp.viewsganapp.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun UserInputForm(onSubmit: (UserData) -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = { nombreCompleto = it },
            label = { Text("Nombre") }
        )
        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo Electrónico") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )
        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = { numeroTelefono = it },
            label = { Text("Número de Teléfono") }
        )
        Button(onClick = { onSubmit(UserData(nombreCompleto, correo, password, numeroTelefono)) }) {
            Text("Enviar")
        }
    }
}

data class UserData(val nombreCompleto: String, val correo: String, val password: String, val numeroTelefono: String)
