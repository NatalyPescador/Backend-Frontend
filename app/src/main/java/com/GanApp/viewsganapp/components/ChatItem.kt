package com.GanApp.viewsganapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.models.ChatEntity

@Composable
fun ChatItem(
    chat: ChatEntity,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false // Puedes agregar un estado para saber si el item está seleccionado
) {
    val containerColor = if (isSelected) Color(10, 191, 4) else Color.Blue // Colores personalizados
    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("chat_message/${chat.chatId}")
            },
        colors = CardDefaults.cardColors(containerColor = containerColor), // Aplicar color de fondo
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = "User ID: ${chat.userId}")
            }
            Row {
                Text(text = "Nombre producto: ${chat.productId}")
            }
            Row {
                Text(text = "Estas hablando con: ${chat.receiverId}")
            }
        }
    }
}
