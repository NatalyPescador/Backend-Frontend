package com.GanApp.viewsganapp.apiService

import com.GanApp.viewsganapp.models.ChatDto
import com.GanApp.viewsganapp.models.ChatEntity
import com.GanApp.viewsganapp.models.MessageDto
import com.GanApp.viewsganapp.models.MessageEntity
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ChatApiService {

    @POST("create")
    suspend fun createChat (@Body chatDto : ChatDto): Response<ChatDto>

    @POST("send")
    suspend fun createMessage (@Body messageDto : MessageDto): Response<MessageDto>

    @GET("messages/{chatId}")
    suspend fun getMessageByChatId(@Path("chatId") chatId : Long): Response<List<MessageEntity>>

    @GET("user/{userId}/chats")
    suspend fun getChatsByUserId(@Path("userId") userId : Long): Response<List<ChatEntity>>
}