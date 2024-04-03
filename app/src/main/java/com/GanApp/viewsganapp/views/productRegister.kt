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


@Composable
fun ProductRegister(navController: NavController, onSubmit: (ProductData) -> Unit) {
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var imagen by remember { mutableStateOf("") }
    var tipoServicioId by remember { mutableStateOf("") }
    var categoriaId by remember { mutableStateOf("") }
    var usuarioId by remember { mutableStateOf("") }



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
            text = "Registrar Producto",
            fontSize = 40.sp,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 20.dp)

        )

        OutlinedTextField(
            value = nombre,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                nombre = filteredText
            },
            label = { Text("Nombre producto") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "gmail")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = precio,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                precio = filteredText
            },
            label = { Text("Precio") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                descripcion = filteredText
            },
            label = { Text("Descripcion") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = imagen,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                imagen = filteredText
            },
            label = { Text("Imagenes") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = tipoServicioId,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                tipoServicioId = filteredText
            },
            label = { Text("Tipo de Servicio") },
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock, contentDescription = "telefono")
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = categoriaId,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                categoriaId = filteredText
            },
            label = { Text("Tipo de Categoria") },
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

                onClick = {
                    val tipoServicioLong = tipoServicioId.toLongOrNull() ?: 0L // Convierte a Long o usa 0L si falla
                    val categoriaLong = categoriaId.toLongOrNull() ?: 0L // Convierte a Long o usa 0L si falla
                    onSubmit(ProductData(nombre, precio, descripcion, imagen, tipoServicioLong, categoriaLong, 122))
                },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4),
                    contentColor = Color.Black
                )
            )
            {
                Text("Publicar", color = Color.Black)
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
    }
}



data class ProductData(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val imagen: String,
    val tipoServicioId: Long,
    val categoriaId: Long,
    val usuarioId: Long
)