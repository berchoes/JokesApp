package com.example.jokesapp.domain.repository

import com.example.jokesapp.data.local.entity.FavoriteJoke
import kotlinx.coroutines.flow.Flow

/**
 * Created by berchoes on 21.01.2022.
 */

interface FavoritesRepository {

    fun getAllFavoriteJokes(): Flow<List<FavoriteJoke>>

    suspend fun insertFavorite(joke: FavoriteJoke)

    suspend fun deleteFavorite(joke: FavoriteJoke)
}