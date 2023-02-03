package com.example.marvel.ui.characters

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.marvel.R
import com.example.marvel.utils.Utils.appComponent
import com.example.marvel.databinding.FragmentCharactersBinding
import com.example.marvel.ui.State
import com.example.marvel.ui.base.ViewModelFactory
import com.example.marvel.ui.base.recycler_view.Adapter
import com.example.marvel.ui.base.recycler_view.HolderFactory
import com.example.marvel.ui.base.recycler_view.ViewTyped
import com.example.marvel.ui.characters.adapter.CharactersHolderFactory
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CharactersFragment : Fragment(R.layout.fragment_characters) {
    private val binding: FragmentCharactersBinding by viewBinding()

    @Inject
    lateinit var factory: ViewModelFactory<CharactersViewModel>

    private val viewModel: CharactersViewModel by viewModels { factory }

    private val holderFactory: HolderFactory = CharactersHolderFactory(::onCharacterClick)

    private var adapter = Adapter<ViewTyped>(holderFactory)

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycleView()
        viewModel.states.onEach(::render).launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun initRecycleView() {
        with(binding) {
            rvCharacters.adapter = adapter
            val layoutManager = binding.rvCharacters.layoutManager as GridLayoutManager
            binding.rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val position = layoutManager.findLastVisibleItemPosition()
                    val loadingPosition = layoutManager.itemCount - 5
                    val isNotLoading = !binding.progressBar.isVisible
                    if (isNotLoading && position == loadingPosition && dy != ZERO_SCROLL_POSITION) {
                        viewModel.getCharacters()
                    }
                }
            })
        }
    }

    private fun render(state: State) {
        with(binding) {
            when (state) {
                State.Error -> {
                    tvError.isVisible = true
                    rvCharacters.isVisible = false
                    progressBar.isVisible = false
                }
                State.Loading -> {
                    tvError.isVisible = false
                    rvCharacters.isVisible = true
                    progressBar.isVisible = true
                }
                is State.Result -> {
                    adapter.items += state.items
                    tvError.isVisible = false
                    rvCharacters.isVisible = true
                    progressBar.isVisible = false
                }
            }
        }
    }

    private fun onCharacterClick(characterUI: CharacterUI) {
        val bundle = bundleOf(
            CHARACTER_ID to characterUI.id,
            CHARACTER_NAME to characterUI.name,
            CHARACTER_DESCRIPTION to characterUI.description,
            CHARACTER_IMAGE to characterUI.image
        )
        findNavController().navigate(R.id.characterInfo, bundle)
    }

    companion object {
        const val ZERO_SCROLL_POSITION = 0
        const val CHARACTER_ID = "CHARACTER_ID"
        const val CHARACTER_NAME = "CHARACTER_NAME"
        const val CHARACTER_DESCRIPTION = "CHARACTER_DESCRIPTION"
        const val CHARACTER_IMAGE = "CHARACTER_IMAGE"
    }
}