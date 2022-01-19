package com.example.jokesapp.data.dto


data class SearchResultDto(
    val result: List<JokeDto>,
    val total: Int
)