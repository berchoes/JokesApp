package com.example.jokesapp.domain.repository

import com.example.jokesapp.data.dto.JokeDto
import com.example.jokesapp.data.dto.SearchResultDto

interface JokesRepository {

    suspend fun getCategories(): List<String>

    suspend fun searchJokes(query: String): SearchResultDto

    suspend fun getRandomJoke(category: String?): JokeDto
}