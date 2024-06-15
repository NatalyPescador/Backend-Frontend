package com.GanApp.viewsganapp.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.viewModels.ButtonCreateChatViewModel
import kotlinx.coroutines.launch

@Composable
fun CreateChat(navController: NavHostController, buttonCreateChatViewModel: ButtonCreateChatViewModel = viewModel()) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by buttonCreateChatViewModel.snackbarMessage.collectAsState()

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            buttonCreateChatViewModel._snackbarMessage.value = null // Reset message after showing
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top // Cambiado a Top para que los elementos comiencen desde la parte superior
        ) {
            // SnackbarHost en la parte superior
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Logo
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

            // Botón para crear el chat
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    coroutineScope.launch {
                        try {
                            buttonCreateChatViewModel.createChat(productId = 14L, userId = 15L, receiverId = 8L)
                            Log.d("chatView", "Botón presionado para crear el chat")
                        } catch (e: Exception) {
                            Log.e("chatView", "Error al crear el chat: ${e.message}", e)
                        }
                    }
                }) {
                    Text(text = "+")
                }
            }
        }
    }
}