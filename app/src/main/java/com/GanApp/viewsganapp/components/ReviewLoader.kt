package com.GanApp.viewsganapp.components

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun loadReviews(coroutineScope: CoroutineScope, reviewsState: MutableState<List<ReviewEntity>>) {
    coroutineScope.launch {
        val call = RetrofitInstance.apiServiceReviewApiService.getReviews()
        call.enqueue(object : Callback<List<ReviewEntity>> {
            override fun onResponse(call: Call<List<ReviewEntity>>, response: Response<List<ReviewEntity>>) {
                if (response.isSuccessful) {
                    reviewsState.value = response.body().orEmpty()
                    Log.d("API Call", "Fetched reviews: ${reviewsState.value}")
                } else {
                    Log.d("API Call", "Failed to fetch reviews: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<List<ReviewEntity>>, t: Throwable) {
                Log.d("API Call", "Failure: ${t.message}")
            }
        })
    }
}