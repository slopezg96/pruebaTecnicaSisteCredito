package com.example.videojuegosapp.ui.videojuegos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.videojuegosapp.data.ResultadoRed
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorio
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class VideoJuegosViewModel @Inject constructor(
    private val videoJuegoRepositorio: VideoJuegoRepositorio
) : ViewModel() {

    private val _videoJuegosLista: MutableStateFlow<VideoJuegoState> = MutableStateFlow(VideoJuegoState.Loading)
    val videoJuegosLista: StateFlow<VideoJuegoState> get() = _videoJuegosLista.asStateFlow()


    fun getAllGame() = viewModelScope.launch(Dispatchers.IO) {
        videoJuegoRepositorio.obtenerVideoJuegos().onEach { response ->
            when (response) {
                is ResultadoRed.Loading -> {
                    _videoJuegosLista.value = VideoJuegoState.Loading
                }

                is ResultadoRed.Error -> {
                    _videoJuegosLista.value = VideoJuegoState.Error(response.throwable.toString())
                }

                is ResultadoRed.Success -> {
                    _videoJuegosLista.value = VideoJuegoState.Success(data = response.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    suspend fun obtenerJuegosFavoritos() {
        /*videoJuegoRepositorio.getGameByCategory(category).onEach { response ->
            when (response) {
                is NetworkResource.Loading -> {
                    _gameList.value = HomeUiState.Loading
                }

                is NetworkResource.Error -> {
                    _gameList.value = HomeUiState.Error(response.throwable.toString())
                }

                is NetworkResource.Success -> {
                    _gameList.value = HomeUiState.Success(data = response.data)
                }
            }
        }.launchIn(viewModelScope)*/
    }

}