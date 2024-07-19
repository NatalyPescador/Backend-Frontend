package com.GanApp.viewsganapp.network

import com.GanApp.viewsganapp.apiService.CategoryApiService
import com.GanApp.viewsganapp.apiService.ChatApiService
import com.GanApp.viewsganapp.apiService.ProductRegisterApiService
import com.GanApp.viewsganapp.apiService.ReviewApiService
import com.GanApp.viewsganapp.apiService.TypeServiceApiService
import com.GanApp.viewsganapp.apiService.UserRegisterApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime

object RetrofitInstance {

    const val BASE_URL = "http://192.168.1.79:8080/GanApp/" // Reemplaza esto con tu URL base

    private val gson: Gson = GsonBuilder()
        .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter())
        .create()

    // Lazy initialization del Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    val apiService: UserRegisterApiService by lazy {
        retrofit.create(UserRegisterApiService::class.java)
    }

    val apiServiceProduct: ProductRegisterApiService by lazy {
        retrofit.create(ProductRegisterApiService::class.java)
    }

    val apiServiceCategory: CategoryApiService by lazy {
        retrofit.create(CategoryApiService::class.java)
    }

    val apiServiceTypeService: TypeServiceApiService by lazy {
        retrofit.create(TypeServiceApiService::class.java)
    }

    val apiServiceReviewApiService: ReviewApiService by lazy {
        retrofit.create(ReviewApiService::class.java)
    }

    val apiServiceChats: ChatApiService by lazy {
        retrofit.create(ChatApiService::class.java)
        }

}