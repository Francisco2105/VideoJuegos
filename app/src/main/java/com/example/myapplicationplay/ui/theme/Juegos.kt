package com.example.myapplicationplay.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.viewmodel.JuegoViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Juegos(
    viewModel: JuegoViewModel,
    onAgregarJuegoClick: () -> Unit,
    onCarritoClick: () -> Unit
) {
    val todosLosJuegos by viewModel.todosLosJuegos.collectAsState(initial = emptyList())
    val juegosEnCarrito by viewModel.juegosEnCarrito.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tienda de Juegos") },
                actions = {
                    IconButton(onClick = onCarritoClick) {
                        BadgedBox(
                            badge = {
                                if (juegosEnCarrito.isNotEmpty()) {
                                    Badge {
                                        Text(juegosEnCarrito.size.toString())
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Carrito"
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregarJuegoClick) {
                Icon(Icons.Default.Add, "Agregar juego")
            }
        }
    ) { paddingValues ->
        if (todosLosJuegos.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text("No hay juegos disponibles")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                items(todosLosJuegos) { juego ->
                    ItemJuego(
                        juego = juego,
                        onAgregarAlCarrito = { viewModel.agregarAlCarrito(juego.id) },
                        onRemoverDelCarrito = { viewModel.removerDelCarrito(juego.id) }
                    )
                }
            }
        }
    }
}

@Composable
fun ItemJuego(
    juego: Juego,
    onAgregarAlCarrito: () -> Unit,
    onRemoverDelCarrito: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = juego.nombre,
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = juego.descripcion,
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${String.format("%.2f", juego.precio)}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                if (juego.enCarrito) {
                    Button(
                        onClick = onRemoverDelCarrito,
                        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
                    ) {
                        Text("Remover")
                    }
                } else {
                    Button(onClick = onAgregarAlCarrito) {
                        Text("Agregar al Carrito")
                    }
                }
            }
        }
    }
}