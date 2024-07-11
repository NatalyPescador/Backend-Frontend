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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.components.ChatItem
import com.GanApp.viewsganapp.viewModels.ChatViewModel

@Composable
fun ShowChats(navController: NavHostController, userId: Long, chatViewModel: ChatViewModel = viewModel()){

    val chats by chatViewModel.chats.collectAsState()
    val snackbarHostState = remember {SnackbarHostState()}
    val snackbarMessage by chatViewModel.snackbarMessage.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(userId){
        chatViewModel.getChatsByUserId(userId)
    }

    LaunchedEffect(snackbarMessage){
        snackbarMessage?.let {
            snackbarHostState.showSnackbar(it)
            chatViewModel._snackbarMessage.value = null
        }
    }

    Scaffold (
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .background(color = Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(), // Esto hará que la Column ocupe todo el tamaño disponible
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ){
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier.offset(y = 35.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "TUS CHATS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            chats.forEach { chat ->
                ChatItem(chat = chat, navController = navController)
                Spacer(modifier = Modifier.height(8.dp))
            }

        }
    }
}