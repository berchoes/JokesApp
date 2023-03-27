package com.example.jokesapp.di

import com.example.jokesapp.data.repository.FavoritesRepositoryImpl
import com.example.jokesapp.data.repository.JokesRepositoryImpl
import com.example.jokesapp.domain.repository.FavoritesRepository
import com.example.jokesapp.domain.repository.JokesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Berk Çelik on 27.03.2023.
 * Volt Lines
 */

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindJokesRepository(
        jokesRepositoryImpl: JokesRepositoryImpl
    ): JokesRepository

    @Binds
    @Singleton
    abstract fun bindFavoritesRepository(
        favoritesRepositoryImpl: FavoritesRepositoryImpl
    ): FavoritesRepository
}