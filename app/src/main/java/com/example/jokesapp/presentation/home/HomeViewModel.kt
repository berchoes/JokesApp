package com.example.jokesapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.toFavoriteJoke
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
class HomeViewModel @Inject constructor(
    private val getRandomJokeUseCase: GetRandomJokeUseCase,
    private val insertFavoriteUseCase: InsertFavoriteUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
) : ViewModel() {

    private val _dialogState =  MutableStateFlow(DialogState())
    val dialogState = _dialogState.asStateFlow()

    fun getRandomJoke() {
        getRandomJokeUseCase.invoke().onEach {
            _dialogState.update { state ->
                when (it) {
                    is Resource.Error -> DialogState(
                        errorMessage = it.message,
                        isShowing = true,
                        isLoading = false
                    )
                    is Resource.Success -> DialogState(
                        joke = it.data,
                        isShowing = true,
                        isLoading = false
                    )
                    is Resource.Loading -> state.copy(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun dismissDialog() {
        _dialogState.update {
            it.copy(isShowing = false)
        }
    }

    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }
}