package com.GanApp.viewsganapp.navigation



import androidx.compose.runtime.observeAsState
import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.GanApp.viewsganapp.viewModels.ProductViewModel
import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.ProductData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.UserData


sealed class AppScreens(val route: String) {
    object loginUser : AppScreens("loginUser_screens")
    object viewReister : AppScreens("viewRegister")
    object conecctionFacebook : AppScreens ("facebook")
    object conecctionGmail : AppScreens("gmail")
    object forgotPassword : AppScreens ("forgotPassword")
    object resetPassword : AppScreens("resetPassword")
    object productRegister : AppScreens("productRegister")
    object productViewModel : AppScreens("productView")

    sealed class Screen(val route: String) {
        object ProductList : Screen("productList")
        object ProductDetail : Screen("productDetail")
    }
}


@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun AppScreens(navController: NavController) {
    @Composable
    fun ProductItem(product: Int) {

    }
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
            /** @Composable
            fun ProductRegister(navController: NavController, onSubmit: (ProductData) -> Unit) {
                val viewModel: ProductViewModel = viewModel()

                // Observa los tipos de servicio y categorías
                val tiposServicio by viewModel.tiposServicio.observeAsState(emptyList())
                val categorias by viewModel.categorias.observeAsState(emptyList())

                // Resto del código para mostrar y manejar los datos...
            }
            }
        }
    }



       


