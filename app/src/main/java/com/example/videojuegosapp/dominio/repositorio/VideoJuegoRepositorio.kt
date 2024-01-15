package com.example.videojuegosapp.dominio.repositorio

import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego
import kotlinx.coroutines.flow.Flow

interface VideoJuegoRepositorio {
    suspend fun obtenerVideoJuegos(): Flow<ResultadoRed<List<VideoJuego>>>
    suspend fun obtenerDetalleVideoJuego(id: Int): Flow<ResultadoRed<DetalleVideoJuego>>
}