package com.example.marvel.data.repository

import com.example.marvel.data.network.DataCharacterComicsResponse
import com.example.marvel.data.network.DataCharactersResponse

interface IMarvelRepository {

    suspend fun getCharacters(offset: Int): DataCharactersResponse

    suspend fun getCharacterComics(id: Int): DataCharacterComicsResponse
}