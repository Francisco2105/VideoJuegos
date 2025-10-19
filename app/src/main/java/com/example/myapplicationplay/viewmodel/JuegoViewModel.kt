package com.example.myapplicationplay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.repository.JuegoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class JuegoViewModel(private val repository: JuegoRepository) : ViewModel() {

    val todosLosJuegos: Flow<List<Juego>> = repository.getAllJuegos()
    val juegosEnCarrito: Flow<List<Juego>> = repository.getJuegosEnCarrito()

    fun agregarJuego(nombre: String, descripcion: String, precio: Double) {
        viewModelScope.launch {
            val nuevoJuego = Juego(
                nombre = nombre,
                descripcion = descripcion,
                precio = precio
            )
            repository.insertJuego(nuevoJuego)
        }
    }

    fun agregarAlCarrito(id: Long) {
        viewModelScope.launch {
            repository.agregarAlCarrito(id)
        }
    }

    fun removerDelCarrito(id: Long) {
        viewModelScope.launch {
            repository.removerDelCarrito(id)
        }
    }

    fun actualizarCantidad(id: Long, cantidad: Int) {
        viewModelScope.launch {
            repository.actualizarCantidad(id, cantidad)
        }
    }

    // Datos de ejemplo para inicializar
    fun inicializarDatosEjemplo() {
        viewModelScope.launch {
            val juegosEjemplo = listOf(
                Juego(nombre = "The Legend of Zelda", descripcion = "Aventura épica", precio = 59.99),
                Juego(nombre = "Mario Kart", descripcion = "Carreras divertidas", precio = 49.99),
                Juego(nombre = "Animal Crossing", descripcion = "Simulación de vida", precio = 54.99)
            )
            juegosEjemplo.forEach { repository.insertJuego(it) }
        }
    }
}