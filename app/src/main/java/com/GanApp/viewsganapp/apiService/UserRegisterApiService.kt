package com.GanApp.viewsganapp.apiService


import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApiService {
    @POST("registro")
    fun createUser(@Body userData: UserData): Call<Void>

    @POST("inicio-sesion")
    fun logIn(@Body logInData: LogInData): Call<Void>

    @POST("olvidar-contraseña")
    fun forgotPassword(@Body forgotPasswordData: ForgotPasswordData): Call<Void>

    @POST("restablecer-contraseña")
    fun resetPassword(@Body resetPasswordData: ResetPasswordData): Call<Void>


}