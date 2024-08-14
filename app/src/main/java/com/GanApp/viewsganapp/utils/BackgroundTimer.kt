package com.GanApp.viewsganapp.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class BackgroundTimer(
    private val context: Context,
    private val onTimeout: () -> Unit
) : DefaultLifecycleObserver {

    private val handler = Handler(Looper.getMainLooper())
    private val timeoutDuration = 300000L // 5 minutos en milisegundos
    private var startTime: Long = 0

    private val timeoutRunnable = Runnable {
        Log.d("BackgroundTimer", "Temporizador de inactividad agotado, cerrando sesión")
        TokenManager.resetToken(context)
        onTimeout()
    }

    override fun onStart(owner: LifecycleOwner) {
        stopTimer()
        val elapsedTime = System.currentTimeMillis() - startTime
        Log.d("BackgroundTimer", "Aplicación en primer plano. Tiempo transcurrido en segundo plano: ${elapsedTime / 1000} segundos")
    }

    override fun onStop(owner: LifecycleOwner) {
        startTimer()
    }

    private fun startTimer() {
        Log.d("BackgroundTimer", "Iniciando temporizador de inactividad")
        startTime = System.currentTimeMillis()
        handler.postDelayed(timeoutRunnable, timeoutDuration)
    }

    private fun stopTimer() {
        Log.d("BackgroundTimer", "Deteniendo temporizador de inactividad")
        handler.removeCallbacks(timeoutRunnable)
    }
}
