package com.example.jokesapp.presentation.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.toFavoriteJoke
import com.example.jokesapp.domain.usecase.SearchJokesUseCase
import com.example.jokesapp.domain.usecase.favorites.DeleteFavoriteUseCase
import com.example.jokesapp.domain.usecase.favorites.GetAllFavoritesUseCase
import com.example.jokesapp.domain.usecase.favorites.InsertFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berchoes on 21.01.2022.
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchJokesUseCase: SearchJokesUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val getAllFavoritesUseCase: GetAllFavoritesUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    var searchQuery by mutableStateOf("")
        private set

    var state by mutableStateOf(SearchScreenState())
        private set

    init {
        searchJokes("Chuck Norris")
    }

    fun setSearchText(text: String) {
        searchQuery = text
    }

    fun searchJokes(query: String) {
        state = SearchScreenState(isLoading = true)
        val searchFlow = searchJokesUseCase.invoke(query)
        val favoritesFlow = getAllFavoritesUseCase.invoke()

        favoritesFlow.zip(searchFlow) { favorites, searchResults ->
            state = when (searchResults) {
                is Resource.Error -> SearchScreenState(errorMessage = searchResults.message)
                is Resource.Success -> SearchScreenState(
                    favoriteJokes = favorites,
                    searchResults = searchResults.data
                )
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke)
    }

    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }
}