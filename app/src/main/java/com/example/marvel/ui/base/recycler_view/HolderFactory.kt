package com.example.marvel.ui.base.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class HolderFactory : (ViewGroup, Int) -> BaseViewHolder<ViewTyped> {

    companion object {
        const val UNKNOWN_VIEW_TYPE = "Unknown viewType = "
    }

    abstract fun createViewHolder(view: View, viewType: Int): BaseViewHolder<*>?

    override fun invoke(viewGroup: ViewGroup, viewType: Int): BaseViewHolder<ViewTyped> {
        val view: View = viewGroup.inflate(viewType)

        return checkNotNull(createViewHolder(view, viewType)) {
            UNKNOWN_VIEW_TYPE + viewGroup.resources.getResourceName(viewType)
        } as BaseViewHolder<ViewTyped>
    }
}

fun <T : View> View.inflate(
    layout: Int,
    root: ViewGroup? = this as? ViewGroup,
    attachToRoot: Boolean = false,
): T {
    return LayoutInflater.from(context).inflate(layout, root, attachToRoot) as T
}
