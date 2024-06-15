package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.GanApp.viewsganapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favoritos(navController: NavController){

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("homePage") }) {
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
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trabajando), contentDescription = "Logo",
                    modifier = Modifier
                        .width(300.dp)
                        .height(300.dp)
                )
            }
        }
    }
}