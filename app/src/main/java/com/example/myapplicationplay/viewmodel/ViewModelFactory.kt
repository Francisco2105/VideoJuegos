package com.example.myapplicationplay.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationplay.model.AppDataBase
import com.example.myapplicationplay.repository.JuegoRepository

class JuegoViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(JuegoViewModel::class.java)) {
            val database = AppDataBase.getDatabase(context)
            val repository = JuegoRepository(database.juegoDao())
            return JuegoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}