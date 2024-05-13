    package com.GanApp.viewsganapp.navigation

    import androidx.compose.runtime.collectAsState
    import androidx.compose.foundation.layout.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.unit.dp

    import android.annotation.SuppressLint
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.material3.Button
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.setValue
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


    private val Any.route: String
        get() {
            TODO("Not yet implemented")
        }

    sealed class AppScreens(val route: String) {
        object loginUser : AppScreens("loginUser_screens")
        object viewReister : AppScreens("viewRegister")
        object conecctionFacebook : AppScreens ("facebook")
        object conecctionGmail : AppScreens("gmail")
        object forgotPassword : AppScreens ("forgotPassword")
        object resetPassword : AppScreens("resetPassword")
        object productRegister : AppScreens("productRegister")

        object productListScreen :AppScreens("ProductListScreen")

    }

    @SuppressLint("ComposableDestinationInComposeScope", "UnrememberedMutableState")
    @Composable
    fun AppScreens(navController: NavController) {
        @Composable
        fun ProductItem(product: Int) {

        }

        fun ProductRegister(navController: NavController) {

        }

        fun LogIn(navController: NavHostController, any: Any) {

        }
        NavHost(
            navController = navController as NavHostController,
            startDestination = AppScreens.loginUser.route
        ) {
            composable(AppScreens.loginUser.route) {
                @Composable
                fun LogIn(onSubmit: (LogInData) -> Unit, navController: NavHostController) {
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
                composable(AppScreens.forgotPassword.route) {
                    @Composable
                    fun ForgotPassword(onSubmit: (ForgotPasswordData) -> Unit) {
                        var correo by remember { mutableStateOf("") }
                    }
                }
                composable(AppScreens.resetPassword.route) {
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



                composable(AppScreens.productListScreen.route){
                    @Composable
                    fun ProductListScreen(){

                    }
                }




            }
        }
    }





