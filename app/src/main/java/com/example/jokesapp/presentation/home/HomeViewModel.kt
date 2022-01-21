package com.example.jokesapp.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.usecase.GetRandomJokeUseCase
import com.example.jokesapp.presentation.common.DialogState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Created by berchoes on 20.01.2022.
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRandomJokeUseCase: GetRandomJokeUseCase
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
                is Resource.Success -> _dialogState.value = DialogState(joke = it.data.content, isShowing = true)
            }
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }


    fun dismissDialog(){
        _dialogState.value = DialogState(isShowing = false)
    }
}