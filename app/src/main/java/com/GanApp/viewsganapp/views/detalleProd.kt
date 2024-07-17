package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R


@Composable
fun VerDetalle(navController: NavController) {

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Button(
                onClick = { /* Handle filter button click */ },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Filtrar", fontSize = 18.sp)
            }

        }

        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagenes_icn ),
                contentDescription = null,
                modifier = Modifier
                    .offset(y = 35.dp)
                    .width(300.dp)
                    .height(350.dp)
            )
        }

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            items(additionalImages) { imageUrl ->
                Image(
                    painter = painterResource(id = imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .size(100.dp)

                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Button(
                onClick = { /* Handle filter button click */ },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier.padding(16.dp)
            ) {

                Icon(imageVector = Icons.Default.Person, contentDescription = "Contactar al Vendedor")

                Text(text = "Contactar al Vendedor", fontSize = 18.sp)

            }

        }


        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Descripción: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Raza: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Departamento: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Municipio: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Nombre del ejemplar: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Criadero: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Sexo: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Peso: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Edad: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Precio: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Vendedor: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Categoría: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cantidad: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cantidad: ", fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))


    }
}


val additionalImages = listOf(
    R.drawable.logo,
    R.drawable.gannap_cabeza,
    R.drawable.gmail_logo,
    R.drawable.imagenes_icn,
    R.drawable.gannap_cabeza
)
