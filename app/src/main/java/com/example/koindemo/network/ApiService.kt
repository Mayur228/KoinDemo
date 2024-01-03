package com.example.koindemo.network

import com.example.koindemo.model.MarvelResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("marvel/")
    suspend fun getMarvelCharacters(): List<MarvelResponse>
}