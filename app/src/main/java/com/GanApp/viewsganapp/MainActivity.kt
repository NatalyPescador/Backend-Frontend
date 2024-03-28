package com.GanApp.viewsganapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.views.ForgotPassword
import com.GanApp.viewsganapp.views.LogIn
import com.GanApp.viewsganapp.views.Register
import com.GanApp.viewsganapp.views.ResetPassword
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewsGanAppTheme { // Asume que este es tu tema de Compose

                Register { userData ->
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
                }

                LogIn { logInData ->
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
                }

                ForgotPassword { forgotPasswordData ->
                    val call = RetrofitInstance.apiService.forgotPassword(forgotPasswordData)
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
                }

                ResetPassword { resetPasswordData ->
                    val call = RetrofitInstance.apiService.resetPassword(resetPasswordData)
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
                }
            }
        }
    }
}

