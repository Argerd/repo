package ru.argerd.repo.newsFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.LocalDate;

import ru.argerd.repo.R;

public class NewsFragment extends Fragment {
    private static final String TAG = NewsFragment.class.getName();

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.news_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        int[] photos = {R.drawable.news_1, R.drawable.news_2};
        String[] titles = {"Спонсоры отремонтируют школу-интернат",
                "Спонсоры отремонтируют школу-интернат"};
        String[] newsContent = {"Дубовская школа-интернат для детей с ограниченными возможностями " +
                "здоровья стала первой в области блаблбалбалабалабалаблабалабалабалба",
                "Дубовская школа-интернат для детей с ограниченными возможностями " +
                        "здоровья стала первой в области блаблбалбалабалабалаблабалабалабалба"};

        recyclerView.setAdapter(new NewsAdapter(2, getContext(), photos, titles,
                newsContent));

        return view;
    }
}
