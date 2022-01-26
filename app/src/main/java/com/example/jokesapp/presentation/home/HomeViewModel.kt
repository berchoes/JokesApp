package com.example.jokesapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    var dialogState by mutableStateOf(DialogState())
        private set

    fun getRandomJoke() {
        dialogState = DialogState(isLoading = true)
        getRandomJokeUseCase.invoke().onEach {
            dialogState = when (it) {
                is Resource.Error -> DialogState(errorMessage = it.message, isShowing = true)
                is Resource.Success -> DialogState(joke = it.data, isShowing = true)
            }
        }.launchIn(viewModelScope)
    }

    fun dismissDialog() {
        dialogState = DialogState(isShowing = false)
    }


    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke)
    }
}