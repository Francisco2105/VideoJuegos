package com.example.myapplicationplay.repository

import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.model.JuegoDao
import kotlinx.coroutines.flow.Flow

class JuegoRepository(private val juegoDao: JuegoDao) {

    fun getAllJuegos(): Flow<List<Juego>> = juegoDao.getAllJuegos()

    fun getJuegosEnCarrito(): Flow<List<Juego>> = juegoDao.getJuegosEnCarrito()

    suspend fun insertJuego(juego: Juego) = juegoDao.insertJuego(juego)

    suspend fun updateJuego(juego: Juego) = juegoDao.updateJuego(juego)

    suspend fun agregarAlCarrito(id: Long) = juegoDao.actualizarEstadoCarrito(id, true)

    suspend fun removerDelCarrito(id: Long) = juegoDao.actualizarEstadoCarrito(id, false)

    suspend fun actualizarCantidad(id: Long, cantidad: Int) = juegoDao.actualizarCantidad(id, cantidad)
}