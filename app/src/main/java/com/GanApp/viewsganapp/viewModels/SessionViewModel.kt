package com.GanApp.viewsganapp.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.LogInData
import com.GanApp.viewsganapp.models.LoginDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.utils.decodeJWT
import com.GanApp.viewsganapp.utils.saveLoginData
import com.GanApp.viewsganapp.utils.saveUserData
import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.UserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SessionViewModel : ViewModel() {

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage

    private val _registrationSuccess = MutableStateFlow<Boolean?>(null)
    val registrationSuccess: StateFlow<Boolean?> = _registrationSuccess

    private val _resetSuccess = MutableStateFlow<Boolean?>(null)
    val resetSuccess: StateFlow<Boolean?> = _resetSuccess

    private val _updateSuccess = MutableStateFlow<Boolean?>(null)
    val updateSuccess: StateFlow<Boolean?> = _updateSuccess

    fun login(context: Context, loginData: LogInData, onLoginSuccess: () -> Unit) {
        viewModelScope.launch {
            val call = RetrofitInstance.apiService.logIn(loginData)
            call.enqueue(object : Callback<LoginDto> {
                override fun onResponse(call: Call<LoginDto>, response: Response<LoginDto>) {
                    if (response.isSuccessful) {
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            saveLoginData(context, loginResponse)
                            Log.d("LoginUser", "Inicio de sesión exitoso. Token: ${loginResponse.token}, Expiración: ${loginResponse.expirationTime}")

                            // Decodificar el token JWT
                            val userData = decodeJWT(loginResponse.token)
                            Log.d("LoginUser", "Datos del usuario: $userData")

                            // Guardar datos del usuario en SharedPreferences si es necesario
                            saveUserData(context, userData)

                            onLoginSuccess()
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("LoginViewModel", "Error en la respuesta del servidor: $errorBody")
                        if (!errorBody.isNullOrEmpty()) {
                            try {
                                val json = JSONObject(errorBody)
                                val errorMessage = json.getString("errorMessage")
                                Log.e("LoginViewModel", "Mensaje de error transformado: $errorMessage")
                                _snackbarMessage.value = errorMessage
                            } catch (e: Exception) {
                                Log.e("LoginViewModel", "Error al parsear el mensaje de error", e)
                                _snackbarMessage.value = "Error en el inicio de sesión"
                            }
                        } else {
                            _snackbarMessage.value = "Error en el inicio de sesión"
                        }
                    }
                }

                override fun onFailure(call: Call<LoginDto>, t: Throwable) {
                    Log.e("LoginViewModel", "Error de conexión: ${t.message}")
                    _snackbarMessage.value = "Error de conexión: ${t.message}"
                }
            })
        }
    }

    fun registerUser(userData: UserData) {
        viewModelScope.launch {
            val call = RetrofitInstance.apiService.createUser(userData)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        _snackbarMessage.value = "Usuario creado con éxito"
                        _registrationSuccess.value = true
                    } else {
                        val errorBody = response.errorBody()?.string()
                        if (!errorBody.isNullOrEmpty()) {
                            try {
                                val json = JSONObject(errorBody)
                                val errorMessage = json.getString("errorMessage")
                                _snackbarMessage.value = errorMessage
                            } catch (e: Exception) {
                                _snackbarMessage.value = "Error al procesar la respuesta"
                            }
                        }
                        _registrationSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    _snackbarMessage.value = "Error al crear el usuario: ${t.message}"
                    _registrationSuccess.value = false
                }
            })
        }
    }

    fun forgotPassword(forgotPasswordData: ForgotPasswordData) {
        viewModelScope.launch {
            val call = RetrofitInstance.apiService.forgotPassword(forgotPasswordData)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        _snackbarMessage.value = "Correo enviado con éxito"
                        _resetSuccess.value = true
                    } else {
                        val errorBody = response.errorBody()?.string()
                        if (!errorBody.isNullOrEmpty()) {
                            try {
                                val json = JSONObject(errorBody)
                                val errorMessage = json.getString("errorMessage")
                                _snackbarMessage.value = errorMessage

                            } catch (e: Exception) {
                                _snackbarMessage.value = "Error al procesar la respuesta"
                            }
                        }
                        _resetSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    _snackbarMessage.value = "Error al enviar el correo: ${t.message}"
                    _resetSuccess.value = false
                }
            })
        }

    }

    fun resetPassword(resetPasswordData: ResetPasswordData) {
        viewModelScope.launch {
            val call = RetrofitInstance.apiService.resetPassword(resetPasswordData)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        _snackbarMessage.value = "Contraseña actualizada con éxito"
                        _updateSuccess.value = true
                    } else {
                        val errorBody = response.errorBody()?.string()
                        if (!errorBody.isNullOrEmpty()) {
                            try {
                                val json = JSONObject(errorBody)
                                val errorMessage = json.getString("errorMessage")
                                _snackbarMessage.value = errorMessage

                            } catch (e: Exception) {
                                _snackbarMessage.value = "Error al procesar la respuesta"
                            }
                        }
                        _updateSuccess.value = false
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    _snackbarMessage.value = "Error al restablecer la contraseña: ${t.message}"
                    _updateSuccess.value = false
                }
            })
        }

    }


    fun clearSnackbarMessage() {
        _snackbarMessage.value = null // Limpiar el mensaje después de mostrarlo
    }
}
