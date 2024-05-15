package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.ProductoEntity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ProductRegisterApiService {

    @Multipart
    @POST("registrar-producto")
    fun createProduct(
        @Part file: MultipartBody.Part,
        @Part("product") productData: RequestBody
    ): Call<String>
    //fun createProduct(@Body productData: ProductData): Call<Void>

    @GET("producto")
    suspend fun getProductList(): Response<List<ProductoEntity>> // Cambio en el tipo de retorno
}