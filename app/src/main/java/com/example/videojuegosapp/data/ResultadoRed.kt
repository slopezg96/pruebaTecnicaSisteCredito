package com.example.videojuegosapp.data

sealed class ResultadoRed<out T : Any> {
    object Loading : ResultadoRed<Nothing>()
    data class Success<out T : Any>(val data: T) : ResultadoRed<T>()
    data class Error(val throwable: String?) : ResultadoRed<Nothing>()
}