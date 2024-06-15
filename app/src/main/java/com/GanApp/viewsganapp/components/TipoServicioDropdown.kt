package com.GanApp.viewsganapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.GanApp.viewsganapp.models.TipoServicioEntity

@Composable
fun TipoServicioDropdown(
    tiposServicio: List<TipoServicioEntity>,
    selectedTipoServicio: TipoServicioEntity?,
    onTipoServicioSelected: (TipoServicioEntity) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val displayText = selectedTipoServicio?.nombre ?: "Tipo de servicio"

    Box(modifier = Modifier
        .padding(8.dp)) {
        Column {
            OutlinedTextField(
                value = displayText,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo servicio", color = Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Tipo servicio",
                        modifier = Modifier.size(30.dp).clickable { expanded = !expanded }
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(280.dp) // Ajusta el ancho para que sea igual a los otros campos
                    .offset(y = (-5).dp)
                    .clickable { expanded = !expanded }
            )

            if (expanded) {
                Surface(
                    modifier = Modifier
                        .width(280.dp)
                        .background(Color.White)
                        .offset(y = 2.dp),
                    shape = RoundedCornerShape(15.dp),
                    shadowElevation = 8.dp
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .heightIn(max = 200.dp) // Altura máxima del DropdownMenu
                    ) {
                        items(tiposServicio) { tipoServicio ->
                            Text(
                                text = tipoServicio.nombre ?: "Sin nombre",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        onTipoServicioSelected(tipoServicio)
                                        expanded = false
                                    }
                                    .padding(8.dp),
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

