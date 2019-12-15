package ru.argerd.repo.categoriesOfHelpScreen;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class CategoriesOfHelpFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories_of_help, null);

        RecyclerView recyclerCategories = view.findViewById(R.id.recycler_categories);
        recyclerCategories.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();

        TextView toolbarText = getActivity().findViewById(R.id.toolbar_text);
        toolbarText.setText(R.string.help);

        int[] images = {R.drawable.ic_1, R.drawable.ic_2, R.drawable.ic_3, R.drawable.ic_4,
                R.drawable.ic_5};
        String[] nameCategories = {"Дети", "Взрослые", "Пожилые", "Животные", "Мероприятия"};
        recyclerCategories.setAdapter(new AdapterCategories(getContext(), images, nameCategories));

        Resources resources = getResources();
        recyclerCategories.addItemDecoration(new Decorator(
                resources.getDimension(R.dimen.margin_item)));

        return view;
    }
}
