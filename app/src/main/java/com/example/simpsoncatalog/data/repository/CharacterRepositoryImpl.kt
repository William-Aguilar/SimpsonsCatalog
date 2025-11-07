package com.example.simpsoncatalog.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.simpsoncatalog.data.datasource.CharacterPagingSource
import com.example.simpsoncatalog.data.datasource.api.ApiServices
import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import com.example.simpsoncatalog.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(val api: ApiServices) : CharactersRepository {

    companion object {
        const val PAGE_SIZE = 20
        const val PREFETCH_DISTANCE = 2
    }

    override fun getCharacters(page: Int?): Flow<PagingData<SimpsonCharacter>> {
        return Pager(
            PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = PREFETCH_DISTANCE
            ),
            pagingSourceFactory = {
                CharacterPagingSource(api)
            }
        ).flow
    }

    override suspend fun getCharactersFromDB(): List<SimpsonCharacter> {
        TODO("Not yet implemented")
    }
}