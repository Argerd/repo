package ru.argerd.repo.searchScreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import ru.argerd.repo.R;

public class SearchFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmen_search, container, false);

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();

        ViewPager viewPager = view.findViewById(R.id.fragment_pager);
        viewPager.setAdapter(new PagerAdapter(getActivity().getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));
        TabLayout tabLayout = view.findViewById(R.id.fragment_tab);
        tabLayout.setupWithViewPager(viewPager);

        TextView searchResults = view.findViewById(R.id.search_results);
        TextView keywords = view.findViewById(R.id.keywords);
        keywords.append(" мастер-класс, помощь");
        searchResults.append(" 109 мероприятий");

        return view;
    }
}
