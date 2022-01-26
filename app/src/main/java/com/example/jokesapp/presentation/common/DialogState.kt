package com.example.jokesapp.presentation.common

import com.example.jokesapp.domain.model.Joke

/**
 * Created by berchoes on 20.01.2022.
 */

data class DialogState(
    var joke: Joke? = null,
    var errorMessage: String? = null,
    var isShowing: Boolean = false,
    var isLoading: Boolean = false
)
