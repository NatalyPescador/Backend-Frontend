package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TypeServiceApiService {

    @GET("TipoServicio")
    suspend fun listarTiposServicios(): Response<List<TipoServicioEntity>>

    @POST("TipoServicio")
    suspend fun saveCategoria(@Body tipoServicioEntity: TipoServicioEntity): Response<TipoServicioEntity>

}