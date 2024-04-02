package com.GanApp.viewsganapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.views.LogIn
import com.GanApp.viewsganapp.views.Register
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val showLogin = remember { mutableStateOf(false) } // Estado para controlar la visualización de la pantalla de login

            ViewsGanAppTheme { // Asume que este es tu tema de Compose
                if (!showLogin.value) {
                    // Muestra la vista de registro y provee la función para cambiar a login
                    Register(onSubmit = { userData ->
                        val call = RetrofitInstance.apiService.createUser(userData)
                        call.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    Log.d("API Call", "Success")
                                } else {
                                    Log.d("API Call", "Response not successful: ${response.errorBody()?.string()}")
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d("API Call", "Failure: ${t.message}")
                            }
                        })
                    }, onSwitchToLogin = {
                        showLogin.value = true
                    })
                } else {
                    // Muestra la vista de inicio de sesión
                    LogIn(onSubmit = { logInData ->
                        val call = RetrofitInstance.apiService.logIn(logInData)
                        call.enqueue(object : Callback<Void> {
                            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                                if (response.isSuccessful) {
                                    Log.d("API Call", "Success")
                                } else {
                                    Log.d("API Call", "Response not successful: ${response.errorBody()?.string()}")
                                }
                            }

                            override fun onFailure(call: Call<Void>, t: Throwable) {
                                Log.d("API Call", "Failure: ${t.message}")
                            }
                        })
                    }, onSwitchToRegister = {
                        // Cambiar a la vista de registro
                        showLogin.value = false
                    })
                }
            }
        }
    }
}
