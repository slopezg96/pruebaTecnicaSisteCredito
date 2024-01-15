package com.example.videojuegosapp.data.repositorio

import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.data.api.VideoJuegoApi
import com.example.videojuegosapp.data.traductor.convertirDetalleVideoJuegoADominio
import com.example.videojuegosapp.data.traductor.convertirVideoJuegoADominio
import com.example.videojuegosapp.dominio.modelo.DetalleVideoJuego
import com.example.videojuegosapp.dominio.modelo.VideoJuego
import com.example.videojuegosapp.dominio.repositorio.VideoJuegoRepositorio
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import okio.IOException
import javax.inject.Inject

class VideoJuegoRepositorio @Inject constructor(
    private val api: VideoJuegoApi
) : VideoJuegoRepositorio {
    override suspend fun obtenerVideoJuegos(): Flow<ResultadoRed<List<VideoJuego>>> = flow {
        emit(ResultadoRed.Loading)
        try {
            val response = api.obtenerVideoJuegos()
            emit(ResultadoRed.Success(data = response.map { convertirVideoJuegoADominio(it) }))
        } catch (e: HttpException) {
            e.printStackTrace()
            emit(ResultadoRed.Error(e.localizedMessage?.toString()))
        } catch (e: IOException) {
            e.printStackTrace()
            emit(ResultadoRed.Error(e.localizedMessage?.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun obtenerDetalleVideoJuego(id: Int): Flow<ResultadoRed<DetalleVideoJuego>> =
        flow {
            emit(ResultadoRed.Loading)
            try {
                val response = api.obtenerDetalleVideoJuego(id)
                emit(ResultadoRed.Success(data = convertirDetalleVideoJuegoADominio(response)))
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ResultadoRed.Error(e.localizedMessage?.toString()))
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ResultadoRed.Error(e.localizedMessage?.toString()))
            }
        }.flowOn(Dispatchers.IO)
}