package com.GanApp.viewsganapp.network

import com.GanApp.viewsganapp.apiService.UserRegisterApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.224.190:8080/GanApp/" // Reemplaza esto con tu URL base

    // Lazy initialization del Retrofit instance
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: UserRegisterApiService by lazy {
        retrofit.create(UserRegisterApiService::class.java)
    }

}