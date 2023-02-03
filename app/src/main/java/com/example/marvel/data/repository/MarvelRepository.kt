package com.example.marvel.data.repository

import com.example.marvel.data.network.MarvelService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val api: MarvelService,
    private val executionDispatcher: CoroutineDispatcher
) : IMarvelRepository {

    override suspend fun getCharacters(offset: Int) = withContext(executionDispatcher) {
        api.getCharacters(offset)
    }

    override suspend fun getCharacterComics(id: Int) = withContext(executionDispatcher) {
        api.getCharacterComics(id)
    }

}