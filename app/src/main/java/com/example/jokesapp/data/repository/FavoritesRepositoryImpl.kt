package com.example.jokesapp.data.repository

import com.example.jokesapp.data.local.FavoritesDao
import com.example.jokesapp.data.local.entity.FavoriteJoke
import com.example.jokesapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by berchoes on 21.01.2022.
 */

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: FavoritesDao
) : FavoritesRepository {

    override fun getAllFavoriteJokes(): Flow<List<FavoriteJoke>> = dao.getAllFavorites()

    override suspend fun insertFavorite(joke: FavoriteJoke) = dao.insertFavorite(joke)

    override suspend fun deleteFavorite(joke: FavoriteJoke) = dao.deleteFavorite(joke)
}