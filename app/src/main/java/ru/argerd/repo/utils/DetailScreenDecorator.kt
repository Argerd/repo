package ru.argerd.repo.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class DetailScreenDecorator(
        private val marginFirst: Float,
        private val marginTopBottom: Float,
        private val margin: Float
) : ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.top = marginTopBottom.toInt()
        outRect.bottom = marginTopBottom.toInt()
        if (parent.getChildLayoutPosition(view) == 0)
            outRect.left = marginFirst.toInt()
        else if (parent.getChildLayoutPosition(view) != state.itemCount - 1) {
            outRect.left = margin.toInt()
        }
    }

}