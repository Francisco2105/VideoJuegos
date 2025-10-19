package com.example.myapplicationplay


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplicationplay.ui.theme.MyApplicationplayTheme
import com.example.myapplicationplay.ui.theme.Juegos
import com.example.myapplicationplay.ui.theme.AgregarJuegoScreen
import com.example.myapplicationplay.ui.theme.CarritoScreen
import com.example.myapplicationplay.viewmodel.JuegoViewModel
import com.example.myapplicationplay.viewmodel.JuegoViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationplayTheme {
                // Estado para controlar la pantalla actual
                var currentScreen by remember { mutableStateOf<Screen>(Screen.ListaJuegos) }

                // ViewModel con factory
                val viewModel: JuegoViewModel = viewModel(
                    factory = JuegoViewModelFactory(this)
                )

                // Inicializar datos de ejemplo cuando la app inicia
                LaunchedEffect(Unit) {
                    viewModel.inicializarDatosEjemplo()
                }

                // Navegación entre pantallas
                when (currentScreen) {
                    Screen.ListaJuegos -> {
                        Juegos(
                            viewModel = viewModel,
                            onAgregarJuegoClick = { currentScreen = Screen.AgregarJuego },
                            onCarritoClick = { currentScreen = Screen.Carrito }
                        )
                    }
                    Screen.AgregarJuego -> {
                        AgregarJuegoScreen(
                            viewModel = viewModel,
                            onBack = { currentScreen = Screen.ListaJuegos }
                        )
                    }
                    Screen.Carrito -> {
                        CarritoScreen(
                            viewModel = viewModel,
                            onBack = { currentScreen = Screen.ListaJuegos }
                        )
                    }
                }
            }
        }
    }
}

// Sellado para las diferentes pantallas
sealed class Screen {
    object ListaJuegos : Screen()
    object AgregarJuego : Screen()
    object Carrito : Screen()
}