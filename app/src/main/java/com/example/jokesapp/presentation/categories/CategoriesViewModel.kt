package com.example.jokesapp.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.usecase.GetCategoriesUseCase
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
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getRandomJokeUseCase: GetRandomJokeUseCase
) : ViewModel() {

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf("")
    val error: State<String> = _error

    private val _categories = mutableStateOf<List<String>>(emptyList())
    val categories: State<List<String>> = _categories

    private val _dialogState = mutableStateOf(DialogState())
    val dialogState: State<DialogState> = _dialogState


    init {
        getCategories()
    }


    private fun getCategories() {
        _isLoading.value = true
        getCategoriesUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> {
                    _error.value = it.message
                }
                is Resource.Success -> {
                    _categories.value = it.data
                }
            }
            _isLoading.value = false
        }.launchIn(viewModelScope)
    }


    fun getRandomJoke(category: String) {
        _isLoading.value = true
        getRandomJokeUseCase.invoke(category).onEach {
            when (it) {
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