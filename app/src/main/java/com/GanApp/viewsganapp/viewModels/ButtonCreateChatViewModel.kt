package com.GanApp.viewsganapp.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.apiService.ChatApiService
import com.GanApp.viewsganapp.models.ChatDto
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.launch
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ButtonCreateChatViewModel : ViewModel() {

    private val chatApiService : ChatApiService = RetrofitInstance.apiServiceChats

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun createChat(productId: Long, userId: Long, receiverId: Long) {
        viewModelScope.launch {
            try {
                val chatDto = ChatDto(productId, userId, receiverId)
                Log.d("ChatViewModel", "Enviando ChatDto: $chatDto")

                val response = chatApiService.createChat(chatDto)
                if (response.isSuccessful) {
                    Log.d("ChatViewModel", "Chat creado exitosamente: ${response.body()}")
                    _snackbarMessage.value = "Chat creado exitosamente"
                } else {
                    if (response.code() == 409) {
                        _snackbarMessage.value = "El chat ya existe para este producto y usuario"
                    } else {
                        _snackbarMessage.value = "Error al crear el chat: ${response.errorBody()?.string()}"
                    }
                    Log.d("ChatViewModel", "Error al crear el chat: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ChatViewModel", "Excepción al crear el chat: ${e.message}", e)
                _snackbarMessage.value = "Excepción al crear el chat: ${e.message}"
            }
        }
    }
}