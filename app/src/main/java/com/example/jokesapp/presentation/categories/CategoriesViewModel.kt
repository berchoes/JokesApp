package com.example.jokesapp.presentation.categories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.toFavoriteJoke
import com.example.jokesapp.domain.usecase.GetCategoriesUseCase
import com.example.jokesapp.domain.usecase.GetRandomJokeUseCase
import com.example.jokesapp.domain.usecase.favorites.DeleteFavoriteUseCase
import com.example.jokesapp.domain.usecase.favorites.InsertFavoriteUseCase
import com.example.jokesapp.presentation.common.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by berchoes on 20.01.2022.
 */

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRandomJokeUseCase: GetRandomJokeUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _screenState = MutableStateFlow(CategoriesState())
    val screenState = _screenState.asStateFlow()

    init {
        getCategories()
    }

    private fun getCategories() {
        getCategoriesUseCase.invoke().onEach { response ->
            _screenState.update {
                when (response) {
                    is Resource.Error -> it.copy(error = response.message, isLoading = false)
                    is Resource.Success -> it.copy(categories = response.data, isLoading = false)
                    is Resource.Loading -> _screenState.value.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getRandomJoke(category: String) {
        getRandomJokeUseCase.invoke(category).onEach {
            _screenState.update { state ->
                when (it) {
                    is Resource.Error -> state.copy(
                        isLoading = false,
                        dialog = DialogState(
                            errorMessage = it.message,
                            isShowing = true,
                        )
                    )
                    is Resource.Success -> state.copy(
                        isLoading = false,
                        dialog = DialogState(
                            joke = it.data,
                            isShowing = true,
                        )
                    )
                    is Resource.Loading -> state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun dismissDialog() {
        _screenState.update {
            it.copy(dialog = DialogState(isShowing = false))
        }
    }
}