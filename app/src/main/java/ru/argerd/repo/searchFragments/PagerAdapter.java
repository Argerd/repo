package ru.argerd.repo.searchFragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private String[] tabTitles = {"По мероприятиям", "По НКО"};

    PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return PageFragment.newInstance(new String[]{"Благотворительный фонд Алины Ковальчукglbnlkdbnltknbkdrnblk"
                        , "<<Во имя жизни>>",
                        "Благотворительный Фонд В. Потанина",
                        "<<Детские домики>>",
                        "<<Мозаика счастья>>"});
            case 1:
                return PageFragment.newInstance(new String[]{"3", "4"});
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
