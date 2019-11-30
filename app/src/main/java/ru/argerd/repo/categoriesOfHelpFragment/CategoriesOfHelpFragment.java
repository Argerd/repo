package ru.argerd.repo.categoriesOfHelpFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class CategoriesOfHelpFragment extends Fragment {
    private RecyclerView recyclerCategories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, null);

        recyclerCategories = view.findViewById(R.id.recycler_categories);
        recyclerCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));
        int margin = getResources().getDimensionPixelSize(R.dimen.margin_item);
        recyclerCategories.addItemDecoration(new Decorator(margin));


        int[] images = {R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3, R.drawable.ic_4,
                R.drawable.ic_5};
        String[] nameCategories = {"Дети", "Взрослые", "Пожилие", "Животные", "Мероприятия"};
        recyclerCategories.setAdapter(new AdapterCategories(getContext(), images, nameCategories));

        return view;
    }
}