package com.example.koindemo.marvel

data class MarvelData(
    val results: List<MarvelCharacter>
)

data class MarvelCharacter(
    val name: String?,
    val description: String?,
    val imageUrl: String?
    // Add other properties as needed based on the Marvel API response
)