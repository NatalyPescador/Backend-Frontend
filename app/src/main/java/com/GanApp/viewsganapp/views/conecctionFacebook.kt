package com.GanApp.viewsganapp.views

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R

@Composable

fun Facebook (navController: NavController){

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .padding(16.dp)
            .offset(y = (-70).dp)
            .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

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
            text = "GanApp está solicitando acceso a:",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 16.dp)
                .offset(y = 35.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(text = "Tus nombres, tu foto de perfil y tu dirección de correo electrónico",
            fontSize = 20.sp,
            textAlign = TextAlign.Center)

        Box(
            modifier = Modifier.offset(y = 30.dp)
        ) {
            Button( onClick = { },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 10.dp)
            )
            {
                Text("Continuar", color = Color.Black,
                    fontSize = 17.sp)
            }
        }

        Box(
            modifier = Modifier.offset(y = 20.dp)
        ) {
            Button( onClick = { navController.navigate("loginUser_screens") },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 65.dp, vertical = 10.dp)
            )
            {
                Text("Cancelar", color = Color.Black,
                    fontSize = 17.sp)
            }
        }

    }
}