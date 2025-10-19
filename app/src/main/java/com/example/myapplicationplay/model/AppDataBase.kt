package com.example.myapplicationplay.model


import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Juego::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun JuegoDao(): JuegoDao
}