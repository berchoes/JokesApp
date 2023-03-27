package com.example.jokesapp.domain.repository

import com.example.jokesapp.data.remote.dto.SearchResultDto
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.SearchResult

interface JokesRepository {

    suspend fun getCategories(): List<String>

    suspend fun searchJokes(query: String): SearchResult

    suspend fun getRandomJoke(category: String?): Joke
}