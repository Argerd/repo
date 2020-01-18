package ru.argerd.repo.searchScreen;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jakewharton.rxbinding3.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import ru.argerd.repo.R;
import ru.argerd.repo.model.Event;
import ru.argerd.repo.parsing.Parser;

public class PageFragment extends Fragment {
    private static final String ARG_PAGE = "ARG_PAGE";
    private static final String TAG = PageFragment.class.getName();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private RecyclerView listOfItems;
    private ListAdapter adapter;

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

        ScrollView emptyContent = view.findViewById(R.id.emptyContent);

        Group groupResults = getActivity().findViewById(R.id.results);
        if (type == 0) {
            EditText searchText = getActivity().findViewById(R.id.searchView)
                    .findViewById(R.id.searchEditText);
            compositeDisposable.add(RxTextView.textChanges(searchText)
                    .skipInitialValue()
                    .debounce(500, TimeUnit.MILLISECONDS)
                    .switchMap(seq -> {
                        String input = seq.toString();
                        Log.d(TAG, "" + input.length());
                        return Observable.just(getSearchResult(input));
                    })
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(result -> {
                        Log.d(TAG, "text is changed");
                        if (result.size() == 0) {
                            emptyContent.setVisibility(View.VISIBLE);
                            listOfItems.setVisibility(View.GONE);
                            groupResults.setVisibility(View.GONE);
                        } else {
                            groupResults.setVisibility(View.VISIBLE);
                            Log.d(TAG, "update list");
                            adapter.setItems(result);
                            adapter.notifyDataSetChanged();
                            listOfItems.setVisibility(View.VISIBLE);
                            groupResults.setVisibility(View.VISIBLE);
                            emptyContent.setVisibility(View.GONE);
                        }
                    }, error -> Log.d(TAG, error.toString())));
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

    private ArrayList<String> getSearchResult(String input) {
        Log.d(TAG, "getSearch");
        List<Event> events = new Parser().getEvents(getContext());
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < events.size(); i++) {
            if (events.get(i).getTitle().toLowerCase().contains(input)) {
                result.add(events.get(i).getTitle());
            }
        }
        return result;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        Log.d(TAG, "onDestroy");
    }
}
