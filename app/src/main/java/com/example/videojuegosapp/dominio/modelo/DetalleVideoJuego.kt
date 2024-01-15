package com.example.videojuegosapp.dominio.modelo

import com.example.videojuegosapp.data.dto.CapturaPantalla
import com.example.videojuegosapp.data.dto.RequisitosMinimosSistemaDTO

data class DetalleVideoJuego(val titulo: String,
                             val descripcion: String,
                             val miniatura: String,
                             val desarrollador: String,
                             val urlPerfilFreeToGame: String,
                             val urlJuego: String,
                             val genero: String,
                             val id: Int,
                             val requisitosMinimosSistema: RequisitosMinimosSistemaDTO,
                             val plataforma: String,
                             val editor: String,
                             val fechaLanzamiento: String,
                             val capturasPantalla: List<CapturaPantalla>,
                             val descripcionCorta: String,
                             val estado: String)
