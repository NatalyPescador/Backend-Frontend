package com.GanApp.viewsganapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterButton(
    expanded: Boolean,
    onExpand: () -> Unit,
    onDismiss: () -> Unit,
    onFilterSelected: (Long) -> Unit
) {
    val tiposServicio = listOf(
        TipoServicio(1, "Ganado"),
        TipoServicio(2, "Insumo"),
        TipoServicio(3, "Servicios")
    )

    Box (
        modifier = Modifier
            .padding(start = 25.dp, top = 10.dp)
    ){
        Button(
            onClick = { onExpand() },
            colors = ButtonDefaults.buttonColors(Color(10, 191, 4)),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier//.padding(16.dp)
                .offset(x = (-10).dp)
        ) {
            Text(
                text = "Filtrar",
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 21.sp
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Filtrar")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onDismiss() },
            modifier = Modifier
                .background(Color.White)
        ) {
            tiposServicio.forEach { tipo ->
                DropdownMenuItem( text = {
                    Text(
                        text = tipo.nombre,
                        color = Color(10, 191, 4)
                        )
                },
                    onClick = {
                        onFilterSelected(tipo.id)
                        onDismiss()
                    }
                )
            }
        }
    }
}

data class TipoServicio(
    val id: Long,
    val nombre: String
)
