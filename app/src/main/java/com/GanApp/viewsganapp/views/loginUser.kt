package com.GanApp.viewsganapp.views

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.models.LogInData
import com.GanApp.viewsganapp.viewModels.LoginViewModel
import kotlinx.coroutines.launch

@Composable
fun LogIn(navController: NavController, context: Context, snackbarHostState: SnackbarHostState, onLogin: (LogInData) -> Unit) {
    val viewModel: LoginViewModel = viewModel()
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(viewModel.snackbarMessage) {
        viewModel.snackbarMessage.collect { message ->
            Log.d("LogIn", "Mensaje recibido en LogIn: $message")
            message?.let {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(it)
                    viewModel._snackbarMessage.value = null
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .offset(y = 50.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                modifier = Modifier
                    .offset(y = 35.dp)
            )
        }

        Text(
            text = "Iniciar Sesión",
            color = Color.Black,
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

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Button(
                onClick = {
                    val loginData = LogInData(correo, password)
                    Log.d("LoginUser", "Intentando iniciar sesión con correo: $correo")
                    onLogin(loginData)
                },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4),
                    contentColor = Color.Black
                )
            ) {
                Text("Iniciar Sesión", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Text(text = "¿Aun no tienes cuenta?",
                color = Color.Black)
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "Regístrate",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.clickable {
                    navController.navigate("viewRegister")
                }
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "¿Olvidaste tu contraseña?",
            color = Color.Black,
            modifier = Modifier
                .clickable {
                    navController.navigate("forgotPassword")
                }
                .offset(x = 50.dp)
        )

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .offset(y = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
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
                Spacer(modifier = Modifier.height(2.dp))
                Text(text = "Continuar con",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("facebook")
                        })
                Text(text = "Facebook",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("facebook")
                        })
            }
            Spacer(modifier = Modifier.width(40.dp))
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
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Continuar con",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .clickable {
                            navController.navigate("gmail")
                        })
                Text(text = "Gmail",
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
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
