package com.example.simpsoncatalog.data.datasource.api

import com.example.simpsoncatalog.data.response.PaginationResponse
import com.example.simpsoncatalog.data.response.SimpsonChResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("characters")
    suspend fun getCharacters(@Query("page") page: Int): PaginationResponse<SimpsonChResponse>
}