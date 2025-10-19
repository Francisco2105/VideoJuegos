package com.example.myapplicationplay.model

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface JuegoDao {
    @Query("SELECT * FROM juegos")
    fun getAllJuegos(): Flow<List<Juego>>

    @Query("SELECT * FROM juegos WHERE enCarrito = 1")
    fun getJuegosEnCarrito(): Flow<List<Juego>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJuego(juego: Juego)

    @Update
    suspend fun updateJuego(juego: Juego)

    @Query("DELETE FROM juegos WHERE id = :id")
    suspend fun deleteJuego(id: Long)

    @Query("UPDATE juegos SET enCarrito = :enCarrito WHERE id = :id")
    suspend fun actualizarEstadoCarrito(id: Long, enCarrito: Boolean)

    @Query("UPDATE juegos SET cantidad = :cantidad WHERE id = :id")
    suspend fun actualizarCantidad(id: Long, cantidad: Int)
}