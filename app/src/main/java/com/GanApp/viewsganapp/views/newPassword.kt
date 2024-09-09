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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.material.icons.filled.Lock as Lock1

@Composable
fun newPassword(navController: NavController, onSubmit: (newPasswordData) -> Unit) {
    var correo by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }


    Column(
        modifier = Modifier
            .background(color = Color.White)
            //.padding(16.dp)
            //.verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center
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

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "¿Tienes problemas para entrar a tu cuenta?",
            color = Color.Black,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Introduce tu correo electrónico o tu número de celular y te enviaremos un código, para que puedas ingresar de nuevo",
            //color = Color.Blue,
            fontSize = 16.sp,
            modifier = Modifier
                .padding( 16.dp)
                .offset(y = 20.dp)
        )

        Spacer(modifier = Modifier.height(5.dp))

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

        Spacer(modifier = Modifier.height(5.dp))

        OutlinedTextField(
            value = numeroTelefono,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                numeroTelefono = filteredText
            },
            label = { Text("teléfono") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock1, contentDescription = "teléfono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

        Box(
            modifier = Modifier.offset(y = 20.dp)

        ) {
            Button(
                onClick = { onSubmit(newPasswordData(correo,numeroTelefono))
                    navController.navigate("homePage")},
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4),
                    contentColor = Color.Black)
            )
            {
                Text("Enviar código", color = Color.Black)
            }
        }


        Spacer(modifier = Modifier.height(5.dp))
    }
}



data class newPasswordData(
    val correo: String,
    val numeroTelefono:String
)