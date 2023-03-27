package com.example.jokesapp.data.repository

import com.example.jokesapp.data.remote.dto.JokeDto
import com.example.jokesapp.data.remote.dto.SearchResultDto
import com.example.jokesapp.data.remote.JokesApi
import com.example.jokesapp.data.remote.dto.toJoke
import com.example.jokesapp.data.remote.dto.toSearchResult
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.model.SearchResult
import com.example.jokesapp.domain.repository.JokesRepository
import javax.inject.Inject

class JokesRepositoryImpl @Inject constructor(private val api: JokesApi): JokesRepository {

    override suspend fun getCategories(): List<String> = api.getJokeCategories()

    override suspend fun searchJokes(query: String): SearchResult = api.searchJokes(query).toSearchResult()

    override suspend fun getRandomJoke(category: String?): Joke = api.getRandomJoke(category).toJoke()
}