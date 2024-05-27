package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.apiService.ReviewApiService
import androidx.compose.material3.Icon as Icon


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Perfil(navController: NavController){

    // Aquí definimos los datos del perfil (simulados)
    val name = "Nombre de Usuario"
    val email = "usuario@ejemplo.com"
    val phoneNumber = "+123 456 789"
    val password = "********"
    val birthDate = "01/01/1990"




    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { /* Acción al hacer clic en el ícono de navegación */ }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Volver"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Acción al hacer clic en el ícono de acción */ }) {
                        Icon(
                            imageVector = Icons.Filled.Favorite,
                            modifier = Modifier.size(35.dp),
                            contentDescription = "Favorito"
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
                .background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Mi Perfil",
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.Black // Cambia este color según tus necesidades
            )

            Image(
                painter = painterResource(id = R.drawable.icproject), // Reemplaza con el recurso de tu imagen
                contentDescription = "Foto de perfil",
                modifier = Modifier
                    .size(135.dp)
                    .clip(CircleShape) // Hacer que la imagen sea circular
                    .border(2.dp, Color.Gray, CircleShape),
                contentScale = ContentScale.Crop // Escalar la imagen para llenar el tamaño establecido
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = name,
                onValueChange = { /* No es necesario hacer nada ya que es de solo lectura */ },
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
                onValueChange = { /* No es necesario hacer nada ya que es de solo lectura */ },
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
                onValueChange = { /* No es necesario hacer nada ya que es de solo lectura */ },
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
                onValueChange = { /* No es necesario hacer nada ya que es de solo lectura */ },
                label = { Text("Contraseña") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.Lock, contentDescription = "Contraseña")
                },
                modifier = Modifier
                    .offset(y = (-60).dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp)
            )

            OutlinedTextField(
                value = birthDate,
                onValueChange = { /* No es necesario hacer nada ya que es de solo lectura */ },
                label = { Text("Fecha de Nacimiento") },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(imageVector = Icons.Filled.DateRange, contentDescription = "Fecha de nacimirnto")
                },
                modifier = Modifier
                    .offset(y = (-80).dp)
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp)
            )

            // Aquí puedes agregar cualquier otro contenido que desees mostrar.
        }
    }

}