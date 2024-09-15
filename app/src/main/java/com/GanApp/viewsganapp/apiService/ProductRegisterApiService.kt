package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.models.UpdateProductDto
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface ProductRegisterApiService {

    @Multipart
    @POST("registrar-producto")
    fun createProduct(
        @Part file: MultipartBody.Part,
        @Part("product") productData: RequestBody
    ): Call<String>
    //fun createProduct(@Body productData: ProductData): Call<Void>

    @GET("producto")
    suspend fun getProductList(): Response<List<ProductoEntity>>

    @GET("producto/{id}")
    suspend fun getProductById(@Path("id") id: Long): Response<ProductoEntity>

    @GET("productos/{userId}")
    suspend fun getProductsByUserId(@Path("userId") userId: Long): Response<List<ProductoEntity>>

    @GET("productos/tipoServicio/{tipoServicioId}")
    suspend fun getProductsByTipoServicio(@Path("tipoServicioId") tipoServicioId: Long): Response<List<ProductoEntity>>

    @PUT("producto/actualizar/{id}")
    suspend fun updateProduct(
        @Path("id") id: Long,
        @Body updatedProduct: UpdateProductDto
    ): Response<ResponseBody>

    @DELETE("producto/borrar/{id}")
    suspend fun deleteProduct(@Path("id") id: Long): Response<Void>
}