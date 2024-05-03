package com.GanApp.viewsganapp.views

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.GanApp.viewsganapp.navigation.AppScreens
import kotlinx.coroutines.launch
import androidx.compose.runtime.Composable as Composable
import com.GanApp.viewsganapp.views.LogIn

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(navController: NavHostController) {

    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    /*LaunchedEffect(key1 = true) {
        // Posible inicialización o acciones adicionales
        navigationState.close()  // Ejemplo de cómo asegurar que el drawer esté cerrado al inicio
    }*/


    val items = listOf(
        DrawerItem(
            title = "Perfil",
            selectedIcon = Icons.Filled.Person,
            unselectedIcon = Icons.Outlined.Person,
            route = "Profile_screens"
        ),
        DrawerItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = "ruta_a_home",

        ),
        DrawerItem(
            title = "Favorites",
            selectedIcon = Icons.Filled.FavoriteBorder,
            unselectedIcon = Icons.Outlined.FavoriteBorder,
            route = "ruta_a_home",
        ),
    )

    Surface (
    ){
        ModalNavigationDrawer(
            drawerContent = {

                ModalDrawerSheet(modifier = Modifier.padding(end = 50.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color(195,252,219))
                    ) {
                        Column(
                            modifier = Modifier
                                .verticalScroll(rememberScrollState())
                        ) {
                            Spacer(modifier = Modifier.height(26.dp))
                            Image(
                                painter = painterResource(id = R.drawable.icproject),
                                contentDescription = "",
                                modifier = Modifier
                                    .width(230.dp)
                                    .height(230.dp)
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
                                        selectedItemIndex = index
                                        scope.launch {
                                            navigationState.close()
                                            navController.navigate(drawerItem.route)
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
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )

                            }
                        }
                    }
                }
            }, drawerState = navigationState,



        ) {
            Scaffold( topBar = {
                TopAppBar(title = { Image(painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo", modifier = Modifier
                        .height(200.dp)
                        .width(200.dp)
                 )

                }, navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            navigationState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp)

                        )
                    }
                },  colors = TopAppBarDefaults.topAppBarColors(Color(152, 255, 150))

                )
            }
                ) {
                Column (
                    ){
                    LogIn(navController = navController) { }

                }

            }

        }
    }

    // to define navigation drawer here
}

data class DrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badgeCount: Int? = null,
    val route: String
)


