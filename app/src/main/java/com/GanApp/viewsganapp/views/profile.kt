package com.GanApp.viewsganapp.views

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.viewModels.UserProfileViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavHostController, context: Context) {
    val viewModel : UserProfileViewModel = viewModel()
    val user by viewModel.user.collectAsState()
    val loading by viewModel.loading.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchUserData(context)
    }

    LaunchedEffect(user) {
        user?.let {
            nombreCompleto = it.nombreCompleto
            correo = it.correo
            numeroTelefono = it.numeroTelefono
        }
    }

    LaunchedEffect(viewModel.snackbarMessage) {
        viewModel.snackbarMessage.collect { message ->
            message?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                viewModel.clearSnackbarMessage()
            }
        }
    }

    BackHandler {
        navController.navigate("homePage") {
            popUpTo(0) { inclusive = true } // Elimina toda la pila de navegación
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, // Deja el título vacío para no mostrar contenido
                navigationIcon = {}, // Deja el icono de navegación vacío
                actions = {},
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 255, 255)              )// Deja las acciones vacías
            )
        }
    ) { innerPadding ->
        if (loading) {
            // Mostrar un indicador de carga mientras se cargan los datos
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxHeight()
                    .background(Color.White)
                    .verticalScroll(rememberScrollState())
                    .offset(y = (-55).dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,

            ) {
                Text(
                    text = "Mi Perfil",
                    fontFamily = Utendo,
                    fontSize = 35.sp,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(15.dp))

                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray)
                        .border(2.dp, Color.Gray, CircleShape)
                ) {
                    // Aquí puedes añadir una imagen de perfil usando Coil o cualquier otra biblioteca de imágenes
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = nombreCompleto,
                    onValueChange = { if (isEditing) nombreCompleto = it },
                    label = { Text(text = "Nombre") },
                    textStyle = TextStyle(color = Color.Black),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Nombre"
                        )
                    },
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = isEditing
                )

                OutlinedTextField(
                    value = correo,
                    onValueChange = { if (isEditing) correo = it },
                    label = { Text("Correo electrónico") },
                    textStyle = TextStyle(color = Color.Black),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Email, contentDescription = "Gmail")
                    },
                    modifier = Modifier
                        .offset(y = (-20).dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = isEditing
                )

                OutlinedTextField(
                    value = numeroTelefono,
                    onValueChange = { if (isEditing) numeroTelefono = it },
                    label = { Text("Teléfono") },
                    textStyle = TextStyle(color = Color.Black),
                    leadingIcon = {
                        Icon(imageVector = Icons.Filled.Phone, contentDescription = "Teléfono")
                    },
                    modifier = Modifier
                        .offset(y = (-40).dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = isEditing
                )
                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier.offset(y = (-50).dp)
                ) {
                    if (isEditing) {
                        Button(
                            onClick = {
                                val updatedUser = user?.copy(
                                    nombreCompleto = nombreCompleto,
                                    correo = correo,
                                    numeroTelefono = numeroTelefono
                                )
                                if (updatedUser != null) {
                                    viewModel.upgradeUser(context, updatedUser)
                                }
                                isEditing = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                Color(10, 191, 4),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Guardar cambios", color = Color.Black)
                        }
                    } else {
                        Button(
                            onClick = { isEditing = true },
                            colors = ButtonDefaults.buttonColors(
                                Color(10, 191, 4),
                                contentColor = Color.Black
                            )
                        ) {
                            Text("Editar perfil", color = Color.White,
                                style = TextStyle(
                                    fontFamily = Utendo,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}