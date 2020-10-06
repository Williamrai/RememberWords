package com.example.alwaysremeber.util

import android.content.res.Resources
import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class EqualSpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildViewHolder(view).adapterPosition
        val itemCount = state.itemCount
        val layoutManger = parent.layoutManager

        setSpacingForDirection(outRect, layoutManger!!, position, itemCount)
    }

    private fun setSpacingForDirection(
        outRect: Rect,
        layoutManager: RecyclerView.LayoutManager,
        position: Int,
        itemCount: Int
    ) {
        val gridLayoutManager = layoutManager as GridLayoutManager
        val cols = position % gridLayoutManager.spanCount
        //val rows = itemCount / cols

        outRect.left = spacing
        outRect.right = spacing
        outRect.top = spacing / 2
        outRect.bottom = spacing / 2

    }

    private fun Int.toDp() : Int = (this/ Resources.getSystem().displayMetrics.density.toInt())
}

