package com.GanApp.viewsganapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.apiService.ChatApiService
import com.GanApp.viewsganapp.models.ChatDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import android.util.Log
import com.GanApp.viewsganapp.models.ChatEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ButtonCreateChatViewModel : ViewModel() {

    private val chatApiService : ChatApiService = RetrofitInstance.apiServiceChats

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    private val _chatId = MutableStateFlow<Long?>(null)
    val chatId: StateFlow<Long?> get() = _chatId

    fun createOrFetchChat(productId: Long, userId: Long, receiverId: Long) {
        viewModelScope.launch {
            try {
                // Primero, verifica si el chat ya existe
                val existingChatResponse = chatApiService.verifyChatExists(productId, userId, receiverId)
                if (existingChatResponse.isSuccessful) {
                    val existingChat = existingChatResponse.body()
                    if (existingChat != null) {
                        Log.d("ButtonCreateChatViewModel", "El chat ya existe: ${existingChat.chatId}")
                        _chatId.value = existingChat.chatId
                        _snackbarMessage.value = "El chat ya existe para este producto y usuario"

                    }else {
                        _snackbarMessage.value = "Error: La respuesta del servidor fue exitosa pero no se encontró el chat"
                    }

                } else if (existingChatResponse.code() == 404) {

                    // Si no existe, crea un nuevo chat
                    val chatDto = ChatDto(productId, userId, receiverId)
                    Log.d("ButtonCreateChatViewModel", "Enviando ChatDto: $chatDto")

                    val response = chatApiService.createChat(chatDto)
                    if (response.isSuccessful) {
                        val createdChat = response.body()
                        Log.d("ButtonCreateChatViewModel", "Chat creado exitosamente: ${createdChat?.chatId}")
                        _chatId.value = createdChat?.chatId
                        _snackbarMessage.value = "Chat creado exitosamente"
                    } else {
                        _snackbarMessage.value =
                            "Error al crear el chat: ${response.errorBody()?.string()}"
                        Log.d(
                            "ButtonCreateChatViewModel",
                            "Error al crear el chat: ${response.errorBody()?.string()}"
                        )
                    }
                }
            } catch (e: Exception) {
                Log.e("ButtonCreateChatViewModel", "Excepción al crear el chat: ${e.message}", e)
                _snackbarMessage.value = "Excepción al crear el chat: ${e.message}"
            }
        }
    }
    fun reserDate(){
        _chatId.value = null
        _snackbarMessage.value = null
    }
}