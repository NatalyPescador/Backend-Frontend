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
import com.GanApp.viewsganapp.R
import androidx.compose.foundation.layout.Column as Column


@Composable
fun Register(onSubmit: (UserData) -> Unit) {
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }


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
            text = "Registrarse",
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

        )

        OutlinedTextField(
            value = nombreCompleto,
            onValueChange = { //Elimina las lineas no deseadas
                val filteredText = it.replace("\n", "")
                nombreCompleto = filteredText
            },
            label = { Text("Nombre") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
            modifier = Modifier.offset(y = 20.dp)

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
                val filteredText = it.replace("\n", "")
                numeroTelefono = filteredText
            },
            label = { Text("Número de Teléfono") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Phone, contentDescription = "telefono")
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
                { onSubmit(UserData(nombreCompleto, correo, password, numeroTelefono)) },
                colors = buttonColors(Color(10, 191, 4),
                    contentColor = Color.Black)
            )
            {
                Text("Registrarse", color = Color.Black)
            }
        }


        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Row {
                Text(text = "¿Ya tienes una cuenta?")
                Spacer(modifier = Modifier.width(15.dp))
                Text(text = "Inicia Sesión", fontWeight = FontWeight.Bold)
            }
        }

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
                    .height(70.dp)
                    .width(70.dp)
                    .offset(x = 22.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.gmail_logo),
                contentDescription = "Logo Gmail",
                modifier = Modifier
                    .height(55.dp)
                    .offset(x = 135.dp)
                    .width(55.dp)
            )
        }
        Box(modifier = Modifier.offset(y = (-10).dp)
        ) {
            Row {
                Text(text = "Continuar con", modifier = Modifier.offset(x = (-38).dp))//Facebook
                Text(text = "Continuar con", modifier = Modifier.offset(x = 30.dp))//Gmail
            }
        }

            Box(modifier = Modifier.offset(y = (-15).dp)){
                Row {
                    Text(text = "Facebook", modifier = Modifier.offset(x = (-70).dp))
                    Text(text = "Gmail", modifier = Modifier.offset(x = (45).dp))
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

    }
}



data class UserData(
    val nombreCompleto: String,
    val correo: String,
    val password: String,
    val numeroTelefono:String
)