package com.example.videojuegosapp.dominio.modelo

data class VideoJuego(val miniatura: String,
                      val titulo: String,
                      val genero: String,
                      val desarrollador: String,
                      val descripcionCorta: String,
                      val plataforma: String,
                      val editor: String,
                      val fechaLanzamiento: String,
                      val id: Int)
