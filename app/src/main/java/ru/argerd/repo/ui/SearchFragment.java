package ru.argerd.repo.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import ru.argerd.repo.R;
import ru.argerd.repo.adapters.PagerAdapter;
import ru.argerd.repo.presenters.SearchPresenter;
import ru.argerd.repo.views.SearchView;

public class SearchFragment extends MvpAppCompatFragment implements SearchView {
    @InjectPresenter
    SearchPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_search, container, false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();

        ViewPager viewPager = view.findViewById(R.id.fragment_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getChildFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.fragment_tab);
        tabLayout.setupWithViewPager(viewPager);

        TextView searchResults = view.findViewById(R.id.search_results);
        TextView keywords = view.findViewById(R.id.keywords);
        keywords.append(" мастер-класс, помощь");
        searchResults.append(" 109 мероприятий");

        return view;
    }
}
