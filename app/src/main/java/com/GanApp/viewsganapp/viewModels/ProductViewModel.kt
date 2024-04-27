package com.GanApp.viewsganapp.viewModels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch

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

    fun fetchProducts() {
        TODO("Not yet implemented")
    }


}