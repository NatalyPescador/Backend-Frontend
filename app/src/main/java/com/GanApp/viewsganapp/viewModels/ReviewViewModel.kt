package com.GanApp.viewsganapp.viewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class ReviewViewModel: ViewModel() {

    private val _reviews = mutableStateListOf<ReviewEntity>()
    val reviews: List<ReviewEntity> get() = _reviews

    init{
        getReviews()
    }

    fun getReviews() {
        viewModelScope.launch {
            val response = try {
                RetrofitInstance.apiServiceReviewApiService.getReviews()
            } catch (e: IOException) {
                // Handle network error
                println("Network error: ${e.localizedMessage}")
                return@launch
            } catch (e: HttpException) {
                // Handle API error
                println("API error: ${e.localizedMessage}")
                return@launch
            }

            if (response.isSuccessful && response.body() != null) {
                _reviews.clear()
                _reviews.addAll(response.body()!!)
            }
        }
    }
}