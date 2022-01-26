package com.example.jokesapp.presentation.categories

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    var screenState by mutableStateOf(CategoriesState())
        private set

    var dialogState by mutableStateOf(DialogState())
        private set

    init {
        getCategories()
    }

    private fun getCategories() {
        screenState = CategoriesState(isLoading = true)
        getCategoriesUseCase.invoke().onEach {
            screenState = when (it) {
                is Resource.Error -> CategoriesState(error = it.message)
                is Resource.Success -> CategoriesState(categories = it.data)
            }
        }.launchIn(viewModelScope)
    }

    fun getRandomJoke(category: String) {
        dialogState = DialogState(isLoading = true)
        getRandomJokeUseCase.invoke(category).onEach {
            dialogState = when (it) {
                is Resource.Error -> DialogState(errorMessage = it.message, isShowing = true)
                is Resource.Success -> DialogState(joke = it.data, isShowing = true)
            }
        }.launchIn(viewModelScope)
    }

    fun insertFavorite(joke: Joke) = viewModelScope.launch {
        insertFavoriteUseCase.invoke(joke)
    }

    fun deleteFavorite(joke: Joke) = viewModelScope.launch {
        deleteFavoriteUseCase.invoke(joke.toFavoriteJoke())
    }

    fun dismissDialog() {
        dialogState = DialogState(isShowing = false)
    }
}