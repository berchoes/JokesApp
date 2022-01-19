package com.example.jokesapp.domain.usecase

import com.example.jokesapp.common.BaseResult
import com.example.jokesapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(private val repository: JokesRepository) {

    operator fun invoke() :Flow<BaseResult<List<String>>> = flow {
        try {
            emit(BaseResult.Loading<List<String>>())
            val categoriesList = repository.getCategories()
            emit(BaseResult.Success<List<String>>(categoriesList))
        }catch (e: Exception){
            emit(BaseResult.Error<List<String>>(e.localizedMessage ?: "An unexpected error occurred."))
        }
    }

}