package com.example.jokesapp.presentation.categories

/**
 * Created by berchoes on 20.01.2022.
 */
data class CategoriesState(
    val isLoading: Boolean = false,
    val categories: List<String> = emptyList(),
    val error: String? = null
)
