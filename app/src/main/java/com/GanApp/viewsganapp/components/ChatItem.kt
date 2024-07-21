package com.GanApp.viewsganapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.models.ChatEntity

@Composable
fun ChatItem(chat: ChatEntity, navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("chat_message/${chat.chatId}")
            },
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