package com.GanApp.viewsganapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.GanApp.viewsganapp.R
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable as Composable


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {

    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    // Estado de carga añadido
    var isLoading by remember { mutableStateOf(false) }

    val items = listOf(
        DrawerItem(
            title = "Login",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = "loginUser_screens"
        ),
        DrawerItem(
            title = "Perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = "Profile_screens"
        ),
        DrawerItem(
            title = "Catálogo",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = "homePage"
        ),
        DrawerItem(
            title = "Favoritos",
            selectedIcon = Icons.Filled.FavoriteBorder,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            route = "favorito"
        ),
        DrawerItem(
            title = "Registrar producto",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = "productRegister"
        ),
        DrawerItem(
            title = "ShowChats",
            selectedIcon = Icons.Filled.Create,
            unselectedIcon = Icons.Outlined.Create,
            route = "ChatView"
        )


    )

    Box {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(modifier = Modifier.padding(end = 50.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(195, 252, 219))
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            Spacer(modifier = Modifier.height(26.dp))
                            Image(
                                painter = painterResource(id = R.drawable.icono_proyect),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(150.dp)
                                    .size(150.dp)
                                    .fillMaxWidth()
                                    .align(CenterHorizontally)
                            )
                            Spacer(modifier = Modifier.height(26.dp))
                            items.forEachIndexed { index, drawerItem ->
                                NavigationDrawerItem(label = {
                                    Text(text = drawerItem.title)
                                },
                                    selected = index == selectedItemIndex,
                                    onClick = {
                                        isLoading = true // Comienza la carga inmediatamente
                                        selectedItemIndex = index
                                        scope.launch {
                                            delay(100L) // Pequeño retraso para asegurar que la IU se actualice
                                            navigationState.close()
                                            navController.navigate(drawerItem.route) {
                                                // Evita la duplicación de destinos en la pila de back stack
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                            delay(100L) // Asegurarse de que la IU tenga tiempo de actualizarse
                                            isLoading = false // Termina la carga
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedItemIndex) {
                                                drawerItem.selectedIcon
                                            } else drawerItem.unselectedIcon,
                                            contentDescription = drawerItem.title
                                        )

                                    },
                                    badge = {
                                        drawerItem.badgeCount?.let {
                                            Text(text = drawerItem.badgeCount.toString())
                                        }
                                    },

                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }
                    }
                }
            },
            drawerState = navigationState,
        ) {
            Scaffold(topBar = {
                TopAppBar(title = {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier
                            .height(200.dp)
                            .width(200.dp)
                    )
                }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navigationState.open()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp)
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(Color(152, 255, 150))
                )
            }
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .background(color = Color.White) // Cambiar el fondo a blanco
                ) {
                    CatalogoPrincipal(navController = navController)
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


data class DrawerItem (
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)