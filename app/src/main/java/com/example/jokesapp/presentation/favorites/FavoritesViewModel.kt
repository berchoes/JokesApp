package com.example.jokesapp.presentation.favorites

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.data.local.entity.FavoriteJoke
import com.example.jokesapp.domain.usecase.favorites.DeleteFavoriteUseCase
import com.example.jokesapp.domain.usecase.favorites.GetAllFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
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

    var favorites by mutableStateOf<List<FavoriteJoke>>(emptyList())
        private set

    init {
        getFavorites()
    }

    private fun getFavorites() {
        getAllFavoritesUseCase.invoke().onEach {
            favorites = it
        }
            .catch { e -> Log.e("FetchFavorites", e.localizedMessage ?: "RoomFetchError") }
            .launchIn(viewModelScope)
    }

    fun deleteFavorite(joke: FavoriteJoke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke)
    }
}