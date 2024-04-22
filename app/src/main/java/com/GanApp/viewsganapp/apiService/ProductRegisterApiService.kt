package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.views.ProductData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Part

interface ProductRegisterApiService {

    @POST("registrar-producto")
    fun createProduct(
        @Part file: MultipartBody.Part,
        @Part("product") productData: RequestBody
    ): Call<String>
    //fun createProduct(@Body productData: ProductData): Call<Void>
}