package com.example.simpsoncatalog.domain.entity

data class SimpsonCharacter(
    val id: Int,
    val age: Int,
    val name: String,
    val gender: String,
    val occupation: String,
    val portraitPath: String,
    val phrases: List<String>,
    val status: String
)
