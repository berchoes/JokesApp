package com.example.jokesapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    init {
        searchJokes("Chuck Norris")
    }

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun searchJokes(query: String) {
        _state.value = SearchScreenState(isLoading = true)
        val searchFlow = searchJokesUseCase.invoke(query)
        val favoritesFlow = getAllFavoritesUseCase.invoke()

        favoritesFlow.zip(searchFlow) { favorites, searchResults ->
            when (searchResults) {
                is Resource.Error -> {
                    _state.value = SearchScreenState(errorMessage = searchResults.message)
                }
                is Resource.Success -> {
                    _state.value = SearchScreenState(
                        favoriteJokes = favorites,
                        searchResults = searchResults.data
                    )
                }
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