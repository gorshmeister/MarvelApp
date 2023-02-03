package com.example.marvel.ui.character_info

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.bumptech.glide.Glide
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterInfoBinding
import com.example.marvel.ui.base.ViewModelFactory
import com.example.marvel.ui.base.recycler_view.Adapter
import com.example.marvel.ui.base.recycler_view.ViewTyped
import com.example.marvel.ui.character_info.adapter.CharacterComicsHolderFactory
import com.example.marvel.ui.characters.CharactersFragment.Companion.CHARACTER_DESCRIPTION
import com.example.marvel.ui.characters.CharactersFragment.Companion.CHARACTER_ID
import com.example.marvel.ui.characters.CharactersFragment.Companion.CHARACTER_IMAGE
import com.example.marvel.ui.characters.CharactersFragment.Companion.CHARACTER_NAME
import com.example.marvel.ui.State
import com.example.marvel.utils.Utils.appComponent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CharacterInfoFragment : Fragment(R.layout.fragment_character_info) {
    private val binding: FragmentCharacterInfoBinding by viewBinding()

    @Inject
    lateinit var factory: ViewModelFactory<CharacterInfoViewModel>

    private val viewModel: CharacterInfoViewModel by viewModels { factory }

    private val holderFactory: CharacterComicsHolderFactory = CharacterComicsHolderFactory(::onComicsClick)

    private val adapter = Adapter<ViewTyped>(holderFactory)

    private val characterId: Int by lazy { arguments?.getInt(CHARACTER_ID) ?: 0 }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            toolbar.setNavigationOnClickListener { findNavController().popBackStack() }
            arguments?.let {
                rvComics.adapter = adapter
                Glide.with(view).load(it.getString(CHARACTER_IMAGE).orEmpty()).into(characterImage)
                characterBio.text = it.getString(CHARACTER_DESCRIPTION).orEmpty()
                characterName.text = it.getString(CHARACTER_NAME).orEmpty()
            }
        }

        viewModel.getComics(characterId)
        viewModel.states.onEach(::render).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun render(state: State) {
        with(binding) {
            when (state) {
                State.Error -> {
                    tvError.isVisible = true
                    rvComics.isVisible = false
                    progressBar.isVisible = false
                }
                State.Loading -> {
                    tvError.isVisible = false
                    rvComics.isVisible = false
                    progressBar.isVisible = true
                }
                is State.Result -> {
                    adapter.items = state.items
                    tvError.isVisible = false
                    rvComics.isVisible = true
                    progressBar.isVisible = false
                }
            }
        }
    }

    private fun onComicsClick(comicsUi: CharacterComicsUI) {
        val bundle = bundleOf(
            COMICS_ID to comicsUi.id,
            COMICS_TITLE to comicsUi.title,
            COMICS_DESCRIPTION to comicsUi.description,
            COMICS_IMAGE to comicsUi.image
        )
        findNavController().navigate(R.id.comicsFragment, bundle)
    }


    companion object {
        const val COMICS_ID = "COMICS_ID"
        const val COMICS_TITLE = "COMICS_TITLE"
        const val COMICS_DESCRIPTION = "COMICS_DESCRIPTION"
        const val COMICS_IMAGE = "COMICS_IMAGE"
    }

}