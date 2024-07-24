package com.GanApp.viewsganapp.viewModels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import com.GanApp.viewsganapp.views.ReviewData
import com.GanApp.viewsganapp.views.errorMessageReview
import com.GanApp.viewsganapp.views.showErrorReview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewViewModel: ViewModel() {

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> get() = _loading
    var selectedReviews = mutableStateOf<List<ReviewEntity>>(emptyList())

    fun getReviewByProductId(productId: Long) {
        viewModelScope.launch {
            _loading.value = true
            try {
                val response = RetrofitInstance.apiServiceReviewApiService.getReviewByProductId(productId)
                if (response.isSuccessful) {
                    selectedReviews.value = response.body() ?: emptyList()
                } else {
                    println("Error en la respuesta: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                println("Excepción capturada: ${e.localizedMessage}")
            } finally {
                _loading.value = false
            }
        }
    }

    fun publishReview(reviewData: ReviewData) {
        _loading.value = true
        val call = RetrofitInstance.apiServiceReviewApiService.publishReview(reviewData)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d("API Call", "Reseña publicada con éxito")
                } else {
                    val errorBody = response.errorBody()?.string()
                    Log.d("API Call", "Response not successful: $errorBody")
                    if (!errorBody.isNullOrEmpty()) {
                        try {
                            val json = JSONObject(errorBody)
                            errorMessageReview = json.getString("errorMessage")
                            showErrorReview = true
                        } catch (e: JSONException) {
                            Log.e("API Call", "Error parsing JSON", e)
                        }
                    }
                }
                _loading.value = false
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("API Call", "Failure: ${t.message}")
                _loading.value = false
            }
        })
    }
}