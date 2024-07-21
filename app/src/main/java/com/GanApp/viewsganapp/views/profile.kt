package com.GanApp.viewsganapp.views


import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.viewmodels.UserProfileViewModel
import androidx.compose.material3.Icon as Icon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavHostController) {
    val viewModel : UserProfileViewModel = viewModel()
    val user by viewModel.user.collectAsState()
    val loading by viewModel.loading.collectAsState()

    var isEditing by remember { mutableStateOf(false) }
    var nombreCompleto by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var numeroTelefono by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.fetchUserData(17L)
    }

    LaunchedEffect(user) {
        user?.let {
            nombreCompleto = it.nombreCompleto
            correo = it.correo
            numeroTelefono = it.numeroTelefono
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("homePage") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Volver"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate("favorito") }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Favorito"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(152, 255, 150),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Red
                )
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
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Mi Perfil",
                    fontSize = 35.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    color = Color.Black
                )

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
                    label = { Text("Gmail") },
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
                        Icon(imageVector = Icons.Filled.Phone, contentDescription = "Telefono")
                    },
                    modifier = Modifier
                        .offset(y = (-40).dp)
                        .padding(16.dp),
                    shape = RoundedCornerShape(20.dp),
                    enabled = isEditing
                )

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
                                    viewModel.upgradeUser(updatedUser)
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
                            Text("Editar perfil", color = Color.Black)
                        }
                    }
                }
            }
        }
    }
}