package com.example.jokesapp.domain.usecase.favorites

import com.example.jokesapp.data.local.entity.FavoriteJoke
import com.example.jokesapp.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by berchoes on 21.01.2022.
 */

class GetAllFavoritesUseCase @Inject constructor(private val repository: FavoritesRepository) {

    operator fun invoke(): Flow<List<FavoriteJoke>> = repository.getAllFavoriteJokes()
}