package com.GanApp.viewsganapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.viewModels.ChatMessagesViewModel
import com.GanApp.viewsganapp.models.MessageDto
import com.GanApp.viewsganapp.utils.getUserData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatMessage(navController: NavHostController, chatId: Long) {
    val context = LocalContext.current
    val userId = remember { getUserData(context)?.userId ?: 0L }
    val chatViewModel: ChatMessagesViewModel = viewModel()
    var message by remember { mutableStateOf("") }
    val messages by chatViewModel.messages.collectAsState()

    // Aquí puedes añadir la lógica para obtener la imagen de perfil y el nombre del usuario desde el backend
    val profileImageUrl = "https://example.com/path/to/profile/image.jpg" // Reemplaza esta URL con la URL real
    val userName by remember { mutableStateOf("Nombre del destinatario") } // Nombre de ejemplo

    LaunchedEffect(chatId) {
        chatViewModel.getMessagesByChatId(chatId)
        // Lógica para obtener la imagen de perfil y el nombre del usuario
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(profileImageUrl),
                            contentDescription = "Foto de perfil",
                            modifier = Modifier
                                .size(50.dp) // Tamaño de la imagen
                                .clip(CircleShape)
                                .background(Color.LightGray)
                                .border(2.dp, Color.Gray, CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = userName,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                            color = Color.Black)
                    }
                },
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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxSize()
                .padding(paddingValues)
                .nestedScroll(rememberNestedScrollInteropConnection()), // Agregar nestedScrol
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Sección para mostrar los mensajes
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                messages.forEach { msg ->
                    MessageBubble(message = msg.message, isSentByCurrentUser = msg.senderId == userId)
                    Spacer(modifier = Modifier.height(8.dp)) // Espacio entre mensajes
                }
            }

            // Row inferior para escribir y enviar mensajes
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                TextField(
                    value = message,
                    onValueChange = { newValue -> message = newValue },
                    modifier = Modifier
                        .weight(1f)
                        .border(1.dp, Color.Gray, RoundedCornerShape(20.dp)), // Borde redondeado
                    placeholder = { Text(text = "Escribe un mensaje") },
                    shape = RoundedCornerShape(20.dp),// Redondear el campo de texto
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(10, 191, 4)) // Color de fondo del círculo
                ) {
                    IconButton(
                        onClick = {
                            if (message.isNotEmpty()) {
                                val messageDto = MessageDto(
                                    chatId = chatId,
                                    message = message,
                                    status = "SENT",
                                    senderId = userId  // userId del que registro el producto
                                )
                                chatViewModel.sendMessage(messageDto)
                                message = ""  // Limpiar el campo de texto después de enviar
                            }
                        },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Enviar",
                            tint = Color.White // Cambiar el color del icono a blanco
                        )
                    }
                }
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
                .background(color = backgroundColor, shape = RoundedCornerShape(8.dp)) // Bordes redondeados para los mensajes
                .padding(8.dp)
        ) {
            Text(text = message, color = Color.Black) // Color de la letra de los textos
        }
    }
}
