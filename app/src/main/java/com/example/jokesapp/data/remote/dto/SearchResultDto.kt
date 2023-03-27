package com.example.jokesapp.data.remote.dto

import com.example.jokesapp.domain.model.SearchResult


data class SearchResultDto(
    val result: List<JokeDto>,
    val total: Int
)

fun SearchResultDto.toSearchResult() = SearchResult(
    result = this.result.map { it.toJoke() },
    total = this.total
)