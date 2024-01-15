package com.example.videojuegosapp.di

import com.example.videojuegosapp.data.api.VideoJuegoApi
import com.example.videojuegosapp.data.repositorio.VideoJuegoRepositorio
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuloRepositorio {

    @Provides
    @Singleton
    fun provideGameRepository(gameApi: VideoJuegoApi): VideoJuegoRepositorio {
        return VideoJuegoRepositorio(gameApi)
    }
}