package com.GanApp.viewsganapp.viewModels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.UserDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.utils.getUserData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileViewModel : ViewModel() {

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> = _snackbarMessage

    private val _user = MutableStateFlow<UserDto?>(null)
    val user: StateFlow<UserDto?> get() = _user

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading

    fun fetchUserData(context: Context) {
        viewModelScope.launch {
            _loading.value = true
            val userData = getUserData(context)

            if (userData != null){
                val service = RetrofitInstance.apiService
                val call = service.getUser(userData.userId)

                call.enqueue(object : Callback<UserDto> {
                    override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                        if (response.isSuccessful) {
                            _user.value = response.body()
                        } else {
                            _snackbarMessage.value = "Error al obtener la información de perfil: ${response.errorBody()?.string()}"
                            Log.d("UserProfielViewModel-fetchUserData","fetchUserData Error en la respuesta: ${response.errorBody()?.string()}")
                        }
                        _loading.value = false
                    }

                    override fun onFailure(call: Call<UserDto>, t: Throwable) {
                        _snackbarMessage.value = "Error al obtener la información de perfil: ${t.message}"
                        Log.d("UserProfielViewModel-fetchUserData","fetchUserData Error de red o conversión: ${t.localizedMessage}")
                        _loading.value = false
                    }
                })
            } else {
                _loading.value = false
            }
        }
    }

    fun upgradeUser(context: Context, user: UserDto) {
        _loading.value = true
        val service = RetrofitInstance.apiService
        val call = service.upgradeUser(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    _user.value = user
                    _snackbarMessage.value = "Información actualizada exitosamente"
                } else {
                    Log.d("UserProfielViewModel-upgradeUser","upgrade Error en la respuesta: ${response.errorBody()?.string()}")
                    _snackbarMessage.value = "Error al actualizar la información: ${response.errorBody()?.string()}"
                }
                _loading.value = false
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                _snackbarMessage.value = "Error al restablecer la contraseña: ${t.message}"
                Log.d("UserProfielViewModel-upgradeUser","upgrade Error de red o conversión: ${t.localizedMessage}")
                _loading.value = false
            }
        })
    }

    fun clearSnackbarMessage() {
        _snackbarMessage.value = null // Limpiar el mensaje después de mostrarlo
    }
}