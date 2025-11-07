package com.example.simpsoncatalog.domain.usecase

import androidx.paging.PagingData
import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import com.example.simpsoncatalog.domain.repository.CharactersRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    operator fun invoke(): Flow<PagingData<SimpsonCharacter>> {
        return repository.getCharacters()
    }
}
