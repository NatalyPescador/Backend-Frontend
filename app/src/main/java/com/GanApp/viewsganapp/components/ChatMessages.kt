package com.GanApp.viewsganapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.viewModels.ChatMessagesViewModel
import com.GanApp.viewsganapp.models.MessageDto

@Composable
fun ChatMessage(navController: NavHostController, chatId: Long) {
    val chatViewModel: ChatMessagesViewModel = viewModel()
    var message by remember { mutableStateOf("") }
    val messages by chatViewModel.messages.collectAsState()

    LaunchedEffect(chatId) {
        chatViewModel.getMessagesByChatId(chatId)
    }

    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Row superior para el nombre del destinatario
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Nombre del destinatario", style = MaterialTheme.typography.bodyMedium)
        }

        // Sección para mostrar los mensajes
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            messages.forEach { msg ->
                MessageBubble(message = msg.message, isSentByCurrentUser = msg.senderId == 8L)
            }
        }

        // Row inferior para escribir y enviar mensajes
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = message,
                onValueChange = { newValue -> message = newValue },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "Escribe un mensaje") }
            )
            IconButton(
                onClick = {
                    if (message.isNotEmpty()) {
                        val messageDto = MessageDto(
                            chatId = chatId,
                            message = message,
                            status = "SENT",
                            senderId = 8L  // Ajuste temporal para prueba
                        )
                        chatViewModel.sendMessage(messageDto)
                        message = ""  // Limpiar el campo de texto después de enviar
                    }
                },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Enviar"
                )
            }
        }
    }
}

@Composable
fun MessageBubble(message: String, isSentByCurrentUser: Boolean) {
    val backgroundColor = if (isSentByCurrentUser) Color(0xFFD1F1C6) else Color(0xFFF1F1F1)
    val horizontalArrangement = if (isSentByCurrentUser) Arrangement.End else Arrangement.Start
    val padding = if (isSentByCurrentUser) PaddingValues(start = 64.dp, end = 8.dp) else PaddingValues(start = 8.dp, end = 64.dp)

    Row(
        horizontalArrangement = horizontalArrangement,
        modifier = Modifier
            .fillMaxWidth()
            .padding(padding)
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .padding(8.dp)
        ) {
            Text(text = message)
        }
    }
}