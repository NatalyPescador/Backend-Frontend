package com.GanApp.viewsganapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.GanApp.viewsganapp.ui.theme.ViewsGanAppTheme
import com.GanApp.viewsganapp.views.UserInputForm

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewsGanAppTheme { // Asume que este es tu tema de Compose
                UserInputForm { userData ->
                    // Aquí puedes manejar los datos del formulario, como enviarlos a tu backend
                    // Por ahora, solo imprimirá los datos en la consola Logcat
                    println("Nombre: ${userData.name}, Email: ${userData.email}, Contraseña: ${userData.password}, Teléfono: ${userData.phoneNumber}")
                }
            }
        }
    }
}

