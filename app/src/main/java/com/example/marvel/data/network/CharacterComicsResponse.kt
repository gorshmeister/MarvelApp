package com.example.marvel.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCharacterComicsResponse(
    @SerialName("data") val data: ResultsCharacterComicsResponse
)

@Serializable
data class ResultsCharacterComicsResponse(
    @SerialName("results") val results: List<CharacterComicsResponse> = emptyList()
)

@Serializable
data class CharacterComicsResponse(
    @SerialName("id") val id: Int? = null,
    @SerialName("title") val title: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("thumbnail") val thumbnail: Thumbnail? = null,
)