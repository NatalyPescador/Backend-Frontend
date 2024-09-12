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
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.ui.theme.Utendo
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Column as Column

var showErrorResetPassword by mutableStateOf(false)
var errorMessageResetPassword by mutableStateOf("")

@Composable
fun ResetPassword(navController: NavController, onSubmit: (ResetPasswordData) -> Unit) {
    var token by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmedPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
            horizontalAlignment = Alignment.CenterHorizontally,
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

            Text(
                text = "Restablecer Contraseña",
                color = Color.Black,
                fontFamily = Utendo,
                fontSize = 35.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .offset(y = 20.dp)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = token,
                onValueChange = { //Elimina las lineas no deseadas
                    val filteredText = it.replace("\n", "")
                    token = filteredText
                },
                label = { Text("Ingresa el código recibido", color = Color.Black,) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "candado")
                },
                shape = RoundedCornerShape(20.dp), // Ajusta el radio del borde según tus preferencias
                modifier = Modifier

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = newPassword,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    newPassword = filteredText
                },
                label = { Text("Nueva Contraseña", color = Color.Black,) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "gmail")
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
                modifier = Modifier

            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = confirmedPassword,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    confirmedPassword = filteredText
                },
                label = { Text("Confirmar Contraseña", color = Color.Black,) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "telefono")
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
                modifier = Modifier


            )

            Spacer(modifier = Modifier.height(16.dp))

            LaunchedEffect(showErrorResetPassword) {
                if (showErrorResetPassword) {
                    delay(5000)
                    showErrorResetPassword = false
                }
            }

            if (showErrorResetPassword) {
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        errorMessageResetPassword,
                        color = Color.Red,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box() {
                Button(
                    { onSubmit(ResetPasswordData(token, newPassword, confirmedPassword)) },
                    colors = ButtonDefaults.buttonColors(
                        Color(10, 191, 4),
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .height(55.dp)
                )
                {
                    Text(
                        "Guardar cambios",
                        style = TextStyle(
                            fontFamily = Utendo,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                        ), color = Color.White
                    )
                }
            }

        }
    }
}



data class ResetPasswordData(
    val token: String,
    val newPassword: String,
    val confirmedPassword: String,
)