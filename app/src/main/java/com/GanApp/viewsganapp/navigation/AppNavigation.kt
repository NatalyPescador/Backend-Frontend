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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.ProductData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.ReviewData
import com.GanApp.viewsganapp.views.UserData

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
    object detalleProd : AppScreens("detalleProd")
    object CreateChatView : AppScreens("CreateChatView")
    object ChatView : AppScreens("ChatView")

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

            composable(AppScreens.conecctionFacebook.route){
                @Composable
                fun Facebook(){}
            }

            composable(AppScreens.conecctionGmail.route){
                @Composable
                fun Gmail(){}
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
            composable(AppScreens.productRegister.route){
                @Composable
                fun ProductRegister(navController: NavController, onSubmit: (ProductData) -> Unit) {
                    var nombre by remember { mutableStateOf("") }
                    var precio by remember { mutableStateOf("") }
                    var descripcion by remember { mutableStateOf("") }
                    var imagenes by remember { mutableStateOf("") }
                }
            }
            composable(AppScreens.homePage.route){
                @Composable
                fun HomePage() {

                    val navigationState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()
                    var selectedItemIndex by rememberSaveable {
                        mutableIntStateOf(0)
                    }
                }

                composable(AppScreens.catalogo.route){
                    @Composable
                    fun CatalogoPrincipal(){}
                }

                composable(AppScreens.detalleProd.route){
                    @Composable
                    fun DetalleProducto(){}
                }

            }

            composable(AppScreens.profile.route) {
                 @Composable
                 fun Perfil() {}
            }

            composable(AppScreens.reviews.route) {
                 @Composable
                 fun PublishReview(
                     navController: NavController,
                     onSubmit: (ReviewData) -> Unit
                 ) {
                     var resena by remember {
                         mutableStateOf("")
                     }
                 }
            }

            composable(AppScreens.CreateChatView.route){
                @Composable
                fun CreateChat(navController: NavHostController){}
            }

            composable(AppScreens.ChatView.route){
                @Composable
                fun ShowChats(navController: NavController){}
            }

    }
}