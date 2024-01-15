package com.example.videojuegosapp.ui.detalleVideoJuego

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetalleVideoJuegoViewModel @Inject constructor(
    private val videoJuegoRepositorio: VideoJuegoRepositorio,
) : ViewModel() {

    private val _detalleData: MutableStateFlow<DetalleVideoJuegoState> =
        MutableStateFlow(DetalleVideoJuegoState.Loading)
    val detalleData: StateFlow<DetalleVideoJuegoState> get() = _detalleData.asStateFlow()

    suspend fun obtenerDetalleVideoJuego(id: Int) = viewModelScope.launch {
        videoJuegoRepositorio.obtenerDetalleVideoJuego(id).onEach { response ->
            when (response) {
                is ResultadoRed.Loading -> {
                    _detalleData.value = DetalleVideoJuegoState.Loading
                }

                is ResultadoRed.Error -> {
                    _detalleData.value = DetalleVideoJuegoState.Error(response.throwable.toString())
                }

                is ResultadoRed.Success -> {
                    _detalleData.value = DetalleVideoJuegoState.Success(data = response.data)
                }
            }
        }.flowOn(Dispatchers.IO).collect()
    }

}