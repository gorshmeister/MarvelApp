package com.example.marvel.data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataCharactersResponse(
    @SerialName("data") val data: ResultsCharactersResponse
)

@Serializable
data class ResultsCharactersResponse(
    @SerialName("results") val results: List<CharacterResponse> = emptyList()
)

@Serializable
data class CharacterResponse(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("description") val description: String? = null,
    @SerialName("thumbnail") val thumbnail: Thumbnail? = null,
)

@Serializable
data class Thumbnail(
    @SerialName("path") val path: String? = null
)
