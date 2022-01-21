package com.example.jokesapp.presentation.search

import com.example.jokesapp.domain.model.Joke

/**
 * Created by berchoes on 21.01.2022.
 */

data class SearchScreenState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val jokes: List<Joke> = emptyList(),
    val isEmptyResult: Boolean = false
)