package com.GanApp.viewsganapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.GanApp.viewsganapp.navigation.AppScreens
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.views.Facebook
import com.GanApp.viewsganapp.views.Gmail
import com.GanApp.viewsganapp.views.ForgotPassword
import com.GanApp.viewsganapp.views.LogIn
import com.GanApp.viewsganapp.views.ProductRegister
import com.GanApp.viewsganapp.views.Register
import com.GanApp.viewsganapp.views.ResetPassword
import com.GanApp.viewsganapp.views.errorMessageForgotPassword
import com.GanApp.viewsganapp.views.errorMessageLogin
import com.GanApp.viewsganapp.views.errorMessageRegister
import com.GanApp.viewsganapp.views.errorMessageResetPassword
import com.GanApp.viewsganapp.views.showErrorForgotPassword
import com.GanApp.viewsganapp.views.showErrorLogin
import com.GanApp.viewsganapp.views.showErrorRegister
import com.GanApp.viewsganapp.views.showErrorResetPassword
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response





class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            ViewsGanAppTheme {  // Asume que este es tu tema de Compose
                NavHost(
                    navController = navController,
                    startDestination = AppScreens.loginUser.route
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
                        LogIn(navController = navController) { logInData ->
                            val call = RetrofitInstance.apiService.logIn(logInData)
                            call.enqueue(object : Callback<Void> {
                                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                    if (response.isSuccessful) {
                                        Log.d("API Call", "Inicio de sesión exitoso")
                                    } else {
                                        val errorBody = response.errorBody()?.string()
                                        Log.d("API Call", "Response not successful: $errorBody")
                                        if (!errorBody.isNullOrEmpty()) {
                                            try {
                                                val json = JSONObject(errorBody)
                                                errorMessageLogin = json.getString("errorMessage")
                                                showErrorLogin = true
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

                    composable(AppScreens.conecctionFacebook.route){
                        Facebook(navController = navController)
                    }

                    composable(AppScreens.conecctionGmail.route){
                        Gmail(navController = navController)
                    }

                    composable(AppScreens.forgotPassword.route){
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

                    composable(AppScreens.resetPassword.route){
                        ResetPassword (navController = navController){ resetPasswordData ->
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

                    composable(AppScreens.productRegister.route){
                        ProductRegister(navController = navController) {

                        }
                    }
                }
            }
        }
    }
}

