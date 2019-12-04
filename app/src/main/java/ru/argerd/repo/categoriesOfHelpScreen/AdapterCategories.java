package ru.argerd.repo.categoriesOfHelpScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

class AdapterCategories extends RecyclerView.Adapter {
    private Context context;
    private int[] images;
    private String[] namesCategories;

    AdapterCategories(Context context, int[] images, String[] namesCategories) {
        this.context = context;
        this.images = images;
        this.namesCategories = namesCategories;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_help, parent, false);
        return new CategoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CategoriesHolder) {
            ((CategoriesHolder) holder).bind(images[position], namesCategories[position], position);
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    private class CategoriesHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView nameCategory;

        CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.help_category_image);
            this.nameCategory = itemView.findViewById(R.id.help_category_text);
        }

        void bind(int image, String nameCategory, int position) {
            this.image.setImageResource(image);
            this.nameCategory.setText(nameCategory);
        }
    }
}
