package com.example.jokesapp.domain.usecase.favorites

import com.example.jokesapp.data.local.entity.FavoriteJoke
import com.example.jokesapp.domain.repository.FavoritesRepository
import javax.inject.Inject

/**
 * Created by berchoes on 22.01.2022.
 */

class DeleteFavoriteUseCase @Inject constructor(private val repository: FavoritesRepository) {
    suspend operator fun invoke(joke: FavoriteJoke) {
        repository.deleteFavorite(joke)
    }
}