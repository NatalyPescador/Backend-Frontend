package com.GanApp.viewsganapp.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditarPerfil(navController: NavController){

    // Aquí definimos los datos del perfil (simulados)
    var name by remember { mutableStateOf("Nombre de Usuario") }
    var email by remember { mutableStateOf("usuario@ejemplo.com") }
    var phoneNumber by remember { mutableStateOf("+123 456 789") }
    var password by remember { mutableStateOf("Hola") }
    var birthDate by remember { mutableStateOf("01/01/1990") }

    // Estado para mostrar u ocultar la contraseña
    var passwordVisible by remember { mutableStateOf(false) }

    // Recordatorio del estado de la URI de la imagen seleccionada
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // ActivityResultLauncher para seleccionar la imagen de la galería
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("Profile_screens") }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Volver"
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(152, 255, 150), // Cambia este color según tus necesidades
                    titleContentColor = Color.White, // Color del título
                    navigationIconContentColor = Color.Black, // Color del icono de navegación
                    actionIconContentColor = Color.Red // Color de los iconos de acción
                )
            )
        }
    ) { innerPadding ->
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
                text = "Editar Perfil",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.Black // Cambia este color según tus necesidades
            )

            // Imagen de perfil redonda con clickable
            Box(
                modifier = Modifier
                    .size(150.dp) // Tamaño del contenedor
                    .clip(CircleShape) // Hacer que la imagen sea circular
                    .background(Color.LightGray)
                    .border(2.dp, Color.Gray, CircleShape) // Borde alrededor de la imagen
                    .clickable { launcher.launch("image/*") } // Abre la galería para seleccionar una imagen
            ) {
                if (imageUri == null) {
                    // Mostrar el ícono de "más" si no hay imagen seleccionada
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Agregar Imagen",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(100.dp)
                    )
                } else {
                    // Mostrar la imagen seleccionada
                    Image(
                        painter = rememberAsyncImagePainter(imageUri),
                        contentDescription = "Foto de perfil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Nombre") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Nombre"
                    )
                },
                modifier = Modifier.padding(16.dp),
                shape = RoundedCornerShape(20.dp)
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Gmail") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "Gmail")
                },
                modifier = Modifier
                    .offset(y = (-20).dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp)
            )

            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Telefono") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "Telefono")
                },
                modifier = Modifier
                    .offset(y = (-40).dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp)
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
                    Row {
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(
                                imageVector = if (passwordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                                contentDescription = if (passwordVisible) "Ocultar contraseña" else "Mostrar contraseña"
                            )
                        }
                    }
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp)
            )


            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier.offset(y = (-50).dp)
            ) {
                Button(
                    onClick = { /* Aquí puedes manejar la acción de guardar cambios */ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(10, 191, 4),
                        contentColor = Color.Black
                    )
                ) {
                    Text("Guardar cambios", color = Color.Black)
                }
            }
        }
    }
}