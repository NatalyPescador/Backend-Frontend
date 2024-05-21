package com.GanApp.viewsganapp.network

import com.GanApp.viewsganapp.apiService.CategoryApiService
import com.GanApp.viewsganapp.apiService.ProductRegisterApiService
import com.GanApp.viewsganapp.apiService.ReviewApiService
import com.GanApp.viewsganapp.apiService.TypeServiceApiService
import com.GanApp.viewsganapp.apiService.UserRegisterApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "http://192.168.0.104:8080/GanApp/" // Reemplaza esto con tu URL base

    // Lazy initialization del Retrofit instance
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
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

}