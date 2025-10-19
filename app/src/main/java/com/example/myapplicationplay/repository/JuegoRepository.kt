package com.example.myapplicationplay.repository

import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.model.JuegoDao


class JuegoRepository(private val dao: JuegoDao ) {

    suspend fun getAll() = dao.getAll()
    suspend fun insert(juego: Juego) = dao.insert(juego)
    suspend fun update(juego: Juego) = dao.update(juego)
    suspend fun delete(juego: Juego) = dao.delete(juego)
}