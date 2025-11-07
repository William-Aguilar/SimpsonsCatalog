package com.example.simpsoncatalog.data.response

import com.example.simpsoncatalog.domain.entity.SimpsonCharacter
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SimpsonChResponse(
    val id: Int,
    val age: Int,
    val name: String,
    val gender: String,
    val occupation: String,
    @SerialName("portrait_path")
    val portraitPath: String,
    val phrases: List<String>,
    val status: String
)

fun SimpsonChResponse.toDomain(): SimpsonCharacter{
    return SimpsonCharacter(
        id = id,
        age = age,
        name = name,
        gender = gender,
        occupation = occupation,
        portraitPath = portraitPath,
        phrases = phrases,
        status = status
    )
}