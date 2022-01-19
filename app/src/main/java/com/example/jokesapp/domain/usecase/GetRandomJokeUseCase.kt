package com.example.jokesapp.domain.usecase

import com.example.jokesapp.common.BaseResult
import com.example.jokesapp.data.dto.toJoke
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke(category: String): Flow<BaseResult<Joke>> = flow {
        try {
            emit(BaseResult.Loading<Joke>())
            val joke: Joke = repository.getRandomJoke(category).toJoke()
            emit(BaseResult.Success<Joke>(joke))
        }catch (e: Exception){
            emit(BaseResult.Error<Joke>(e.localizedMessage ?: "An error occurred."))
        }
    }
}