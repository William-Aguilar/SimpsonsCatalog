package com.example.simpsoncatalog.domain.usecase

import com.example.simpsoncatalog.domain.model.SimpsonCharacter
import com.example.simpsoncatalog.domain.repository.CharactersRepository

class GetCharactersUseCase (private val repository: CharactersRepository) {
    suspend operator fun invoke(): List<SimpsonCharacter> {
        return repository.getCharacters()
    }
}
