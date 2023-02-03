package com.example.marvel.ui.characters.adapter

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.marvel.databinding.CharactersItemBinding
import com.example.marvel.ui.base.recycler_view.BaseViewHolder
import com.example.marvel.ui.characters.CharacterUI

class CharactersViewHolder(
    view: View,
    private val onCharacterClick: (item: CharacterUI) -> Unit
) : BaseViewHolder<CharacterUI>(view) {
    private val binding: CharactersItemBinding by viewBinding()

    override fun bind(item: CharacterUI) {
        itemView.setOnClickListener {
            onCharacterClick(item)
        }
        with(binding) {
            itemText.text = item.name
            Glide.with(itemView).load(item.image).into(itemImage)
        }
    }
}
