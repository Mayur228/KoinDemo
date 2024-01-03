package com.example.koindemo.repository

import android.util.Log
import com.example.koindemo.network.ApiService
import com.example.koindemo.constants.Result
import com.example.koindemo.marvel.MarvelCharacter

interface MarvelRepository {
    suspend fun getMarvelCharacters(): Result<List<MarvelCharacter>>
}

class MarvelRepositoryImpl(private val marvelApiService: ApiService) : MarvelRepository {
    override suspend fun getMarvelCharacters(): Result<List<MarvelCharacter>> {
        return try {
            val response = marvelApiService.getMarvelCharacters()

            Result.Success(response.map { result ->
                MarvelCharacter(
                    name = result.name,
                    description = result.bio,
                    imageUrl = result.imageurl
                )
            })
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
            Result.Error(e)
        }
    }

}