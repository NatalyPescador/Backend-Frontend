package com.GanApp.viewsganapp.viewModels

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.ProductDataDto
import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.google.gson.Gson
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.IOException
import com.GanApp.viewsganapp.utils.FileUtils
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException


class ProductViewModel : ViewModel() {

    var categorias = mutableStateOf<List<CategoriaEntity>>(listOf())
    var tiposServicio = mutableStateOf<List<TipoServicioEntity>>(listOf())
    var selectedTipoServicioId = mutableStateOf<Long?>(null)
    var loading = mutableStateOf(false)
    private val categoryApiService = RetrofitInstance.apiServiceCategory
    private val typeServiceApiService = RetrofitInstance.apiServiceTypeService
    private val _products = mutableStateListOf<ProductoEntity>()
    val products: List<ProductoEntity> get() = _products
    private val _product = mutableStateOf<ProductoEntity?>(null)
    var selectedProduct = mutableStateOf<ProductoEntity?>(null)

    init {
        fetchTiposServicio()
        getProducts()
    }

    init {
        selectedTipoServicioId.value?.let {
            fetchCategoriasByTipoServicio(it)
        }
    }

    fun fetchCategoriasByTipoServicio(tipoServicioId: Long) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = categoryApiService.getCategoriasByTypeService(tipoServicioId)
                if (response.isSuccessful) {
                    categorias.value = response.body() ?: emptyList()
                } else {
                    Log.d("ProductViewModel-fetchCategoriasByTipoServicio", "Error in response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel-fetchCategoriasByTipoServicio", "Exception caught: ${e.localizedMessage}")
            } finally {
                loading.value = false
            }
        }
    }

    private fun fetchTiposServicio() {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = typeServiceApiService.listarTiposServicios()
                if (response.isSuccessful) {
                    tiposServicio.value = response.body() ?: emptyList()
                } else {
                    Log.d("ProductViewModel-fetchTiposServicio", "Error in response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel-fetchTiposServicio", "Exception caught: ${e.localizedMessage}")
            } finally {
                loading.value = false
            }
        }
    }

    private fun fetchCategorias() {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = categoryApiService.listarCategorias()
                if (response.isSuccessful) {
                    categorias.value = response.body() ?: emptyList()
                } else {
                    Log.d("ProductViewModel-fetchCategorias", "Error in response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel-fetchCategorias", "Exception caught: ${e.localizedMessage}")
            } finally {
                loading.value = false
            }
        }
    }

    fun getProducts() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.apiServiceProduct.getProductList()
            } catch (e: IOException) {
                Log.d("ProductViewModel-getProducts", "Network error: ${e.localizedMessage}")
                return@launch
            } catch (e: HttpException) {
                Log.d("ProductViewModel-getProducts", "API error: ${e.localizedMessage}")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _products.clear()
                _products.addAll(response.body()!!)
            }
        }
    }

    fun getProductById(productId: Long) {
        viewModelScope.launch {
            loading.value = true
            try {
                val response = RetrofitInstance.apiServiceProduct.getProductById(productId)
                if (response.isSuccessful) {
                    selectedProduct.value = response.body()
                } else {
                    Log.d("ProductViewModel-getProductById", "Error in response: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.d("ProductViewModel-getProductById", "Exception caught: ${e.localizedMessage}")
            } finally {
                loading.value = false
            }
        }
    }

    fun clearSelectedProduct() {
        _product.value = null
    }

    fun uploadProductData(context: Context, imageUri: Uri, productData: ProductDataDto) {
        try {
            val gson = Gson()
            val productJson = gson.toJson(productData)
            val productRequestBody = productJson.toRequestBody("application/json".toMediaTypeOrNull())

            val file = File(FileUtils.getPath(context, imageUri))
            val requestFile = file.asRequestBody("image/jpeg".toMediaTypeOrNull())
            val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

            val service = RetrofitInstance.apiServiceProduct
            val call = service.createProduct(body, productRequestBody)
            call.enqueue(object : Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Log.d("ProductViewModel-uploadProductData", "Product registered successful")
                    } else {
                        Log.d("ProductViewModel-uploadProductData", "Error registering product: ${response.errorBody()?.string()}")
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("ProductViewModel-uploadProductData", "Network or conversion error: ${t.localizedMessage}")
                }
            })
        }catch (e: Exception){
            Log.d("ProductViewModel-uploadProductData", "Exception caught: ${e.localizedMessage}")
        }
    }
}
