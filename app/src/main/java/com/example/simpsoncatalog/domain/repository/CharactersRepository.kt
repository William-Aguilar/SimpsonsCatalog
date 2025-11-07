package com.example.simpsoncatalog.domain.repository

import androidx.paging.PagingData
import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    fun getCharacters(page: Int? = 0): Flow<PagingData<SimpsonCharacter>>
    suspend fun getCharactersFromDB(): List<SimpsonCharacter>
}
