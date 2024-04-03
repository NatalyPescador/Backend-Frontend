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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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

    var imagenes by remember { mutableStateOf("") }



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
                // Cargar el recurso de la imagen PNG como un pintor
                val painter = painterResource(id = R.drawable.nombre_producto_icn)
                // Utilizar el pintor en el Icon
                Icon(
                    painter = painter, contentDescription = "Nombre",
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp)

        )

        OutlinedTextField(
            value = precio,
            onValueChange = {
                // Filtrar el texto para permitir solo números y puntos decimales
                val filteredText = it.filter { char ->
                    char.isDigit() || char == '.'
                }
                precio = filteredText
            },
            label = { Text("Precio") },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                val painter = painterResource(id = R.drawable.dolar_icn)
                Icon(
                    painter = painter, contentDescription = "Precio",
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.offset(y = 20.dp),
            // Configuración del teclado para permitir solo números y puntos decimales
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            // Filtrar la entrada para permitir solo números y puntos decimales
            keyboardActions = KeyboardActions.Default,
            singleLine = true,

            )

        OutlinedTextField(
            value = descripcion,
            onValueChange = {
                val filteredText = it.replace("\n", "")
                descripcion = filteredText
            },
            label = { Text("Descripcion") },
            visualTransformation = PasswordVisualTransformation(),
            textStyle = TextStyle(color = Color.Black),
            leadingIcon = {
                val painter = painterResource(id = R.drawable.descripcion_icn)
                Icon(
                    painter = painter, contentDescription = "Descripción",
                    modifier = Modifier.size(24.dp)
                )
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
                val painter = painterResource(id = R.drawable.imagenes_icn)
                Icon(
                    painter = painter, contentDescription = "Icono de imagenes",
                    modifier = Modifier.size(24.dp)
                )
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
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Utiliza el ícono PNG convertido en vector drawable
                    Icon(
                        painter = painterResource(id = R.drawable.promocion_icn),
                        contentDescription = "Descripción del icono",
                        modifier = Modifier.size(24.dp) // Tamaño del ícono
                    )
                    Text("Publicar", color = Color.Black, modifier = Modifier.padding(start = 8.dp))
                }
            }

        }

        Spacer(modifier = Modifier.height(30.dp))

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



data class ProductData(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val imagen: String,
    val tipoServicioId: Long,
    val categoriaId: Long,
    val usuarioId: Long
)