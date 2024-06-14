package com.GanApp.viewsganapp.views

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.components.CategoriaDropdown
import com.GanApp.viewsganapp.components.TipoServicioDropdown
import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity
import com.GanApp.viewsganapp.viewModels.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductRegister(navController: NavController, onSubmit: (ProductData) -> Unit) {
    val viewModel: ProductViewModel = viewModel()
    val context = LocalContext.current
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    //var imagen by remember { mutableStateOf("") }
    var selectedCategoria by remember { mutableStateOf<CategoriaEntity?>(null) }
    var selectedTipoServicio by remember { mutableStateOf<TipoServicioEntity?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    // Observa cambios en selectedTipoServicio y carga las categorías relacionadas
    LaunchedEffect(selectedTipoServicio) {
        selectedTipoServicio?.tipoServicioId?.let {
            viewModel.fetchCategoriasByTipoServicio(it)
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("") },
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
                    containerColor = Color(
                        152,
                        255,
                        150
                    ), // Cambia este color según tus necesidades
                    titleContentColor = Color.White, // Color del título
                    navigationIconContentColor = Color.Black, // Color del icono de navegación
                    actionIconContentColor = Color.Red // Color de los iconos de acción
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .background(Color.White)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )

        {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), contentDescription = "Logo",
                    modifier = Modifier.offset(y = (-20).dp)
                )
            }
            Text(
                text = "Registrar producto",
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    //.padding(bottom = 16.dp)
                    .offset(y = (-35).dp)

            )


            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    nombre = filteredText
                },
                label = { Text("Nombre producto", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    // Cargar el recurso de la imagen PNG como un pintor
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    // Utilizar el pintor en el Icon
                    Icon(
                        painter = painter, contentDescription = "Nombre",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)

            )

            OutlinedTextField(
                value = precio,
                onValueChange = {
                    // Filtrar el texto para permitir solo números y puntos decimales
                    val filteredText = it.filter { char ->
                        char.isDigit() || char == '.'
                    }
                    precio = filteredText
                },
                label = { Text("Precio", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.dolar_icn)
                    Icon(
                        painter = painter, contentDescription = "Precio",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp),
                // Configuración del teclado para permitir solo números y puntos decimales
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                // Filtrar la entrada para permitir solo números y puntos decimales
                keyboardActions = KeyboardActions.Default,
                singleLine = true,

                )

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    descripcion = filteredText
                },
                label = { Text("Descripcion", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.descripcion_icn)
                    Icon(
                        painter = painter, contentDescription = "Descripción",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)

            )

            TipoServicioDropdown(
                tiposServicio = viewModel.tiposServicio.value,
                selectedTipoServicio = selectedTipoServicio,
                onTipoServicioSelected = { tipoServicio ->
                    selectedTipoServicio = tipoServicio
                }
            )

            if (selectedTipoServicio != null) { // Mostrar solo si hay un tipo de servicio seleccionado
                CategoriaDropdown(
                    categorias = viewModel.categorias.value,
                    selectedCategoria = selectedCategoria,
                    onCategoriaSelected = { categoria ->
                        selectedCategoria = categoria
                    }
                )

            }


            Spacer(modifier = Modifier.height(5.dp)) // Añade espacio entre el formulario y el botón

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4)
                )
            ) {
                Text("Seleccionar Imagen")
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier.size(100.dp)
                )
            }

            Box(
                modifier = Modifier.offset(y = 20.dp)

            ) {
                Button(

                onClick = {
                    imageUri?.let { uri ->
                        viewModel.uploadProductData(
                            context,
                            uri,
                            ProductData(
                                nombre = nombre,  // Asegúrate de que 'nombre' está definido en tu estado composable
                                precio = precio,  // Asegúrate de que 'precio' está definido en tu estado composable
                                descripcion = descripcion,  // Asegúrate de que 'descripcion' está definido en tu estado composable
                                imagen = uri.toString(),
                                tipoServicioId = selectedTipoServicio?.tipoServicioId ?: 0,
                                categoriaId = selectedCategoria?.categoriaId ?: 0,
                                usuarioId = 8  // Ejemplo de usuario ID
                            )
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    Color(10, 191, 4),
                    contentColor = Color.Black
                )
            )
            {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Utiliza el ícono PNG convertido en vector drawable
                    Icon(
                        painter = painterResource(id = R.drawable.promocion_icn),
                        contentDescription = "Descripción del icono",
                        modifier = Modifier.size(24.dp) // Tamaño del ícono
                    )
                    Text("Publicar", color = Color.Black, modifier = Modifier.padding(start = 8.dp))
                }
            }

            }

            Spacer(modifier = Modifier.height(30.dp))

            repeat(3) {
                Text(
                    text = "Regístrate",
                    color = Color.White,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
        }
    }
}



data class ProductData(
    val nombre: String,
    val precio: String,
    val descripcion: String,
    val imagen: String,
    val tipoServicioId: Long,
    val categoriaId: Long,
    val usuarioId: Long
)