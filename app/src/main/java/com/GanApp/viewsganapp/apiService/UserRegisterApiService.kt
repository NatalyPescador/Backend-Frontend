package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.UserDto
import com.GanApp.viewsganapp.views.ForgotPasswordData
import com.GanApp.viewsganapp.views.LogInData
import com.GanApp.viewsganapp.views.ResetPasswordData
import com.GanApp.viewsganapp.views.UserData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

interface UserRegisterApiService {
    @POST("user/register")
    fun createUser(@Body userData: UserData): Call<Void>

    @POST("user/login")
    fun logIn(@Body logInData: LogInData): Call<Void>

    @POST("user/forgot-password")
    fun forgotPassword(@Body forgotPasswordData: ForgotPasswordData): Call<Void>

    @POST("user/reset-password")
    fun resetPassword(@Body resetPasswordData: ResetPasswordData): Call<Void>

    @GET("user/findById")
    fun getUser(@Query("id") userId: Long): Call<UserDto>

    @Multipart
    @POST("user/create-with-image")
    fun createUserWithImage(
        @Part image: MultipartBody.Part,
        @Part("user") user: RequestBody
    ): Call<String>

    @POST("user/create")
    fun createUser(@Body user: RequestBody): Call<String>

    @PUT("user/{id}")
    fun updateUser(@Path("id") userId: Long, @Body user: RequestBody): Call<Void>

    @POST("user/upgradeUser")
    fun upgradeUser(@Body user:UserDto) : Call<Void>



}
