package com.example.myapplicationplay.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "juegos")
data class Juego(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String = "",
    val enCarrito: Boolean = false,
    val cantidad: Int = 1
)