package com.example.jokesapp.presentation.common

/**
 * Created by berchoes on 20.01.2022.
 */
data class DialogState(
    var joke: String? = null,
    var errorMessage: String? = null,
    var isShowing: Boolean = false
)
