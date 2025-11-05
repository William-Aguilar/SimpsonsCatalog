package com.example.simpsoncatalog.data.datasource.api

sealed class NetworkResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val apiError: ApiError) : NetworkResult<Nothing>()
}