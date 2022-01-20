package com.example.jokesapp.presentation.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.usecase.GetCategoriesUseCase
import com.example.jokesapp.domain.usecase.GetRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _state = mutableStateOf(CategoriesState())
    val state: State<CategoriesState> = _state

    private val _randomJoke = MutableSharedFlow<Joke>()
    val randomJoke = _randomJoke.asSharedFlow()

    init {
        getCategories()
    }


    private fun getCategories() = getCategoriesUseCase.invoke().onEach {
        when (it) {
            is Resource.Error -> {
                _state.value = CategoriesState(error = it.message)
            }
            Resource.Loading -> {
                _state.value = CategoriesState(isLoading = true)
            }
            is Resource.Success -> {
                _state.value = CategoriesState(categories = it.data)
            }
        }
    }.launchIn(viewModelScope)


    private fun getRandomJoke(category: String) = getRandomJokeUseCase.invoke(category).onEach {
        when (it) {
            is Resource.Error -> TODO()
            Resource.Loading -> TODO()
            is Resource.Success -> {
                _randomJoke.emit(it.data)
            }
        }
    }
}