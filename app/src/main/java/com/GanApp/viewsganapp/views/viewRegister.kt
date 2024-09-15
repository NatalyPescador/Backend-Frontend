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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.ui.theme.Utendo
import kotlinx.coroutines.delay


var showErrorRegister by mutableStateOf(false)
var errorMessageRegister by mutableStateOf("")

@Composable
fun Register(navController: NavController, onSubmit: (UserData) -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var numeroTelefono by remember { mutableStateOf("") }


    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    )
    {
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
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Regístrate",
                color = Color.Black,
                fontFamily = Utendo,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .offset(y = 10.dp)

            )

            Spacer(modifier = Modifier.height(10.dp))

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
                modifier = Modifier.offset(y = 5.dp),

                )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    correo = filteredText
                },
                label = { Text("Correo electrónico") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = 5.dp)

            )
            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = numeroTelefono,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() }
                    numeroTelefono = filteredText
                },
                label = { Text("Número de teléfono") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "nombre")
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = 5.dp)


            )
            Spacer(modifier = Modifier.height(10.dp))

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
                modifier = Modifier.offset(y = 5.dp)

            )

            Spacer(modifier = Modifier.height(10.dp)) // Añade espacio entre el formulario y el botón

            Box(
                modifier = Modifier.offset(y = 20.dp)
            ) {
                Button(
                    onClick = {
                        navController.navigate("loginUser_screens")
                        onSubmit(UserData(nombreCompleto, correo, password, numeroTelefono))
                    },
                    colors = buttonColors(
                        Color(10, 191, 4)
                    ),
                    modifier = Modifier
                        .height(55.dp)
                )
                {
                    Text(
                        "Regístrate", color = Color.White,
                        style = TextStyle(
                            fontFamily = Utendo,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                        ),
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

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

            Spacer(modifier = Modifier.height(18.dp))

            Row {
                Text(text = "¿Ya tienes una cuenta?", color = Color.Black)
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Inicia Sesión",
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("loginUser_screens")
                    })
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}



data class UserData(
    val nombreCompleto: String,
    val correo: String,
    val password: String,
    val numeroTelefono:String
)