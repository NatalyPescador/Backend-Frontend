package com.GanApp.viewsganapp.apiService


import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.UserData
import com.GanApp.viewsganapp.views.newPasswordData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApiService {
    @POST("registro")
    fun createUser(@Body userData: UserData): Call<Void>

    @POST("iniciosesion")
    fun logIn(@Body logInData: LogInData): Call<Void>

    @POST("recuperarcontraseña")
    fun newPassword(@Body newPasswordData: newPasswordData): Call<Void>
}