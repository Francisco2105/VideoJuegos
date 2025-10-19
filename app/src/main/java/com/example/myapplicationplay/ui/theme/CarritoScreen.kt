package com.example.myapplicationplay.ui.theme

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.myapplicationplay.model.Juego
import com.example.myapplicationplay.viewmodel.JuegoViewModel


@Composable
fun CarritoScreen(
    viewModel: JuegoViewModel,
    onBack: () -> Unit
) {
    val juegosEnCarrito by viewModel.juegosEnCarrito.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Carrito de Compras",
                style = MaterialTheme.typography.headlineMedium
            )

            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Volver"
                )
            }
        }

        if (juegosEnCarrito.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "El carrito está vacío",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(juegosEnCarrito) { juego ->
                    ItemCarrito(
                        juego = juego,
                        onRemover = { viewModel.removerDelCarrito(juego.id) },
                        onCantidadChange = { nuevaCantidad ->
                            viewModel.actualizarCantidad(juego.id, nuevaCantidad)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            // Total y botón de comprar
            val total = juegosEnCarrito.sumOf { it.precio * it.cantidad }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Total:",
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Text(
                            text = "$${String.format("%.2f", total)}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { /* Procesar compra */ },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = juegosEnCarrito.isNotEmpty()
                    ) {
                        Text("Comprar Ahora")
                    }
                }
            }
        }
    }
}

@Composable
fun ItemCarrito(
    juego: Juego,
    onRemover: () -> Unit,
    onCantidadChange: (Int) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = juego.nombre,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = juego.descripcion,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = "$${String.format("%.2f", juego.precio)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            // Selector de cantidad - CORREGIDO
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(
                    onClick = {
                        if (juego.cantidad > 1) onCantidadChange(juego.cantidad - 1)
                    },
                    enabled = juego.cantidad > 1,
                    modifier = Modifier.size(40.dp)
                ) {
                    Text("-")

                    Text(
                        text = juego.cantidad.toString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.width(20.dp)
                    )

                    IconButton(
                        onClick = { onCantidadChange(juego.cantidad + 1) },
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Aumentar cantidad"
                        )
                    }
                }

                IconButton(onClick = onRemover) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Remover del carrito",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
