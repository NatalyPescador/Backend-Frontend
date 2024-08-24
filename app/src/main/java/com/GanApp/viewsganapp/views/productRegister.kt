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
import com.GanApp.viewsganapp.models.ProductDataDto
import com.GanApp.viewsganapp.models.TipoServicioEntity
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
                    containerColor = Color(152, 255, 150),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.Red
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
        ) {
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
                modifier = Modifier.offset(y = (-35).dp)
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
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
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

            OutlinedTextField(
                value = raza,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    raza = filteredText
                },
                label = { Text("Raza", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Raza",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            OutlinedTextField(
                value = sexo,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    sexo = filteredText
                },
                label = { Text("Sexo", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Sexo",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            OutlinedTextField(
                value = uom,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    uom = filteredText
                },
                label = { Text("Unidad de Medida", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Unidad de Medida",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            OutlinedTextField(
                value = edad,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                    edad = filteredText
                },
                label = { Text("Edad", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.dolar_icn)
                    Icon(
                        painter = painter, contentDescription = "Edad",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true
            )

            OutlinedTextField(
                value = cantidad,
                onValueChange = {
                    val filteredText = it.filter { char -> char.isDigit() || char == '.' }
                    cantidad = filteredText
                },
                label = { Text("Cantidad", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.dolar_icn)
                    Icon(
                        painter = painter, contentDescription = "Cantidad",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions.Default,
                singleLine = true
            )

            OutlinedTextField(
                value = departamento,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    departamento = filteredText
                },
                label = { Text("Departamento", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Departamento",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            OutlinedTextField(
                value = municipio,
                onValueChange = {
                    val filteredText = it.replace("\n", "")
                    municipio = filteredText
                },
                label = { Text("Municipio", color=Color.Black) },
                textStyle = TextStyle(color = Color.Black),
                leadingIcon = {
                    val painter = painterResource(id = R.drawable.nombre_producto_icn)
                    Icon(
                        painter = painter, contentDescription = "Municipio",
                        modifier = Modifier.size(24.dp)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier.offset(y = (-5).dp)
            )

            Spacer(modifier = Modifier.height(5.dp))

            Button(
                onClick = { imagePickerLauncher.launch("image/*") },
                colors = ButtonDefaults.buttonColors(Color(10, 191, 4))
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
                            painter = painterResource(id = R.drawable.promocion_icn),
                            contentDescription = "Descripción del icono",
                            modifier = Modifier.size(24.dp)
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