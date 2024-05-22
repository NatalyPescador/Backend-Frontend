package com.GanApp.viewsganapp.apiService;

import com.GanApp.viewsganapp.views.ReviewData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ReviewApiService {

    @POST("reseñas")
    fun publishReview(@Body reviewData: ReviewData): Call<Void>
}
