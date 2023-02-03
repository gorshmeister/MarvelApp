package com.example.marvel.ui.base.recycler_view

interface ViewTyped {
    val viewType: Int
        get() = error("provide viewType $this")
    val id: Int
        get() = error("provide id $this")
}
