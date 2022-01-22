package com.example.jokesapp.presentation.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.usecase.SearchJokesUseCase
import com.example.jokesapp.domain.usecase.favorites.InsertFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berchoes on 21.01.2022.
 */

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchJokesUseCase: SearchJokesUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase
) : ViewModel() {

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _state = mutableStateOf(SearchScreenState())
    val state: State<SearchScreenState> = _state

    init {
        searchJokes("Michael Jackson")
    }

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun searchJokes(query: String) {
        _state.value = SearchScreenState(isLoading = true)
        searchJokesUseCase.invoke(query).onEach {
            when (it) {
                is Resource.Error -> _state.value = SearchScreenState(errorMessage = it.message)
                is Resource.Success -> {
                    if (it.data.isNullOrEmpty()) {
                        _state.value = SearchScreenState(isEmptyResult = true)
                    } else {
                        _state.value = SearchScreenState(jokes = it.data)

                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke)
    }
}