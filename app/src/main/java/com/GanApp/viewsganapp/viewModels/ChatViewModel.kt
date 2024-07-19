package com.GanApp.viewsganapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.GanApp.viewsganapp.models.ChatEntity
import com.GanApp.viewsganapp.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val _chats = MutableStateFlow<List<ChatEntity>>(emptyList())
    val chats: StateFlow<List<ChatEntity>> get() = _chats

    val _snackbarMessage = MutableStateFlow<String?>(null)
    val snackbarMessage: StateFlow<String?> get() = _snackbarMessage

    fun getChatsByUserId (userId: Long){
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiServiceChats.getChatsByUserId(userId)
                if (response.isSuccessful){
                    _chats.value = response.body() ?: emptyList()
                }else{
                    _snackbarMessage.value = "Error al obtener los chats: ${response.errorBody()?.string()}"
                }
            }catch (e: Exception){
                Log.d("ChatViewModel", "ExcepciónView al obtener los chats: ${e.message}", e)
                _snackbarMessage.value = "Excepción al obtener los chats: ${e.message}"
            }
        }
    }
}