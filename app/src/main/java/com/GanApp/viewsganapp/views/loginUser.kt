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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.views.LogInData



@Composable
fun LogIn(navController: NavController, onSubmit: (LogInData) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordIncorrect by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .offset(y = 50.dp)
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

        Text(
            text = "Iniciar Sesión",
            fontSize = 40.sp,
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
            label = { Text("Correo Electrónico") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                password = filteredText
            },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

        Box(
            modifier = Modifier.offset(y = 20.dp)

        ) {
            Button(
                onClick = { onSubmit(LogInData(correo, password)) },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4),
                    contentColor = Color.Black
                )
            )
            {
                Text("Iniciar Sesión", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row {
            Text(text = "¿Aun no tienes cuenta?")
            Spacer(modifier = Modifier.width(15.dp))
            Text(text = "Registrate", fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    navController.navigate("viewRegister")
                })
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(text = "¿Olvidaste tú contraseña?",
            modifier = Modifier
            .clickable {
                navController.navigate("forgotPassword")
            }
            .offset(x = 50.dp))

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(y = 20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.fc_logo),
                contentDescription = "Logo de Facebook",
                modifier = Modifier
                    .clickable {
                        navController.navigate("facebook")
                    }
                    .height(70.dp)
                    .width(70.dp)
                    .offset(x = 22.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gmail_logo),
                contentDescription = "Logo Gmail",
                modifier = Modifier
                    .clickable {
                        navController.navigate("gmail")
                    }
                    .height(55.dp)
                    .offset(x = 135.dp)
                    .width(55.dp)
            )
        }
        Box(modifier = Modifier.offset(y = (-10).dp)
        ) {
            Row {
                Text(text = "Continuar con", modifier = Modifier
                    .clickable {
                        navController.navigate("facebook")
                    }
                    .offset(x = (-38).dp))//Facebook
                Text(text = "Continuar con",modifier = Modifier
                    .clickable {
                        navController.navigate("gmail")
                    }.offset(x = 30.dp))//Gmail
            }
        }

        Box(modifier = Modifier.offset(y = (-15).dp)){
            Row {
                Text(text = "Facebook", modifier = Modifier
                    .clickable {
                        navController.navigate("facebook")
                    }
                    .offset(x = (-70).dp))
                Text(text = "Gmail", modifier = Modifier
                    .clickable {
                        navController.navigate("gmail")
                    }.offset(x = (45).dp))
            }
        }

        Text(
            text = "Registrate", color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)

        )

        Text(
            text = "Registrate", color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Registrate", color = Color.White,
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
        )


    }
}

data class LogInData(
    val correo: String,
    val password: String
)
