/*package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R

@Composable
fun DetalleProducto(navController: NavController){
    @Composable
    fun VerDetalle(productIndex: Int) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Button(
                onClick = { /* Handle filter button click */ },
                modifier = Modifier.padding(bottom = 16.dp)
            ) {
                Text(text = "Filtrar")
            }
            Image(
                painter = painterResource(id = R.drawable.gmail_logo),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.4f.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Precio: $${product.precio}",
                //style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Usuario: ${product.usuarioId}",
                //style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Categoría: ${product.categoriaId}",
                //style = MaterialTheme.typography.body1
            )
            Spacer(modifier = Modifier.height(8.dp))
            /*Text(
                text = "Descripción: ${product.descripcion}",
                //style = MaterialTheme.typography.body1
            )*/
        }
    }

}*/

