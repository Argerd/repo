package ru.argerd.repo.categoriesOfHelpScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.argerd.repo.R;
import ru.argerd.repo.model.Category;

class AdapterCategories extends RecyclerView.Adapter {
    private Picasso picasso = Picasso.get();
    private Context context;
    private List<Category> listOfCategories;

    AdapterCategories(Context context) {
        this.context = context;
    }

    void setListOfCategories(List<Category> listOfCategories) {
        this.listOfCategories = listOfCategories;
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
            ((CategoriesHolder) holder).bind(listOfCategories.get(position).getImage(),
                    listOfCategories.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return listOfCategories.size();
    }

    private class CategoriesHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView nameCategory;

        CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.help_category_image);
            this.nameCategory = itemView.findViewById(R.id.help_category_text);
        }

        void bind(String image, String nameCategory) {
            picasso.load(image).into(this.image);
            this.nameCategory.setText(nameCategory);
        }
    }
}
