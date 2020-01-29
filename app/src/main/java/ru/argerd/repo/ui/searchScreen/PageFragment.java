package ru.argerd.repo.ui.searchScreen;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.widget.RxTextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.argerd.repo.R;
import ru.argerd.repo.presenters.PagePresenter;
import ru.argerd.repo.views.PageView;

public class PageFragment extends MvpAppCompatFragment implements PageView {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG = PageFragment.class.getName();

    private RecyclerView listOfItems;
    private ListAdapter adapter;
    private ScrollView emptyContent;
    private Group groupResults;

    private EditText searchText;

    @InjectPresenter
    PagePresenter presenter;

    private byte type;

    static PageFragment newInstance(byte type) {
        Bundle bundle = new Bundle();
        bundle.putByte(ARG_PAGE, type);

        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(bundle);
        return pageFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = getArguments().getByte(ARG_PAGE);
        }
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, null);

        listOfItems = view.findViewById(R.id.list_with_results);
        adapter = new ListAdapter(new ArrayList<>(), getContext());
        listOfItems.setAdapter(adapter);
        listOfItems.setLayoutManager(new LinearLayoutManager(getContext()));

        emptyContent = view.findViewById(R.id.emptyContent);

        groupResults = getActivity().findViewById(R.id.results);

        if (type == 0) {
            searchText = getActivity().findViewById(R.id.searchView)
                    .findViewById(R.id.searchEditText);
            presenter.onSearch(RxTextView.textChanges(searchText)
                    .skipInitialValue());
        } else {
            ArrayList<String> example = new ArrayList<>();
            example.add("3");
            example.add("4");
            adapter = new ListAdapter(example, getContext());
            listOfItems.setAdapter(adapter);
            emptyContent.setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void skipListOfResults() {
        emptyContent.setVisibility(View.VISIBLE);
        listOfItems.setVisibility(View.GONE);
        groupResults.setVisibility(View.GONE);
    }

    @Override
    public void showListOfResults(@NotNull List<String> list) {
        groupResults.setVisibility(View.VISIBLE);
        Log.d(TAG, "update list");
        adapter.setItems(list);
        adapter.notifyDataSetChanged();
        listOfItems.setVisibility(View.VISIBLE);
        groupResults.setVisibility(View.VISIBLE);
        emptyContent.setVisibility(View.GONE);
    }
}
