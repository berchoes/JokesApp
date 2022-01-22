package com.example.jokesapp.presentation.favorites

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.data.local.entity.FavoriteJoke
import com.example.jokesapp.domain.usecase.favorites.DeleteFavoriteUseCase
import com.example.jokesapp.domain.usecase.favorites.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berchoes on 22.01.2022.
 */

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _favorites = mutableStateOf<List<FavoriteJoke>>(emptyList())
    val favorites: State<List<FavoriteJoke>> = _favorites

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getAllFavoritesUseCase.invoke().onEach {
            _favorites.value = it
        }.launchIn(viewModelScope)
    }

    fun deleteFavorite(joke: FavoriteJoke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke)
    }
}