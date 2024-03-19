package com.GanApp.viewsganapp.apiService


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApiService {
    @POST("users")
    fun createUser(@Body userData: android.service.autofill.UserData): Call<Void>
}