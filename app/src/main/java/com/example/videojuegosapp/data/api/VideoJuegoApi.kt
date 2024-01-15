package com.example.videojuegosapp.data.api

import com.example.videojuegosapp.data.dto.DetalleVideoJuegoDTO
import com.example.videojuegosapp.data.dto.VideoJuegoDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoJuegoApi {
    @GET("api/games")
    suspend fun obtenerVideoJuegos(): List<VideoJuegoDTO>

    @GET("api/game")
    suspend fun obtenerDetalleVideoJuego(
        @Query("id") gameId: Int
    ): DetalleVideoJuegoDTO
}