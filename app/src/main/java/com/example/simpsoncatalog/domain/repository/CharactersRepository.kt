package com.example.simpsoncatalog.domain.repository

import com.example.simpsoncatalog.domain.model.SimpsonCharacter

interface CharactersRepository {
    suspend fun getCharacters(): List<SimpsonCharacter>
    suspend fun getCharactersFromDB(): List<SimpsonCharacter>
}
