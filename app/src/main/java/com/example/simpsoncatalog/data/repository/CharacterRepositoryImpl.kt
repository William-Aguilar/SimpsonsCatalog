package com.example.simpsoncatalog.data.repository

import com.example.simpsoncatalog.data.datasource.api.ApiServices
import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import com.example.simpsoncatalog.domain.repository.CharactersRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(val api: ApiServices) : CharactersRepository {
    override suspend fun getCharacters(): List<SimpsonCharacter> {
        TODO("Not yet implemented")
    }

    override suspend fun getCharactersFromDB(): List<SimpsonCharacter> {
        TODO("Not yet implemented")
    }
}