package com.GanApp.viewsganapp.views

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.AddTask
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.R
import com.GanApp.viewsganapp.models.ProductDataDto
import com.GanApp.viewsganapp.navigation.AppScreens
import com.GanApp.viewsganapp.viewModels.ChatViewModel
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String,
    val iconColor: Color = Color(2,115,10),
)


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {

    val context = LocalContext.current
    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(1)
    }

    val viewModel: ProductViewModel = viewModel()

    val onSubmit: (ProductDataDto) -> Unit = { productDataDto ->
        // Usar context correctamente aquí
        viewModel.uploadProductData(
            context,
            Uri.parse(productDataDto.imagen), // Suponiendo que la imagen es una URI en cadena
            productDataDto
        )
    }
    val chatViewModel: ChatViewModel = viewModel()
    val productViewModel: ProductViewModel = viewModel()


    // Estado de carga añadido
    var isLoading by remember { mutableStateOf(false) }
    var currentScreen by remember { mutableStateOf(AppScreens.profile.route) }


    val items = listOf(

        DrawerItem(
            title = "Mi perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = AppScreens.profile.route
        ),
        DrawerItem(
            title = "Catálogo",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = AppScreens.catalogo.route
        ),
        DrawerItem(
            title = "Registrar producto",
            selectedIcon = Icons.Filled.AddTask,
            unselectedIcon = Icons.Outlined.AddTask,
            route = AppScreens.productRegister.route
        ),
        DrawerItem(
            title = "Mis chats",
            selectedIcon = Icons.Filled.Chat,
            unselectedIcon = Icons.Outlined.Chat,
            route = AppScreens.ChatMessages.route
        ),
        DrawerItem(
            title = "Mis productos",
            selectedIcon = Icons.Filled.AddShoppingCart,
            unselectedIcon = Icons.Outlined.AddShoppingCart,
            route = "mis_productos"
        ),
        DrawerItem(
            title = "Cerrar sesión",
            selectedIcon = Icons.Filled.Logout,
            unselectedIcon = Icons.Outlined.Logout,
            route = "logout"
        ),
        )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxSize()
            .background(Color.White)
    ) {
        ModalNavigationDrawer(
            modifier = Modifier
                .background(Color.White),
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .width(270.dp) // Ajusta el ancho del Drawer
                        .background(Color.White)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()

                            .background(
                                color = Color(
                                    255,
                                    255,
                                    255
                                )
                            )//.background(color = Color(195, 252, 219))
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                                .background(color = Color(255, 255, 255))
                                .fillMaxHeight()
                        ) {
                            Spacer(modifier = Modifier.height(26.dp))
                            Image(
                                painter = painterResource(id = R.drawable.icono_proyect),
                                contentDescription = "",
                                modifier = Modifier
                                    //.width(150.dp)
                                    //.height(150.dp)
                                    .size(150.dp)
                                    //.fillMaxWidth()
                                    .align(CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(26.dp))

                            items.forEachIndexed { index, drawerItem ->
                                val isSelected = index == selectedItemIndex
                                val textColor = if (isSelected) Color.White else Color.Black
                                val iconColor = if (isSelected) Color.White else Color.Black

                                Box(
                                    modifier = Modifier
                                        .background(Color.White)
                                )

                                {
                                    NavigationDrawerItem(

                                        colors = NavigationDrawerItemDefaults.colors(
                                            selectedContainerColor = Color(10, 191, 4),
                                            unselectedContainerColor = Color.White
                                        ),

                                        label = {
                                            Text(
                                                text = drawerItem.title,
                                                color = textColor
                                            )
                                        },
                                        selected = isSelected,
                                        onClick = {
                                            isLoading = true // Comienza la carga inmediatamente
                                            selectedItemIndex = index
                                            if (drawerItem.title == "Cerrar sesión") {
                                                // Lógica para cerrar sesión
                                                scope.launch {
                                                    delay(100L) // Pequeño retraso para asegurar que la IU se actualice
                                                    navController.navigate(AppScreens.loginUser.route) {
                                                        popUpTo(0) // Despeja todo el back stack para que no pueda volver
                                                    }
                                                }
                                            } else {
                                                currentScreen = drawerItem.route
                                                scope.launch {
                                                    delay(100L) // Pequeño retraso para asegurar que la IU se actualice
                                                    navigationState.close()
                                                    delay(100L) // Asegurarse de que la IU tenga tiempo de actualizarse
                                                    isLoading = false // Termina la carga
                                                }
                                            }
                                        },
                                        icon = {
                                            Icon(
                                                imageVector = if (isSelected) {

                                                    drawerItem.selectedIcon
                                                } else drawerItem.unselectedIcon,
                                                contentDescription = drawerItem.title,
                                                tint = iconColor, // Aplicar el color del icono
                                                modifier = Modifier.size(24.dp)


                                            )

                                        },

                                        badge = {
                                            drawerItem.badgeCount?.let {
                                                Text(text = drawerItem.badgeCount.toString())
                                            }

                                        },

                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(NavigationDrawerItemDefaults.ItemPadding)
                                            .background(Color.White)
                                    )
                                }
                                Spacer(modifier = Modifier.height(10.dp))


                            }
                            Row(
                                modifier = Modifier
                                    .height(50.dp)
                                    .background(Color.White)
                            ) {}
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            },
            drawerState = navigationState,
        ) {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(205.dp)
                            .padding(5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .height(200.dp)
                                .width(200.dp)

                        )
                        Image(
                            painter = painterResource(id = R.drawable.vaca_titulo),
                            contentDescription = "Vaca",
                            modifier = Modifier
                                .height(60.dp)
                                .width(85.dp)
                                .offset(y = 65.dp)
                                .offset(x = 45.dp)

                        )

                    }

                }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navigationState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color(2, 115, 10),
                            modifier = Modifier.size(24.dp),

                            )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(Color(255, 255, 255))
                )
            }
            ) { innerPadding ->
                val context: Context = LocalContext.current

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = Color.White) // Cambiar el fondo a blanco
                ) {
                    when (items[selectedItemIndex].title) {
                        "Mi perfil" -> Perfil(navController = navController, context = context)
                        "Catálogo" -> CatalogoPrincipal(navController = navController)
                        "Registrar producto" -> ProductRegister(
                            navController = navController,
                            onSubmit = onSubmit
                        )

                        "Mis chats" -> ShowChats(
                            navController = navController,
                            chatViewModel = chatViewModel
                        )

                        "Mis productos" -> MisProductos(navController = navController)

                        else -> CatalogoPrincipal(navController = navController) // Fallback si no se encuentra la opción
                    }

                }
            }
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0x80000000)), // Fondo semi-transparente
                contentAlignment = Alignment.Center // Centrar el contenido
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icono_proyect), // Tu imagen de carga
                    contentDescription = "Loading",
                    modifier = Modifier
                        .size(200.dp) // Ajusta el tamaño de la imagen aquí
                )
            }
        }
    }
}




