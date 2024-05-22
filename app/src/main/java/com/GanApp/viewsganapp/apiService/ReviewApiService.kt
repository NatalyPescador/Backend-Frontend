package com.GanApp.viewsganapp.apiService;

import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.views.ReviewData;

import retrofit2.Call;
import retrofit2.Response
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.POST;

public interface ReviewApiService {

    @POST("reseñas")
    fun publishReview(@Body reviewData: ReviewData): Call<Void>

    @GET("reseñas")
    suspend fun getReviews(): Response<List<ReviewEntity>>
}
