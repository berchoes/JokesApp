package com.example.jokesapp.domain.usecase

import android.util.Log
import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.model.Joke
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetRandomJokeUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke(category: String? = null): Flow<Resource<Joke>> = flow {
        try {
            emit(Resource.Loading)
            val joke: Joke = repository.getRandomJoke(category)
            emit(Resource.Success(joke))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An error occurred."))
        } catch (e: Exception) {
            Log.e("Error", "Error occurred.")
        }
    }
}