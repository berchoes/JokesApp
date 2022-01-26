package com.example.jokesapp.presentation.categories

/**
 * Created by berchoes on 26.01.2022.
 */

data class CategoriesState(
    var error: String = "",
    var categories: List<String> = emptyList(),
    var isLoading: Boolean = false
)