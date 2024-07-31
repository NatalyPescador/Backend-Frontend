package com.GanApp.viewsganapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.UserDataRegisterDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

sealed class RegistrationState {
    object Idle : RegistrationState()
    object Loading : RegistrationState()
    object Success : RegistrationState()
    data class Failure(val errorMessage: String) : RegistrationState()
}

class RegisterViewModel : ViewModel() {
    private val _registrationState = MutableStateFlow<RegistrationState>(RegistrationState.Idle)
    val registrationState: StateFlow<RegistrationState> = _registrationState

    fun registerUser(userData: UserDataRegisterDto) {
        _registrationState.value = RegistrationState.Loading
        viewModelScope.launch {
            val call = RetrofitInstance.apiService.createUser(userData)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        _registrationState.value = RegistrationState.Success
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMessage = if (!errorBody.isNullOrEmpty()) {
                            try {
                                val json = JSONObject(errorBody)
                                json.getString("errorMessage")
                            } catch (e: Exception) {
                                "Error parsing response"
                            }
                        } else {
                            "Unknown error occurred"
                        }
                        _registrationState.value = RegistrationState.Failure(errorMessage)
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    _registrationState.value = RegistrationState.Failure(t.message ?: "Network error")
                }
            })
        }
    }
}