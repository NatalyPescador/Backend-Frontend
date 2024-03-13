package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.GanApp.viewsganapp.R
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription ="Logo" )
        }

        Text(text = "Registrate",
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Box {
            OutlinedTextField(
                nombreCompleto,
                { //Elimina las lineas no deseadas
                    val filteredText = it.replace("\n", "")
                    nombreCompleto = filteredText },
                label = { Text("Nombre") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = "telefono") },
                shape = RoundedCornerShape(20.dp),// Ajusta el radio del borde según tus preferencias
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color.Black )
            )
        }


        OutlinedTextField(
            value = correo,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                correo = filteredText },
            label = { Text("Correo Electrónico") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail") },
            shape = RoundedCornerShape(20.dp)
        )

        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                numeroTelefono = filteredText
            },
            label = { Text("Número de Teléfono") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp)

        )

        OutlinedTextField(
            password, { val filteredText = it.replace("\n", "")
                password = filteredText },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono") },
            shape = RoundedCornerShape(20.dp),
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