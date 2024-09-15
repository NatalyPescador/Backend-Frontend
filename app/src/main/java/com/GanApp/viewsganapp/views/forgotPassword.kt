package com.GanApp.viewsganapp.views

import android.util.Patterns
import android.widget.Toast
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.viewModels.SessionViewModel
import kotlinx.coroutines.delay

@Composable
fun ForgotPassword(navController: NavController) {
    val sessionViewModel: SessionViewModel = viewModel()
    val context = LocalContext.current
    var correo by remember { mutableStateOf("") }
    var isCorreoValido by remember { mutableStateOf(false) }

    LaunchedEffect(sessionViewModel.snackbarMessage) {
        sessionViewModel.snackbarMessage.collect { message ->
            message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                sessionViewModel.clearSnackbarMessage()
            }
        }
    }

    LaunchedEffect(sessionViewModel.resetSuccess) {
        sessionViewModel.resetSuccess.collect { status ->
            if (status == true) {
                navController.navigate("resetPassword")
            }
        }
    }


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
                .padding(26.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier.offset(y = 35.dp)
            )
        }


        Row(
            modifier = Modifier
                .padding(120.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)

        ) {
            Image(
                painter = painterResource(id = R.drawable.candado), contentDescription = "Logo",
                modifier = Modifier
                    .height(90.dp)
                    .width(90.dp)
                    .offset(y = (-90).dp),

            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "¿Tienes problemas para ingresar a tu cuenta?",
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = (-220).dp)

        )

        Text(
            text = "Introduce tu correo electrónico y te enviaremos un código para que puedas ingresar de nuevo",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 26.dp)
                .offset(y = (-220).dp)

        )

        OutlinedTextField(
            value = correo,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                correo = filteredText
                isCorreoValido = isValidEmail(filteredText) // Verificar si el correo tiene un formato válido
            },
            label = { Text("Ingresa tu correo electrónico", fontSize = 14.sp) },

            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = (-220).dp)

        )

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.offset(y = (-220).dp)

        ) {
            Button(
                onClick = {
                    sessionViewModel.forgotPassword(ForgotPasswordData(correo))
                },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp, vertical = 10.dp)
                    .height(55.dp),
                enabled = isCorreoValido // Habilitar el botón solo si el correo es válido
            )
            {
                Text("Enviar", color = Color.Black,
                    style = TextStyle(
                        fontFamily = Utendo,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                    ))
            }

        }
        Spacer(modifier = Modifier.height(15.dp))

        Box(
            modifier = Modifier.offset(y = (-230).dp)
        ) {
            Button( onClick = { navController.navigate("loginUser_screens") },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp, vertical = 10.dp)
                    .height(55.dp)
            )
            {
                Text("Cancelar", color = Color.White,
                    style = TextStyle(
                        fontFamily = Utendo,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                    ))
            }
        }
    }
}

private fun isValidEmail(email: String): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(email).matches()
}


data class ForgotPasswordData (
    val correo: String,
)