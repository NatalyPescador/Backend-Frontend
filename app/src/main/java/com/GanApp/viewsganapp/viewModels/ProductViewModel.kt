package com.GanApp.viewsganapp.viewModels

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.views.ProductData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import com.GanApp.viewsganapp.utils.FileUtils
import okhttp3.RequestBody.Companion.asRequestBody

class ProductViewModel : ViewModel() {

    var categorias = mutableStateOf<List<CategoriaEntity>>(listOf())
    var tiposServicio = mutableStateOf<List<TipoServicioEntity>>(listOf())
    var selectedTipoServicioId = mutableStateOf<Long?>(null)
    var loading = mutableStateOf(false)
    private val categoryApiService = RetrofitInstance.apiServiceCategory
    private val typeServiceApiService = RetrofitInstance.apiServiceTypeService

    init{
        fetchTiposServicio()
    }

    // Observar cambios en selectedTipoServicioId y cargar categorías correspondientes
    init {
        selectedTipoServicioId.value?.let {
            fetchCategoriasByTipoServicio(it)
        }
    }

    public fun fetchCategoriasByTipoServicio(tipoServicioId: Long) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = categoryApiService.getCategoriasByTypeService(tipoServicioId)
                if (response.isSuccessful) {
                    categorias.value = response.body() ?: emptyList()
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Excepción capturada: ${e.localizedMessage}")
            } finally {
                loading.value = false
            }
        }
    }

    private fun fetchTiposServicio(){
        viewModelScope.launch {
            loading.value = true
            try {
                val response = typeServiceApiService.listarTiposServicios()
                if (response.isSuccessful){
                    tiposServicio.value = response.body() ?: emptyList()
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }catch (e: Exception){
                println("Excepción capturada: ${e.localizedMessage}")
            }finally {
                loading.value = false
            }
        }
    }

    private fun fetchCategorias(){
        viewModelScope.launch {
            loading.value = true
            try {
                val response = categoryApiService.listarCategorias()
                if (response.isSuccessful){
                    categorias.value = response.body() ?: emptyList()
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            }catch (e: Exception){
                println("Excepción capturada: ${e.localizedMessage}")
            }finally {
                loading.value = false
            }
        }
    }

    fun uploadProductData(context: Context, imageUri: Uri, productData: ProductData) {
        val gson = Gson()
        val productJson = gson.toJson(productData)
        val productRequestBody = productJson.toRequestBody("application/json".toMediaTypeOrNull())

        val file = File(FileUtils.getPath(context, imageUri)) // Necesitas implementar FileUtils.getPath o similar
        val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val service = RetrofitInstance.apiServiceProduct // Asegúrate de que tu RetrofitInstance tenga el servicio configurado
        val call = service.createProduct(body, productRequestBody)
        call.enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.isSuccessful) {
                    // Trata aquí el éxito de la carga
                } else {
                    // Trata aquí los errores de la carga
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                // Maneja aquí los fallos de la red o conversión
            }
        })
    }



}