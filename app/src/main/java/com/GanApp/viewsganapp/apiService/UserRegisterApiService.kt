package com.GanApp.viewsganapp.apiService


import com.GanApp.viewsganapp.views.LogInData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApiService {
    @POST("registro")
    fun createUser(@Body userData: Any): Call<Void>

    @POST("iniciosesion")
    fun logIn(@Body logInData: LogInData): Call<Void>
}