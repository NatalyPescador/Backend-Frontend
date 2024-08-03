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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Column as Column


var showErrorRegister by mutableStateOf(false)
var errorMessageRegister by mutableStateOf("")

@Composable
fun Register(navController: NavController, onSubmit: (UserData) -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var numeroTelefono by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(y = 20.dp),
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier.offset(y = 35.dp)
            )
        }

        Text(
            text = "Registrarse",
            color = Color.Black,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

        )

        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                nombreCompleto = filteredText
            },
            label = { Text("Nombre") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
            modifier = Modifier.offset(y = 20.dp),

        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                correo = filteredText
            },
            label = { Text("Correo Electrónico") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = {
                val filteredText = it.filter { char -> char.isDigit() }
                numeroTelefono = filteredText
            },
            label = { Text("Número de Teléfono") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "nombre")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)


        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "Contraseña")
            },
            trailingIcon = {
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                        contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

        Box(
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Button( onClick = { navController.navigate("homePage")
                onSubmit(UserData(nombreCompleto, correo, password, numeroTelefono)) },
                colors = buttonColors(
                    Color(10, 191, 4)
                )
            )
            {
                Text("Registrarse", color = Color.Black)
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

        Row {
            Text(text = "¿Ya tienes una cuenta?", color = Color.Black)
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Inicia Sesión",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                        navController.navigate("loginUser_screens")
                })
        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(y = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center // Centra las imágenes en el Row
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.fc_logo),
                    contentDescription = "Logo de Facebook",
                    modifier = Modifier
                        .clickable {
                            navController.navigate("facebook")
                        }
                        .height(80.dp)
                        .width(80.dp)
                )
                Spacer(modifier = Modifier.height(2.dp)) // Espacio entre imagen y texto
                Text(text = "Continuar con",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("facebook")
                        })
                Text(text = "Facebook",
                    color = Color.Black,
                    textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("facebook")
                        })
            }
            Spacer(modifier = Modifier.width(40.dp)) // Espacio entre las columnas
            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.offset(y = 10.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.gmail_logo),
                    contentDescription = "Logo Gmail",
                    modifier = Modifier
                        .clickable {
                            navController.navigate("gmail")
                        }
                        .height(60.dp)
                        .width(60.dp)
                        .offset(y = (-8).dp)
                )
                Spacer(modifier = Modifier.height(5.dp)) // Espacio entre imagen y texto
                Text(text = "Continuar con",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("gmail")
                        })
                Text(text = "Gmail",
                    color = Color.Black,
                    textAlign = TextAlign.Center, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("gmail")
                        })
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        repeat(3) {
            Text(
                text = "Regístrate",
                color = Color.White,
                fontSize = 40.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}



data class UserData(
    val nombreCompleto: String,
    val correo: String,
    val password: String,
    val numeroTelefono:String
)