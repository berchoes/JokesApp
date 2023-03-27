package com.example.jokesapp.presentation.categories

import com.example.jokesapp.presentation.common.DialogState

/**
 * Created by berchoes on 26.01.2022.
 */

data class CategoriesState(
    var error: String = "",
    var categories: List<String> = emptyList(),
    var isLoading: Boolean = false,
    var dialog: DialogState = DialogState()
)