package com.example.jokesapp.domain.usecase

import com.example.jokesapp.common.Resource
import com.example.jokesapp.data.remote.dto.toJoke
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class SearchJokesUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke(query: String): Flow<Resource<List<Joke>>> = flow {
        try {
            emit(Resource.Loading)
            val resultList: List<Joke> =
                repository.searchJokes(query).result.take(400).map { it }
            emit(Resource.Success<List<Joke>>(resultList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}