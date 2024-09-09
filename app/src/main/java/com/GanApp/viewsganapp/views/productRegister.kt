package com.GanApp.viewsganapp.views

import android.net.Uri
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.filled.AddLocationAlt
import androidx.compose.material.icons.filled.Apps
import androidx.compose.material.icons.filled.AspectRatio
import androidx.compose.material.icons.filled.Balance
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.ContentPaste
import androidx.compose.material.icons.filled.SwipeDownAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
import com.GanApp.viewsganapp.models.ProductDataDto
import com.GanApp.viewsganapp.models.TipoServicioEntity
import com.GanApp.viewsganapp.ui.theme.Utendo
import com.GanApp.viewsganapp.utils.getUserData
import com.GanApp.viewsganapp.viewModels.ProductViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductRegister(navController: NavController, onSubmit: (ProductDataDto) -> Unit) {
    val viewModel: ProductViewModel = viewModel()
    val context = LocalContext.current
    val userData = getUserData(context)
    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var raza by remember { mutableStateOf("") }
    var sexo by remember { mutableStateOf("") }
    var uom by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf("") }
    var departamento by remember { mutableStateOf("") }
    var municipio by remember { mutableStateOf("") }
    var selectedCategoria by remember { mutableStateOf<CategoriaEntity?>(null) }
    var selectedTipoServicio by remember { mutableStateOf<TipoServicioEntity?>(null) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    LaunchedEffect(selectedTipoServicio) {
        selectedTipoServicio?.tipoServicioId?.let {
            viewModel.fetchCategoriasByTipoServicio(it)
        }
    }

    BackHandler {
        navController.navigate("homePage") {
            popUpTo(0) { inclusive = true } // Elimina toda la pila de navegación
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {}, // Deja el título vacío para no mostrar contenido
                navigationIcon = {}, // Deja el icono de navegación vacío
                actions = {}, // Deja las acciones vacías
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color(255, 255, 255)              )// Deja las acciones vacías)
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
                .fillMaxWidth()
                .background(Color.White)
                .verticalScroll(rememberScrollState())
                .offset(y = (30).dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Registrar Producto",
                fontFamily = Utendo,
                fontSize = 35.sp,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.offset(y = (-30).dp)
            )

            OutlinedTextField(
                value = nombre,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    nombre = filteredText
                },
                label = { Text("Nombre del producto", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Nombre",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = precio,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() || char == '.' }
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
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = descripcion,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    descripcion = filteredText
                },
                label = { Text("Descripción", color=Color.Black) },
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
            Spacer(modifier = Modifier.height(5.dp))

            TipoServicioDropdown(
                tiposServicio = viewModel.tiposServicio.value,
                selectedTipoServicio = selectedTipoServicio,
                onTipoServicioSelected = { tipoServicio ->
                    selectedTipoServicio = tipoServicio
                }
            )

            if (selectedTipoServicio != null) {
                CategoriaDropdown(
                    categorias = viewModel.categorias.value,
                    selectedCategoria = selectedCategoria,
                    onCategoriaSelected = { categoria ->
                        selectedCategoria = categoria
                    }
                )
            }
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = raza,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    raza = filteredText
                },
                label = { Text("Raza", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        imageVector = Icons.Default.AspectRatio,
                        contentDescription = "Raza",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = sexo,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    sexo = filteredText
                },
                label = { Text("Sexo", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        imageVector = Icons.Default.SwipeDownAlt,
                        contentDescription = "Sexo",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = uom,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    uom = filteredText
                },
                label = { Text("Unidad de medida", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        imageVector = Icons.Default.Balance,
                        contentDescription = "Unidad de Medida",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = edad,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                    edad = filteredText
                },
                label = { Text("Edad", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.dolar_icn)
                    Icon(
                        imageVector = Icons.Default.ContentPaste,
                        contentDescription = "Edad",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = cantidad,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                    cantidad = filteredText
                },
                label = { Text("Cantidad", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.dolar_icn)
                    Icon(
                        imageVector = Icons.Default.Apps,
                        contentDescription = "Cantidad",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = departamento,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    departamento = filteredText
                },
                label = { Text("Departamento", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        imageVector = Icons.Default.AddLocationAlt,
                        contentDescription = "Departamento",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

            OutlinedTextField(
                value = municipio,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    municipio = filteredText
                },
                label = { Text("Municipio", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    //val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        imageVector = Icons.Default.AddLocationAlt,
                        contentDescription = "Municipio",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4))
            ) {
                Text("Añadir imagen",
                    style = TextStyle(
                    fontFamily = Utendo,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                    )
                )
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(model = it),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier.size(100.dp)
                )
            }

            Box(modifier = Modifier.offset(y = 20.dp)) {
                Button(
                    onClick = {
                        imageUri?.let { uri ->
                            viewModel.uploadProductData(
                                context,
                                uri,
                                ProductDataDto(
                                    nombre = nombre,
                                    precio = precio,
                                    descripcion = descripcion,
                                    raza = raza,
                                    sexo = sexo,
                                    uom = uom,
                                    edad = edad,
                                    cantidad = cantidad,
                                    departamento = departamento,
                                    municipio = municipio,
                                    imagen = uri.toString(),
                                    tipoServicioId = selectedTipoServicio?.tipoServicioId ?: 0,
                                    categoriaId = selectedCategoria?.categoriaId ?: 0,
                                    usuarioId = userData?.userId ?: 0L
                                )
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(Color(10, 191, 4), contentColor = Color.Black)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            //painter = painterResource(id = R.drawable.promocion_icn),
                            imageVector = Icons.Default.Campaign,
                            contentDescription = "Descripción del icono",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                        Text("Publicar",
                            style = TextStyle(
                                fontFamily = Utendo,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 21.sp // Puedes ajustar el tamaño según lo necesites
                            ),
                            color = Color.White, modifier = Modifier.padding(start = 8.dp))
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