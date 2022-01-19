package com.example.jokesapp.common

sealed class BaseResult<T>{
    data class Success<T>(val data: T): BaseResult<T>()
    data class Error<T>(val message: String): BaseResult<T>()
    class Loading<T> : BaseResult<T>()
}
