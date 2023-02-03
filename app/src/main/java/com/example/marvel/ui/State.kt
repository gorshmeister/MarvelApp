package com.example.marvel.ui

import com.example.marvel.ui.base.recycler_view.ViewTyped

sealed class State {
    object Loading : State()
    object Error : State()
    data class Result(val items: List<ViewTyped>) : State()
}
