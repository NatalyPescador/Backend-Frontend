package com.GanApp.viewsganapp.viewmodels
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.apiService.UserRegisterApiService
import com.GanApp.viewsganapp.models.UserDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.google.gson.Gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow<UserDto?>(null)
    val user: StateFlow<UserDto?> get() = _user

    var loading = mutableStateOf(false)

    fun fetchUserData(userId: Long) {
        viewModelScope.launch {
            loading.value = true
            val service = RetrofitInstance.apiService

            val call = service.getUser(userId)
            call.enqueue(object : Callback<UserDto> {
                override fun onResponse(call: Call<UserDto>, response: Response<UserDto>) {
                    if (response.isSuccessful) {
                        _user.value = response.body()
                    } else {
                        println("fetchUserData Error en la respuesta: ${response.errorBody()?.string()}")
                    }
                    loading.value = false
                }

                override fun onFailure(call: Call<UserDto>, t: Throwable) {
                    println("fetchUserData Error de red o conversión: ${t.localizedMessage}")
                    loading.value = false
                }
            })
        }
    }

    fun uploadUserData(updatedUser: UserDto) {
        loading.value = true
        val gson = Gson()
        val userJson = gson.toJson(updatedUser)
        val userRequestBody = userJson.toRequestBody("application/json".toMediaTypeOrNull())

        val service = RetrofitInstance.apiService
        val call = service.updateUser(updatedUser.userId, userRequestBody)

        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    _user.value = updatedUser
                } else {
                    println("uploadUserData Error en la respuesta: ${response.errorBody()?.string()}")
                }
                loading.value = false
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("uploadUserData Error de red o conversión: ${t.localizedMessage}")
                loading.value = false
            }
        })
    }
    fun upgradeUser(user: UserDto) {
        loading.value = true
        val service = RetrofitInstance.apiService
        val call = service.upgradeUser(user)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    _user.value = user
                } else {
                    println("upgrade Error en la respuesta: ${response.errorBody()?.string()}")
                }
                loading.value = false
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("upgrade Error de red o conversión: ${t.localizedMessage}")
                loading.value = false
            }
        })
    }
}


