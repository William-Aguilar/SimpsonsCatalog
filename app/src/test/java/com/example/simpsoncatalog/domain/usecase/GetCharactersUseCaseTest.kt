package com.example.simpsoncatalog.domain.usecase

import com.example.simpsoncatalog.domain.model.SimpsonCharacter
import com.example.simpsoncatalog.domain.repository.CharactersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharactersUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: CharactersRepository

    lateinit var getCharacters: GetCharactersUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getCharacters = GetCharactersUseCase(repository)
    }

    @Test
    fun `when repository return an characters list then show up them`() = runBlocking {
        // Given
        val expectedCharacters = listOf(
            SimpsonCharacter(
                id = 1,
                age = 39,
                name = "Homer Simpson",
                gender = "male",
                occupation = "Safety Inspector",
                portrait_path = "/character/1.webp",
                phrases = listOf("D'oh!", "Mmm~ Donuts"),
                status = "Alive"
            ),
            SimpsonCharacter(
                id = 2,
                age = 36,
                name = "Marge Simpson",
                gender = "female",
                occupation = "Housewife",
                portrait_path = "/character/2.webp",
                phrases = listOf("Hmm~", "Mmm~"),
                status = "Alive"
            )
        )
        coEvery { repository.getCharacters() } returns expectedCharacters
        // When
        val result = getCharacters()
        // Then
        assertEquals(expectedCharacters, result)
    }
}