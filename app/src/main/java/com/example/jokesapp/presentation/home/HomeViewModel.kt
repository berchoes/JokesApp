package com.example.jokesapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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
): ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState


    fun getRandomJoke(){
        _isLoading.value = true
        getRandomJokeUseCase.invoke().onEach {
            when(it){
                is Resource.Error -> _dialogState.value = DialogState(errorMessage = it.message, isShowing = true)
                is Resource.Success -> _dialogState.value = DialogState(joke = it.data, isShowing = true)
            }
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }


    fun dismissDialog(){
        _dialogState.value = DialogState(isShowing = false)
    }


    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke)
    }
}