package com.GanApp.viewsganapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.GanApp.viewsganapp.models.TipoServicioEntity


@Composable
fun TipoServicioDropdown(
    tiposServicio: List<TipoServicioEntity>,
    selectedTipoServicio: TipoServicioEntity?,
    onTipoServicioSelected: (TipoServicioEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var displayText = selectedTipoServicio?.nombre ?: "Seleccione un tipo de servicio"

    Box(modifier = Modifier.fillMaxWidth().padding(16.dp).clickable { expanded = true }) {
        Text(text = displayText)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            tiposServicio.forEach { tipoServicio ->
                DropdownMenuItem(
                    onClick = {
                        onTipoServicioSelected(tipoServicio)
                        expanded = false
                    },
                    //leadingIcon = { Icon(Icons.Filled.ArrowDropDown, contentDescription = null) },
                    text = { Text(tipoServicio.nombre ?: "Sin nombre") }
                )
            }
        }
    }
}