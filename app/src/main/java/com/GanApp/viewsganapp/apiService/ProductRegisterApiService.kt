package com.GanApp.viewsganapp.apiService

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ProductRegisterApiService {

    @POST("registrar-producto")
    fun createProduct(@Body productData: ProductData): Call<Void>

}




