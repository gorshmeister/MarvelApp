package com.example.marvel.ui.character_info.adapter

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.marvel.databinding.CharactersItemBinding
import com.example.marvel.databinding.ComicsItemBinding
import com.example.marvel.ui.base.recycler_view.BaseViewHolder
import com.example.marvel.ui.character_info.CharacterComicsUI
import com.example.marvel.ui.characters.CharacterUI

class CharacterComicsViewHolder(
    view: View,
    private val onComicsClick: (item: CharacterComicsUI) -> Unit
) : BaseViewHolder<CharacterComicsUI>(view) {
    private val binding: ComicsItemBinding by viewBinding()

    override fun bind(item: CharacterComicsUI) {
        itemView.setOnClickListener {
            onComicsClick(item)
        }
        with(binding) {
            itemText.text = item.title
            Glide.with(itemView).load(item.image).into(itemImage)
        }
    }
}
