package com.GanApp.viewsganapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.MessageDto
import com.GanApp.viewsganapp.models.MessageEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.log

class ChatMessagesViewModel : ViewModel(){

    private val chatApiService = RetrofitInstance.apiServiceChats

    private val _messages = MutableStateFlow<List<MessageEntity>>(emptyList())
    val messages: StateFlow<List<MessageEntity>> get() = _messages

    fun getMessagesByChatId(chatId: Long) {
        viewModelScope.launch {
            try {
                Log.e("ChatMessagesViewModel", "El chatId que se intenta consultar es: $chatId")
                val response = chatApiService.getMessageByChatId(chatId)
                Log.e("ChatMessagesViewModel", "el response es: $response")
                if (response.isSuccessful) {
                    _messages.value = response.body() ?: emptyList()
                } else {
                    Log.e("ChatMessagesViewModel", "Error al obtener los mensajes: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("ChatMessagesViewModel", "Excepción al obtener los mensajes: ${e.message}", e)
            }
        }
    }

    fun sendMessage(messageDto: MessageDto) {
        viewModelScope.launch {
            try {
                Log.d("ChatMessageViewModel", "messageDto recibido: $messageDto")
                val response = chatApiService.createMessage(messageDto)
                Log.d("ChatMessageViewModel", "mensaje creado: ${response.body()}")
                if (response.isSuccessful) {
                    getMessagesByChatId(messageDto.chatId)
                }
            } catch (e: Exception) {
                Log.e("ChatMessageViewModel", "Excepción al crear el mensaje: ${e.message}", e)
            }
        }
    }
}