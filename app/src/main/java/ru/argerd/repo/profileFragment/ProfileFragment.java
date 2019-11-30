package ru.argerd.repo.profileFragment;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.Objects;

import ru.argerd.repo.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = ProfileFragment.class.toString();
    private static final int REQUEST_PHOTO = 11;

    private AdapterFriends adapter;
    private RecyclerView recyclerFriends;
    private ImageView photoProfile;
    private Resources resources;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        Toolbar toolbar = view.findViewById(R.id.edit_toolbar_profile);
        toolbar.inflateMenu(R.menu.profile_toolbar_menu);
        toolbar.setOnMenuItemClickListener((item) -> {
            Log.d(TAG, "Menu in toolbar was pressed");
            return false;
        });

        int[] photos = {R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3};
        String[] names = {"Виктор Кузнецов", "Евгений Александров", "Дмитрий Валерьевич"};

        adapter = new AdapterFriends(photos, names, getActivity());
        recyclerFriends = view.findViewById(R.id.recycler_friends);
        recyclerFriends.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerFriends.setAdapter(adapter);

        photoProfile = view.findViewById(R.id.photo_profile);
        photoProfile.setOnClickListener(this);
        resources = getResources();
        File file = new File(getContext().getFilesDir() + "/" +
                resources.getString(R.string.file_name_for_profile));
        if (file.exists()) {
            photoProfile.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_profile:
                Log.d(TAG, "photoProfileClicked");
                DialogFragment dialogFragment = new PhotoOfProfileDialogFragment();
                dialogFragment.setTargetFragment(this, REQUEST_PHOTO);
                if (getFragmentManager() != null)
                    dialogFragment.show(getFragmentManager(), dialogFragment.getClass().getName());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoOfProfileDialogFragment.REQUEST_PHOTO_CAMERA:
                    Log.d(TAG, "after camera");
                    photoProfile.setImageBitmap(BitmapFactory.decodeFile(
                            Objects.requireNonNull(getContext()).getFilesDir() + "/" +
                                    resources.getString(R.string.file_name_for_profile)));
                    break;
                case REQUEST_PHOTO:
                    photoProfile.setImageResource(R.drawable.image_man);
                    break;
            }
        }
    }
}
