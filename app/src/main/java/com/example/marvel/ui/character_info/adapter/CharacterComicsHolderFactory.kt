package com.example.marvel.ui.character_info.adapter

import android.view.View
import com.example.marvel.R
import com.example.marvel.ui.base.recycler_view.BaseViewHolder
import com.example.marvel.ui.base.recycler_view.HolderFactory
import com.example.marvel.ui.character_info.CharacterComicsUI

class CharacterComicsHolderFactory(
    private val onComicsClick: (comicsUI: CharacterComicsUI) -> Unit,
) : HolderFactory() {
    override fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>? {
        return when (viewType) {
            R.layout.comics_item -> CharacterComicsViewHolder(view, onComicsClick)
            else -> null
        }
    }
}

