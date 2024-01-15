package com.example.videojuegosapp.ui.videojuegos

import com.example.videojuegosapp.dominio.modelo.VideoJuego

sealed class VideoJuegoState {
    object Loading : VideoJuegoState()
    data class Success(val data: List<VideoJuego>) : VideoJuegoState()
    data class Error(val message: String) : VideoJuegoState()
}