package ru.argerd.repo.ui.detailDescriptionScreen;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class Decorator extends RecyclerView.ItemDecoration {
    private float marginFirst;
    private float marginTopBottom;
    private float margin;

    Decorator(float marginStart, float marginTopBottom, float margin) {
        this.marginFirst = marginStart;
        this.marginTopBottom = marginTopBottom;
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = (int) marginTopBottom;
        outRect.bottom = (int) marginTopBottom;
        if (parent.getChildLayoutPosition(view) == 0) outRect.left = (int) marginFirst;
        else if (parent.getChildLayoutPosition(view) != state.getItemCount() - 1) {
            outRect.left = (int) margin;
        }
    }
}