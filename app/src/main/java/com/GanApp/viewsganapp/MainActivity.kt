package com.GanApp.viewsganapp

import EditarPerfil
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.GanApp.viewsganapp.components.ChatMessage
import com.GanApp.viewsganapp.navigation.AppScreens
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.utils.BackgroundTimer
import com.GanApp.viewsganapp.utils.TokenManager
import com.GanApp.viewsganapp.viewModels.LoginViewModel
import com.GanApp.viewsganapp.views.CatalogoPrincipal
import com.GanApp.viewsganapp.views.Facebook
import com.GanApp.viewsganapp.views.Favoritos
import com.GanApp.viewsganapp.views.ForgotPassword
import com.GanApp.viewsganapp.views.Gmail
import com.GanApp.viewsganapp.views.HomePage
import com.GanApp.viewsganapp.views.LogIn
import com.GanApp.viewsganapp.views.MisProdDetalles
import com.GanApp.viewsganapp.views.MisProductos
import com.GanApp.viewsganapp.views.MostrarMenuDetalleProd
import com.GanApp.viewsganapp.views.Perfil
import com.GanApp.viewsganapp.views.ProductRegister
import com.GanApp.viewsganapp.views.Register
import com.GanApp.viewsganapp.views.ResetPassword
import com.GanApp.viewsganapp.views.ShowChats
import com.GanApp.viewsganapp.views.VerDetalle
import com.GanApp.viewsganapp.views.errorMessageForgotPassword
import com.GanApp.viewsganapp.views.errorMessageRegister
import com.GanApp.viewsganapp.views.errorMessageResetPassword
import com.GanApp.viewsganapp.views.showErrorForgotPassword
import com.GanApp.viewsganapp.views.showErrorRegister
import com.GanApp.viewsganapp.views.showErrorResetPassword
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private lateinit var backgroundTimer: BackgroundTimer

    @SuppressLint("ComposableDestinationInComposeScope")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate: Resetting token")
        TokenManager.resetToken(this)

        backgroundTimer = BackgroundTimer(this) {
            runOnUiThread {
                navController.navigate(AppScreens.homePage.route) {
                    popUpTo(AppScreens.loginUser.route) {
                        inclusive = true
                    }
                }
            }
        }

        ProcessLifecycleOwner.get().lifecycle.addObserver(backgroundTimer)

        setContent {
            navController = rememberNavController()
            val snackbarHostState = remember { SnackbarHostState() }

            ViewsGanAppTheme {
                Log.d("MainActivity", "Setting up NavHost")
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = AppScreens.homePage.route,
                        modifier = Modifier.padding(padding)
                    ) {
                        composable(AppScreens.viewReister.route) {
                            Register(navController = navController) { userData ->
                                val call = RetrofitInstance.apiService.createUser(userData)
                                call.enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if (response.isSuccessful) {
                                            Log.d("API Call", "Usuario creado con éxito")
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Log.d("API Call", "Response not successful: $errorBody")
                                            if (!errorBody.isNullOrEmpty()) {
                                                try {
                                                    val json = JSONObject(errorBody)
                                                    errorMessageRegister = json.getString("errorMessage")
                                                    showErrorRegister = true
                                                } catch (e: JSONException) {
                                                    Log.e("API Call", "Error parsing JSON", e)
                                                }
                                            }
                                        }
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Log.d("API Call", "Failure: ${t.message}")
                                    }
                                })
                            }
                        }

                        composable(AppScreens.loginUser.route) {
                            val loginViewModel: LoginViewModel = viewModel()
                            LogIn(navController = navController, context = this@MainActivity, snackbarHostState = snackbarHostState) { logInData ->
                                Log.d("MainActivity", "Attempting login")
                                loginViewModel.login(this@MainActivity, logInData) {
                                    Log.d("MainActivity", "Login successful, navigating to home")
                                    navController.navigate(AppScreens.homePage.route) {
                                        popUpTo(AppScreens.homePage.route) { inclusive = true }
                                    }
                                }
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
                                val call = RetrofitInstance.apiService.forgotPassword(forgotPasswordData)
                                call.enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if (response.isSuccessful) {
                                            Log.d("API Call", "Correo enviado")
                                            navController.navigate("resetPassword")
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Log.d("API Call", "Response not successful: $errorBody")
                                            if (!errorBody.isNullOrEmpty()) {
                                                try {
                                                    val json = JSONObject(errorBody)
                                                    errorMessageForgotPassword = json.getString("errorMessage")
                                                    showErrorForgotPassword = true
                                                } catch (e: JSONException) {
                                                    Log.e("API Call", "Error parsing JSON", e)
                                                }
                                            }
                                        }
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Log.d("API Call", "Failure: ${t.message}")
                                    }
                                })
                            }
                        }

                        composable(AppScreens.resetPassword.route) {
                            ResetPassword(navController = navController) { resetPasswordData ->
                                val call = RetrofitInstance.apiService.resetPassword(resetPasswordData)
                                call.enqueue(object : Callback<Void> {
                                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                        if (response.isSuccessful) {
                                            Log.d("API Call", "Contraseña restablecida con éxito")
                                            navController.navigate("loginUser_screens")
                                        } else {
                                            val errorBody = response.errorBody()?.string()
                                            Log.d("API Call", "Response not successful: $errorBody")
                                            if (!errorBody.isNullOrEmpty()) {
                                                try {
                                                    val json = JSONObject(errorBody)
                                                    errorMessageResetPassword = json.getString("errorMessage")
                                                    showErrorResetPassword = true
                                                } catch (e: JSONException) {
                                                    Log.e("API Call", "Error parsing JSON", e)
                                                }
                                            }
                                        }
                                    }

                                    override fun onFailure(call: Call<Void>, t: Throwable) {
                                        Log.d("API Call", "Failure: ${t.message}")
                                    }
                                })
                            }
                        }

                        composable(AppScreens.productRegister.route) {
                            ProductRegister(navController = navController) {

                            }

                        }

                        composable(AppScreens.homePage.route) {
                            HomePage(navController = navController)

                        }

                        composable(AppScreens.profile.route) {
                            Perfil(navController = navController, context = this@MainActivity)

                        }

                        composable(AppScreens.catalogo.route) {
                            CatalogoPrincipal(navController = navController)

                        }

                        composable(AppScreens.detalleProd.route + "/{productId}") { backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")?.toLong() ?: 0L
                            VerDetalle(navController = navController, productId = productId)
                        }

                        composable(AppScreens.menuDetalleProd.route){ backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")?.toLong() ?: 0L
                            MostrarMenuDetalleProd(navController = navController, productId = productId)
                        }

                        composable(AppScreens.editProfile.route) {
                            EditarPerfil(navController = navController, context = this@MainActivity)
                        }

                        composable(AppScreens.favorite.route){
                            Favoritos(navController = navController)
                        }

                        composable(AppScreens.ChatView.route){
                            ShowChats(navController = navController)
                        }
                        composable(
                            route = AppScreens.ChatMessages.route,
                            arguments = listOf(navArgument("chatId") { type = NavType.LongType })
                        ) { backStackEntry ->
                            val chatId = backStackEntry.arguments?.getLong("chatId") ?: 0L
                            ChatMessage(navController = navController, chatId = chatId)
                        }
                        composable(AppScreens.myProductsView.route){
                            MisProductos(navController = navController)
                        }

                        composable(AppScreens.myProductDetailView.route){ backStackEntry ->
                            val productId = backStackEntry.arguments?.getString("productId")?.toLong() ?: 0L
                            MisProdDetalles(navController = navController, productId = productId)
                        }
                    }
                }
            }
        }
    }
}
