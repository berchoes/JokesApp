package com.example.jokesapp.data.remote

import com.example.jokesapp.data.remote.dto.JokeDto
import com.example.jokesapp.data.remote.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface JokesApi {

    @GET("categories")
    suspend fun getJokeCategories(): List<String>

    @GET("random")
    suspend fun getRandomJoke(@Query("category") category: String?): JokeDto

    @GET("search")
    suspend fun searchJokes(@Query("query") query: String): SearchResultDto

}