package ru.argerd.repo.categoriesOfHelpScreen;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Decorator extends RecyclerView.ItemDecoration {
    private int margin;

    Decorator(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                               @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.top = 0;
        outRect.bottom = margin;
        if (parent.getChildLayoutPosition(view) % 2 == 0) {
            outRect.left = margin;
            outRect.right = margin / 2;
        } else {
            outRect.left = margin / 2;
            outRect.right = margin;
        }
    }
}
