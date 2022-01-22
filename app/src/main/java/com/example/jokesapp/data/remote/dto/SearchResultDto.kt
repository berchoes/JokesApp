package com.example.jokesapp.data.remote.dto


data class SearchResultDto(
    val result: List<JokeDto>,
    val total: Int
)