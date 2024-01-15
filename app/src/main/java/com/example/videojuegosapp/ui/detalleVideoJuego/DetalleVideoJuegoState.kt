package com.example.videojuegosapp.ui.detalleVideoJuego

import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego

sealed class DetalleVideoJuegoState {
    object Loading : DetalleVideoJuegoState()
    data class Success(val data: DetalleVideoJuego) : DetalleVideoJuegoState()
    data class Error(val message: String) : DetalleVideoJuegoState()
}