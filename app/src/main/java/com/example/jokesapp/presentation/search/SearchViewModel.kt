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
import kotlinx.coroutines.flow.*
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

    private val _pageState = MutableStateFlow(SearchScreenState())
    val pageState = _pageState.asStateFlow()

    init {
        searchJokes("Chuck Norris")
    }

    fun setSearchText(text: String) {
        searchQuery = text
    }

    fun searchJokes(query: String) {
        val searchFlow = searchJokesUseCase.invoke(query)
        val favoritesFlow = getAllFavoritesUseCase.invoke()

        searchFlow.combine(favoritesFlow) { searchResults, favorites ->
            _pageState.update {
                when (searchResults) {
                    is Resource.Error -> it.copy(
                        errorMessage = searchResults.message,
                        isLoading = false
                    )
                    is Resource.Success -> it.copy(
                        errorMessage = null,
                        favoriteJokes = favorites,
                        searchResults = searchResults.data,
                        isLoading = false
                    )
                    is Resource.Loading -> {
                        it.copy(isLoading = true)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun isInFavorites(joke: Joke): Boolean {
        return _pageState.value.favoriteJokes.any {
            joke.toFavoriteJoke() == it
        }
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }
}
