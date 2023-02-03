package com.example.marvel.ui.character_info

import com.example.marvel.R
import com.example.marvel.data.network.CharacterComicsResponse
import com.example.marvel.ui.base.recycler_view.ViewTyped

data class CharacterComicsUI(
    override val id: Int,
    val title: String,
    val description: String,
    val image: String,
    override val viewType: Int = R.layout.comics_item
): ViewTyped

fun CharacterComicsResponse.toUI(): CharacterComicsUI {
    return CharacterComicsUI(
        id = id!!,
        title = title.orEmpty(),
        description = "\t" + description.orEmpty(),
        image = thumbnail?.path.orEmpty() + ".jpg"
    )
}
