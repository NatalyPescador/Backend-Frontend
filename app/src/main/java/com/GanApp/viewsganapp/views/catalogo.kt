package com.GanApp.viewsganapp.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.components.FilterButton
import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.utils.BaseUrlConstant
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CatalogoPrincipal(navController: NavController, productViewModel: ProductViewModel = viewModel()) {
    val products by remember { mutableStateOf(productViewModel.products) }
    var filterExpanded by remember { mutableStateOf(false) }
    var selectedFilter by remember { mutableStateOf<Long?>(null) } // Estado para el filtro seleccionado

    Column {
        FilterButton(
            expanded = filterExpanded,
            onExpand = { filterExpanded = true },
            onDismiss = { filterExpanded = false },
            onFilterSelected = { tipoServicioId ->
                selectedFilter = tipoServicioId
                productViewModel.filterProductsByTipoServicio(tipoServicioId)
            },
            selectedFilter = selectedFilter
        )

        Catalogo(productos = products, navController = navController)
    }
}

@Composable
fun Catalogo(productos: List<ProductoEntity>, navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Iterar sobre los productos en pasos de 2
        items((productos.size + 1) / 2) { rowIndex ->
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (i in 0 until 2) {
                    val index = rowIndex * 2 + i
                    if (index < productos.size) {
                        Tarjeta(producto = productos[index], navController = navController)
                    } else {
                        // Si no hay un segundo producto, añade un espacio para mantener la consistencia
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
fun Tarjeta(producto: ProductoEntity, navController: NavController) {
    val filename = producto.imagen?.substringAfterLast('\\') ?: ""
    val imageUrl = BaseUrlConstant.BASE_URL + "uploads/$filename"
    val numberFormat = NumberFormat.getInstance(Locale("es", "CO")).apply {
        maximumFractionDigits = 0
    }

    Surface(
        modifier = Modifier
            .padding(end = 8.dp)
            .width(150.dp)
            .height(260.dp),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 3.dp
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .clickable {
                    navController.navigate("detalleProd/${producto.productoId}")
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = producto.nombre ?: "Sin nombre",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = producto.nombre ?: "Sin nombre",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${numberFormat.format(producto.precio.toDouble())}",
                modifier = Modifier.padding(horizontal = 8.dp),
                fontStyle = FontStyle.Italic
            )
            Text(
                text = "${producto.descripcion}",
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}



