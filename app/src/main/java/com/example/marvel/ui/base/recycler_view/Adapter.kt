package com.example.marvel.ui.base.recycler_view

import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.utils.ItemDiffUtil

class Adapter<T : ViewTyped>(holderFactory: HolderFactory) : BaseAdapter<T>(holderFactory) {
    private val localItems: MutableList<T> = mutableListOf()

    override var items: List<T>
        get() = localItems
        set(newItems) {
            val itemDiffUtilCallback = ItemDiffUtil(localItems, newItems)
            val itemDiffResult = DiffUtil.calculateDiff(itemDiffUtilCallback, true)
            localItems.clear()
            localItems.addAll(newItems)
            itemDiffResult.dispatchUpdatesTo(this)
        }

}