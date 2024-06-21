package com.GanApp.viewsganapp.apiService;

import com.GanApp.viewsganapp.models.ProductoEntity
import com.GanApp.viewsganapp.models.ReviewEntity
import com.GanApp.viewsganapp.views.ReviewData;

import retrofit2.Call;
import retrofit2.Response
import retrofit2.http.Body;
import retrofit2.http.GET
import retrofit2.http.POST;
import retrofit2.http.Path

public interface ReviewApiService {

    @POST("reseñas")
    fun publishReview(@Body reviewData: ReviewData): Call<Void>

    @GET("reseñas")
    fun getReviews(): Call<List<ReviewEntity>>

    @GET("reseñas/{productId}")
    suspend fun getReviewByProductId(@Path("productId") productId: Long): Response<List<ReviewEntity>>
}
