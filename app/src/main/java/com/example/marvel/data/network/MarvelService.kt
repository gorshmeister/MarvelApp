package com.example.marvel.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {
    @GET("characters")
    suspend fun getCharacters(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int = 20
    ): DataCharactersResponse

    @GET("characters/{characterId}/comics")
    suspend fun getCharacterComics(@Path("characterId") id: Int): DataCharacterComicsResponse
}