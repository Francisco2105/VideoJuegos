package com.example.myapplicationplay



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplicationplay.model.AppDatabase
import com.example.myapplicationplay.repository.JuegoRepository
import com.example.myapplicationplay.ui.*
import com.example.myapplicationplay.ui.theme.MyApplicationplayTheme
import com.example.myapplicationplay.viewmodel.JuegoViewModel

import androidx.room.Room
import com.example.myapplicationplay.ui.theme.Juegos

class MainActivity : ComponentActivity() {


    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "canciones_db"
        ).build()
    }
    private val repository by lazy { JuegoRepository(db.JuegoDao()) }
    private val viewModelJuegos by lazy { JuegoViewModel(repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationplayTheme { // <- aquí va el nombre de TU TEMA, lo puedes encontrar en ui/theme/Theme.kt
                Juegos(viewModelJuegos)
                //Canciones(viewModelCanciones)
                //Formulario(viewModelForm)
                //BotonCargando()
                //TextoInvertido()
                //Persistencia()
                //UsuarioFormScreen()
                //Modal()
                // Formulario()
                //Login()
                //Navegacion()
                //CamaraFotos()
                //ModalScreen()
                // GPS()
                //AgregarUsuarios()
                // TocarPantalla()
            }
        }
    }



}