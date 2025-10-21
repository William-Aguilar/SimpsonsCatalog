package com.example.simpsoncatalog.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse<T>(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("prev")
    val prev: String?,
    @SerialName("pages")
    val pages: Int,
    @SerialName("results")
    val results: List<T>
)
