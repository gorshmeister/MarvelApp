package com.example.marvel.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.ui.base.recycler_view.ViewTyped

class ItemDiffUtil(
    private val oldList: List<ViewTyped>,
    private val newList: List<ViewTyped>
) : DiffUtil.Callback() {


    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem
    }

}