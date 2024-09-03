package com.GanApp.viewsganapp.views

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.components.ChatItem
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.utils.getUserData
import com.GanApp.viewsganapp.viewModels.ChatViewModel

@Composable
fun ShowChats(navController: NavHostController, chatViewModel: ChatViewModel = viewModel()) {
    val context = LocalContext.current
    val userId = remember { getUserData(context)?.userId ?: 0L }
    val chats by chatViewModel.chats.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarMessage by chatViewModel.snackbarMessage.collectAsState()


    LaunchedEffect(userId) {
        chatViewModel.getChatDetailsByUserId(userId)
    }

    LaunchedEffect(snackbarMessage) {
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            chatViewModel._snackbarMessage.value = null
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.White)
                .fillMaxSize() // Ocupa todo el tamaño disponible
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 8.dp), // Ajustes de padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            // Encabezado con logo
            Row(
                verticalAlignment = Alignment.Top, // Alineación superior
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (30).dp),

            ) {

                Text(
                    text = "Tus Chats",
                    fontFamily = Utendo,
                    fontSize = 35.sp,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically) // Centrar verticalmente el texto
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Lista de chats
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                chats.forEach { chat ->
                    ChatItem(
                        chat = chat,
                        navController = navController,
                        modifier = Modifier.fillMaxWidth() // Ocupa todo el ancho disponible
                    )
                    Spacer(modifier = Modifier.height(1.dp)) // Espaciado entre los elementos
                }
            }
        }
    }
}
