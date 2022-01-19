package com.example.jokesapp.data.dto

import com.example.jokesapp.domain.model.SearchResults

data class SearchResultDto(
    val result: List<JokeDto>,
    val total: Int
)


fun SearchResultDto.toSearchResults() = SearchResults(
    results = this.result.map { it.toJoke() }
)