package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.views.UserData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRegisterApiService {
    @POST("registro")
    fun createUser(@Body userData: UserData): Call<Void>
}