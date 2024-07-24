package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.components.ChatItem
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
        chatViewModel.getChatsByUserId(userId)
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
                modifier = Modifier
                    .padding(start = 16.dp) // Más espacio arriba
                    .fillMaxWidth()
                    .offset(y= (-20).dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(140.dp) // Tamaño del logo más grande
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "TUS CHATS",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterVertically) // Centrar verticalmente el texto
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de chats
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                chats.forEach { chat ->
                    ChatItem(chat = chat, navController = navController)
                }
            }
        }
    }
}
