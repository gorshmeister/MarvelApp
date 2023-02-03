package com.example.marvel.ui.characters

import com.example.marvel.R
import com.example.marvel.data.network.CharacterResponse
import com.example.marvel.ui.base.recycler_view.ViewTyped

data class CharacterUI(
    override val id: Int,
    val name: String,
    val description: String,
    val image: String,
    override val viewType: Int = R.layout.characters_item
): ViewTyped

fun CharacterResponse.toUI(): CharacterUI {
    return CharacterUI(
        id = id!!,
        name = name.orEmpty(),
        description = "\t" + description.orEmpty(),
        image = thumbnail?.path.orEmpty() + ".jpg"
    )
}
