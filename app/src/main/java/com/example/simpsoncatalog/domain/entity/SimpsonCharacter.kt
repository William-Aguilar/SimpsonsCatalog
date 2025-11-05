package com.example.simpsoncatalog.domain.entity

data class SimpsonCharacter(
    val id: Int,
    val age: Int,
    val name: String,
    val gender: String,
    val occupation: String,
    val portrait_path: String,
    val phrases: List<String>,
    val status: String
)
