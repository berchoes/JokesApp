package com.example.jokesapp.domain.usecase

import com.example.jokesapp.common.BaseResult
import com.example.jokesapp.data.dto.toJoke
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class SearchJokesUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke(query: String): Flow<BaseResult<List<Joke>>> = flow {
        try {
            emit(BaseResult.Loading<List<Joke>>())
            val resultList: List<Joke> = repository.searchJokes(query).result.map { it.toJoke() }
            emit(BaseResult.Success<List<Joke>>(resultList))
        } catch (e: Exception) {
            emit(BaseResult.Error<List<Joke>>(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}