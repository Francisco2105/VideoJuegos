package com.example.myapplicationplay.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.repository.JuegoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class JuegoViewModel(private val repository: JuegoRepository) : ViewModel() {

    val nombre = MutableStateFlow("")
    val creador = MutableStateFlow("")
    val genero = MutableStateFlow("")
    val precio = MutableStateFlow("")

    val juegos = MutableStateFlow<List<Juego>>(emptyList())
    // ...

    init {
        cargarJuegos()
    }

    private fun cargarJuegos() {
        viewModelScope.launch {
            juegos.value = repository.getAll()
        }
    }

    fun agregarJuegos(juego: Juego) {
        viewModelScope.launch {
            repository.insert(juego)
            cargarJuegos()
        }
    }

    fun actualizarJuegos(juego: Juego) {
        viewModelScope.launch {
            repository.update(juego)
            cargarJuegos()
        }
    }

    fun eliminarJuegos(juego: Juego) {
        viewModelScope.launch {
            repository.delete(juego)
            cargarJuegos()
        }
    }
}