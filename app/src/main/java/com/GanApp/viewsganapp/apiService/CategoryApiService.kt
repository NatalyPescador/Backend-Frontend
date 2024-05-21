package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.CategoriaEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CategoryApiService {

    @GET("categoria")
    suspend fun listarCategorias(): Response<List<CategoriaEntity>>


    @GET("categoria/{typeServiceId}")
    suspend fun getCategoriasByTypeService(@Path("typeServiceId") typeServiceId: Long): Response<List<CategoriaEntity>>
    @POST("categoria")
    suspend fun saveCategoria(@Body categoriaEntity: CategoriaEntity): Response<CategoriaEntity>

}