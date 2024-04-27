import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.GanApp.viewsganapp.models.CategoriaEntity
import com.GanApp.viewsganapp.models.TipoServicioEntity

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()

    val tiposServicio = repository.tiposServicio
    val categorias = repository.categorias

    // Método para obtener productos desde el backend
    fun fetchProductos() {
        viewModelScope.launch {
            repository.fetchProductos()
        }
    }
}
class ProductRepository {
    // Simulación de tipos de servicio y categorías
    val tiposServicio = mutableStateOf<List<TipoServicioEntity>>(emptyList())
    val categorias = mutableStateOf<List<CategoriaEntity>>(emptyList())

    // Método para obtener productos desde el backend (simulado)
    suspend fun fetchProductos() {
        // Aquí podrías realizar la lógica para obtener productos desde el backend.
        // Por simplicidad, lo simularemos asignando algunos valores de ejemplo a tiposServicio y categorias.
        tiposServicio.value = listOf(
            TipoServicioEntity(1, "Tipo de Servicio 1"),
            TipoServicioEntity(2, "Tipo de Servicio 2"),
            TipoServicioEntity(3, "Tipo de Servicio 3")
        )
        categorias.value = listOf(
            CategoriaEntity(1, "Categoria 1"),
            CategoriaEntity(2, "Categoria 2"),
            CategoriaEntity(3, "Categoria 3")
        )
    }
}