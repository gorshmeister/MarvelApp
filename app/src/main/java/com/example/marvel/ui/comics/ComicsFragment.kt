package com.example.marvel.ui.comics

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.databinding.FragmentComicsBinding
import com.example.marvel.ui.character_info.CharacterInfoFragment.Companion.COMICS_DESCRIPTION
import com.example.marvel.ui.character_info.CharacterInfoFragment.Companion.COMICS_IMAGE
import com.example.marvel.ui.character_info.CharacterInfoFragment.Companion.COMICS_TITLE


class ComicsFragment : Fragment(R.layout.fragment_comics) {
    private val binding: FragmentComicsBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            arguments?.let {
                Glide.with(view).load(it.getString(COMICS_IMAGE).orEmpty()).into(comicsImage)
                comicsTitle.text = it.getString(COMICS_TITLE).orEmpty()
                comicsDescription.text = it.getString(COMICS_DESCRIPTION).orEmpty()
            }
        }
    }
}