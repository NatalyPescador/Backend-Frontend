package com.GanApp.viewsganapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.GanApp.viewsganapp.navigation.AppScreens
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.viewmodels.UserProfileViewModel
import com.GanApp.viewsganapp.views.*

class MainActivity : ComponentActivity() {
    private val userProfileViewModel: UserProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ViewsGanAppTheme {
                NavHost(
                    navController = navController,
                    startDestination = AppScreens.homePage.route
                ) {
                    composable(AppScreens.viewReister.route) {
                        Register(navController = navController) { userData ->
                            // Llamada a la API para registrar usuario
                        }
                    }

                    composable(AppScreens.loginUser.route) {
                        LogIn(navController = navController) { logInData ->
                            // Llamada a la API para iniciar sesión
                        }
                    }

                    composable(AppScreens.conecctionFacebook.route) {
                        Facebook(navController = navController)
                    }

                    composable(AppScreens.conecctionGmail.route) {
                        Gmail(navController = navController)
                    }

                    composable(AppScreens.forgotPassword.route) {
                        ForgotPassword(navController = navController) { forgotPasswordData ->
                            // Llamada a la API para recuperar contraseña
                        }
                    }

                    composable(AppScreens.resetPassword.route) {
                        ResetPassword(navController = navController) { resetPasswordData ->
                            // Llamada a la API para resetear contraseña
                        }
                    }

                    composable(AppScreens.productRegister.route) {
                        ProductRegister(navController = navController) {
                            // Lógica para registrar producto
                        }
                    }

                    composable(AppScreens.homePage.route) {
                        HomePage(navController = navController)
                    }

                    composable(AppScreens.profile.route) {
                        Perfil(navController = navController, viewModel = userProfileViewModel)
                    }

                    composable(AppScreens.catalogo.route) {
                        CatalogoPrincipal(navController = navController)
                    }

                    composable(AppScreens.reviews.route) {
                        PublishReview(navController = navController) { reviewData ->
                            // Llamada a la API para publicar reseña
                        }
                    }

                    composable(AppScreens.favorite.route) {
                        Favoritos(navController = navController)
                    }

                    composable(AppScreens.CreateChatView.route) {
                        CreateChat(navController = navController)
                    }

                    composable(AppScreens.ChatView.route) {
                        val userId = 15L
                        ShowChats(navController = navController, userId = userId)
                    }
                }
            }
        }
    }
}
