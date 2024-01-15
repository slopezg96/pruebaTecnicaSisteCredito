package com.example.videojuegosapp.data.dto

data class DetalleVideoJuegoDTO(val title: String,
                                val description: String,
                                val thumbnail: String,
                                val developer: String,
                                val freetogame_profile_url: String,
                                val game_url: String,
                                val genre: String,
                                val id: Int,
                                val minimum_system_requirements: RequisitosMinimosSistemaDTO,
                                val platform: String,
                                val publisher: String,
                                val release_date: String,
                                val screenshots: List<CapturaPantalla>,
                                val short_description: String,
                                val status: String)
