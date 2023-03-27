package com.example.jokesapp.domain.usecase

import com.example.jokesapp.common.Resource
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke(): Flow<Resource<List<String>>> = flow {
        try {
            emit(Resource.Loading)
            val categoriesList = repository.getCategories()
            emit(Resource.Success<List<String>>(categoriesList))
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }
}