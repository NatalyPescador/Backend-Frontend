package com.GanApp.viewsganapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.models.ChatEntity
import com.GanApp.viewsganapp.models.ChatItemsDto
import com.GanApp.viewsganapp.utils.BaseUrlConstant

@Composable
fun ChatItem(
    chat: ChatItemsDto,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false // Puedes agregar un estado para saber si el item está seleccionado
) {
    val containerColor = if (isSelected) Color(10, 191, 4) else Color.White // Colores personalizados
    val filename = chat.imagen.substringAfterLast('\\')
    val imageUrl = BaseUrlConstant.BASE_URL + "uploads/$filename"

    Card(
        modifier = modifier
            .padding(8.dp)
            .clickable {
                navController.navigate("chat_message/${chat.chatId}")
            },
        colors = CardDefaults.cardColors(containerColor = containerColor), // Aplicar color de fondo
        elevation = CardDefaults.cardElevation(30.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(), // Ocupar todo el ancho disponible
            verticalAlignment = Alignment.CenterVertically // Alinear verticalmente los elementos al centro
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "Sin nombre",
                modifier = Modifier
                    .size(64.dp) // Tamaño de la imagen, ajusta según sea necesario
                    .clip(CircleShape), // Darle forma circular a la imagen
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp)) // Espaciado entre la imagen y el texto
            Text(
                text = chat.nombreCompleto,
                color = Color.Black
            )
        }
    }
}
