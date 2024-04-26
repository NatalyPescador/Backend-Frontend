package com.GanApp.viewsganapp.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import android.net.Uri
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun DisplayImage(imageUri: Uri?) {
    imageUri?.let {
        val painter: Painter = rememberAsyncImagePainter(model = it)
        Image(
            painter = painter,
            contentDescription = "Imagen seleccionada",
            modifier = Modifier.size(100.dp)
        )
    }
}