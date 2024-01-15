package com.example.videojuegosapp.data.traductor

import android.provider.MediaStore.Video
import com.example.videojuegosapp.data.dto.CapturaPantalla
import com.example.videojuegosapp.data.dto.DetalleVideoJuegoDTO
import com.example.videojuegosapp.data.dto.RequisitosMinimosSistemaDTO
import com.example.videojuegosapp.data.dto.VideoJuegoDTO
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego

fun convertirVideoJuegoADominio(dto: VideoJuegoDTO): VideoJuego {
    return VideoJuego(miniatura = dto.thumbnail,
        titulo = dto.title,
        genero = dto.genre,
        desarrollador = dto.developer,
        descripcionCorta = dto.short_description,
        plataforma = dto.platform,
        editor = dto.publisher,
        fechaLanzamiento = dto.release_date,
        id = dto.id)
}
fun convertirDetalleVideoJuegoADominio(detalleDTO: DetalleVideoJuegoDTO): DetalleVideoJuego {
    return DetalleVideoJuego( titulo = detalleDTO.title,
     descripcion = detalleDTO.description,
     miniatura = detalleDTO.thumbnail,
     desarrollador = detalleDTO.developer,
     urlPerfilFreeToGame = detalleDTO.freetogame_profile_url,
     urlJuego = detalleDTO.game_url,
     genero = detalleDTO.genre,
     id = detalleDTO.id,
     requisitosMinimosSistema = detalleDTO.minimum_system_requirements,
     plataforma = detalleDTO.platform,
        editor = detalleDTO.publisher,
     fechaLanzamiento = detalleDTO.release_date,
     capturasPantalla = detalleDTO.screenshots,
     descripcionCorta = detalleDTO.short_description,
     estado = detalleDTO.status)
}