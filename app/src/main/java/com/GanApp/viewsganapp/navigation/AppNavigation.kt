package com.GanApp.viewsganapp.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.UserData

sealed class AppScreens(val route: String) {
    object loginUser : AppScreens("loginUser_screens")
    object viewReister : AppScreens("viewRegister")
    object conecctionFacebook : AppScreens ("facebook")
    object conecctionGmail : AppScreens("gmail")
}

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppScreens(navController: NavController) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = AppScreens.loginUser.route
    ) {
        composable(AppScreens.loginUser.route) {
            @Composable
            fun LogIn(onSubmit: (LogInData) -> Unit) {
                var correo by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                // Composable para la pantalla de inicio de sesión
            }
            composable(AppScreens.viewReister.route) {
                @Composable
                fun Register(onSubmit: (UserData) -> Unit) {
                    var nombreCompleto by remember { mutableStateOf("") }
                    var correo by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var numeroTelefono by remember { mutableStateOf("") }
                // Composable para la pantalla de registro
                }
            }

            composable(AppScreens.conecctionFacebook.route){
                @Composable
                fun Facebook(){}
            }

            composable(AppScreens.conecctionGmail.route){
                @Composable
                fun Gmail(){}
            }
        }
    }

}


       


