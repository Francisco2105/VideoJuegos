package com.example.myapplicationplay.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

import androidx.compose.ui.unit.dp
import com.example.myapplicationplay.R
import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.viewmodel.JuegoViewModel

@Composable
fun Juegos(viewModel: JuegoViewModel) {
    val juegos by viewModel.juegos.collectAsState()
    val nombre by viewModel.nombre.collectAsState()
    val creador by viewModel.creador.collectAsState()
    val genero by viewModel.genero.collectAsState()
    val precio by viewModel.precio.collectAsState()

    // Estado adicional para saber si estamos editando un juego
    var juegoEnEdicion by remember { mutableStateOf<Juego?>(null) }

    // Estado para búsqueda
    var textoBusqueda by remember { mutableStateOf("") }

    // Lista filtrada según la búsqueda
    val juegosFiltrados = juegos.filter { juego ->
        juego.nombre.contains(textoBusqueda, ignoreCase = true)
    }


    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = " !!! GameHub !!! ",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.videojuego),
            contentDescription = "videojuego",
            modifier = Modifier
                .width(250.dp)
                .height(175.dp)
        )

        OutlinedTextField(value = nombre, onValueChange = { viewModel.nombre.value = it }, label = { Text("Nombre") })
        OutlinedTextField(value = creador, onValueChange = { viewModel.creador.value = it }, label = { Text("Creador") })
        OutlinedTextField(value = genero, onValueChange = { viewModel.genero.value = it }, label = { Text("Género") })
        OutlinedTextField(value = precio, onValueChange = { viewModel.precio.value = it }, label = { Text("Precio") })

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val precioInt = precio.toIntOrNull() ?: 0 // convierte el texto a Int
            viewModel.agregarJuegos(
                Juego(
                    nombre = nombre,
                    creador = creador,
                    genero = genero,
                    precio = precioInt
                )
            )
            viewModel.nombre.value = ""; viewModel.creador.value = ""; viewModel.genero.value = ""; viewModel.precio.value = ""
        }) {
            Text("Agregar Juego")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val precioInt = precio.toIntOrNull() ?: 0 // convierte el texto a Int
            viewModel.actualizarJuegos(
                Juego(
                    nombre = nombre,
                    creador = creador,
                    genero = genero,
                    precio = precioInt
                )
            )
            viewModel.nombre.value = ""; viewModel.creador.value = ""; viewModel.genero.value = ""; viewModel.precio.value = ""
        }) {
            Text("Actualizar Juego")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(juegos) { juego ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = juego.nombre,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = juego.creador,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = juego.genero,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "${juego.precio}",
                        modifier = Modifier.weight(1f)
                    )
                }
                Divider()
            }
        }
    }
}