package ru.argerd.repo;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends Fragment {
    private static final String TAG = ProfileFragment.class.toString();

    private Toolbar toolbar;
    private AdapterFriends adapter;
    private RecyclerView recyclerFriends;

    static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        toolbar = view.findViewById(R.id.edit_toolbar_profile);
        toolbar.inflateMenu(R.menu.edit_toolbar_menu);
        toolbar.setOnMenuItemClickListener((item) -> {
            Log.d(TAG, "Menu in toolbar was pressed");
            return false;
        });

        //int[] photos = {R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3};
        /*int[] photos = {getResources().getIdentifier("yourpackagename:drawable/" + "avatar_1", null, null),
                getResources().getIdentifier("yourpackagename:drawable/" + "avatar_2", null, null),
                getResources().getIdentifier("yourpackagename:drawable/" + "avatar_3", null, null)};*/
        /*Drawable[] photos = {ActivityCompat.getDrawable(getContext(), R.drawable.avatar_1),
                ActivityCompat.getDrawable(getContext(), R.drawable.avatar_2),
                ActivityCompat.getDrawable(getContext(), R.drawable.avatar_3)};*/

        Resources res = getResources();
        int[] photos = {res.getIdentifier("avatar_1", "drawable", getActivity().getPackageName()),
                res.getIdentifier("avatar_2", "drawable", getActivity().getPackageName()),
                res.getIdentifier("avatar_3", "drawable", getActivity().getPackageName())};
        String[] names = {"Виктор кузнецов", "Евгений Александров", "Дмитрий Валерьевич"};

        adapter = new AdapterFriends(photos, names, getActivity());
        recyclerFriends = view.findViewById(R.id.recycler_friends);
        recyclerFriends.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerFriends.setAdapter(adapter);

        return view;
    }
}
