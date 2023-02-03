package com.example.marvel.ui.characters.adapter

import android.view.View
import com.example.marvel.R
import com.example.marvel.ui.base.recycler_view.BaseViewHolder
import com.example.marvel.ui.base.recycler_view.HolderFactory
import com.example.marvel.ui.characters.CharacterUI

class CharactersHolderFactory(
    private val onCharacterClick: (characterUI: CharacterUI) -> Unit,
) : HolderFactory() {
    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.characters_item -> CharactersViewHolder(view, onCharacterClick)
            else -> null
        }
    }
}

