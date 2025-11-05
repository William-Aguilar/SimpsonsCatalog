package com.example.simpsoncatalog.data.datasource.api

import kotlinx.serialization.Serializable

@Serializable
data class ApiError(
    val code: Int? = null,
    val message: String? = null
)