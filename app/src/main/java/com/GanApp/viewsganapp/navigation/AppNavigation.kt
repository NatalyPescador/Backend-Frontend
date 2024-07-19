package com.GanApp.viewsganapp.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.GanApp.viewsganapp.components.ChatMessage
import com.GanApp.viewsganapp.views.CatalogoPrincipal
import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.ProductData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.UserData
import com.GanApp.viewsganapp.views.VerDetalle

sealed class AppScreens(val route: String) {
    object loginUser : AppScreens("loginUser_screens")
    object viewReister : AppScreens("viewRegister")
    object conecctionFacebook : AppScreens ("facebook")
    object conecctionGmail : AppScreens("gmail")
    object forgotPassword : AppScreens ("forgotPassword")
    object resetPassword : AppScreens("resetPassword")
    object productRegister : AppScreens("productRegister")
    object homePage: AppScreens ("homePage")
    object profile : AppScreens ("Profile_screens")
    object reviews : AppScreens ("reviews")
    object catalogo : AppScreens("catalogo")
    object detalleProd : AppScreens("detalleProd/{productId}")
    object editProfile : AppScreens ("edit_profile")
    object favorite: AppScreens ("favorito")
    object CreateChatView : AppScreens("CreateChatView")
    object ChatView : AppScreens("ChatView")
    object ChatMessages : AppScreens("chat_message/{chatId}")
    object menuDetalleProd : AppScreens("menuDetalleProd/{productId}")
    companion object {
        fun editProfile(any: Any) {

        }
    }

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

            composable(AppScreens.conecctionFacebook.route) {
                @Composable
                fun Facebook() {
                }
            }

            composable(AppScreens.conecctionGmail.route) {
                @Composable
                fun Gmail() {
                }
            }

            composable(AppScreens.forgotPassword.route){
                @Composable
                fun ForgotPassword(onSubmit: (ForgotPasswordData) -> Unit) {
                    var correo by remember { mutableStateOf("") }
                }
            }

            composable(AppScreens.resetPassword.route){
                @Composable
                fun ResetPassword(onSubmit: (ResetPasswordData) -> Unit) {
                    var token by remember { mutableStateOf("") }
                    var newPassword by remember { mutableStateOf("") }
                    var confirmedPassword by remember { mutableStateOf("") }
                }
            }
            composable(AppScreens.productRegister.route) {
                @Composable
                fun ProductRegister(navController: NavController, onSubmit: (ProductData) -> Unit) {
                    var nombre by remember { mutableStateOf("") }
                    var precio by remember { mutableStateOf("") }
                    var descripcion by remember { mutableStateOf("") }
                    var imagenes by remember { mutableStateOf("") }
                }
            }

            composable(AppScreens.homePage.route) {
                @Composable
                fun HomePage() {

                    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableIntStateOf(0)
                    }
                }
            }
        composable(AppScreens.catalogo.route) {
            CatalogoPrincipal(navController = navController)
            }

        composable(AppScreens.detalleProd.route) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toLong() ?: 0L
            VerDetalle(navController = navController, productId = productId)
        }

        composable(AppScreens.menuDetalleProd.route){
            @Composable
            fun menuDetalleProd(){}
        }

        composable(AppScreens.profile.route) {
            @Composable
            fun Perfil() {}
        }

        composable(AppScreens.ChatView.route){
            @Composable
            fun ShowChats(navController: NavController){}
        }

        composable(AppScreens.favorite.route){
            @Composable
            fun Favoritos(navController: NavController){

            }
        }

            composable(
                route = AppScreens.ChatMessages.route,
                arguments = listOf(navArgument("chatId") { type = NavType.LongType })
            ) { backStackEntry ->
                val chatId = backStackEntry.arguments?.getLong("chatId") ?: 0L
                ChatMessage(navController = navController, chatId = chatId)
            }

    }
}