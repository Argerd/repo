package ru.argerd.repo.searchScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.argerd.repo.R;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private String[] namesOfItems;

    private RecyclerView listOfItems;

    static PageFragment newInstance(String[] strings) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(ARG_PAGE, strings);

        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            namesOfItems = getArguments().getStringArray(ARG_PAGE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);

        listOfItems = view.findViewById(R.id.list_with_results);
        listOfItems.setLayoutManager(new LinearLayoutManager(getContext()));
        listOfItems.setAdapter(new ListAdapter(namesOfItems, getContext()));

        return view;
    }
}
