package ru.argerd.repo.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NewsDecorator(
        private val margin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        if (parent.getChildLayoutPosition(view) == state.itemCount - 1) {
            outRect.bottom = margin
        }
    }
}