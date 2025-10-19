package com.example.myapplicationplay.model

import androidx.room.*

@Dao
interface JuegoDao {
    @Query("SELECT * FROM juegos ")
    suspend fun getAll(): List<Juego>

    @Insert
    suspend fun insert(juego: Juego)

    @Update
    suspend fun update(juego: Juego)

    @Delete
    suspend fun delete(juego: Juego)

}