package ru.argerd.repo.ui.profileScreen;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import ru.argerd.repo.BitmapWorking;
import ru.argerd.repo.R;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = ProfileFragment.class.toString();
    private static final int REQUEST_PHOTO = 11;

    private BitmapWorking bitmapWorking = new BitmapWorking();

    private ImageView photoProfile;
    private Resources resources;
    private View view;

    private Picasso picasso = Picasso.get();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);

        int[] photos = {R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3};
        String[] names = {"Виктор Кузнецов", "Евгений Александров", "Дмитрий Валерьевич"};

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.profile_toolbar_menu);
        TextView toolbarText = getActivity().findViewById(R.id.toolbar_text);
        toolbarText.setText(R.string.profile);
        toolbar.setOnMenuItemClickListener(view -> true);

        AdapterFriends adapter = new AdapterFriends(photos, names, getActivity());
        RecyclerView recyclerFriends = view.findViewById(R.id.recycler_friends);
        recyclerFriends.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerFriends.setAdapter(adapter);

        photoProfile = view.findViewById(R.id.photo_profile);
        photoProfile.setOnClickListener(this);

        resources = getResources();

        setPhotoProfile();

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_profile:
                Log.d(TAG, "photoProfileClicked");
                DialogFragment dialogFragment = new PhotoOfProfileDialogFragment();
                dialogFragment.setTargetFragment(this, REQUEST_PHOTO);
                dialogFragment.show(getParentFragmentManager(),
                        dialogFragment.getClass().getName());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PhotoOfProfileDialogFragment.REQUEST_PHOTO_CAMERA:
                    Log.d(TAG, "after camera");
                    setPhotoProfile();
                    break;
                case PhotoOfProfileDialogFragment.REQUEST_PHOTO_GALLERY:
                    if (data.getData() != null) {
                        Log.d(TAG, "DataPath " + data.getData().getPath());
                        if (bitmapWorking.saveBitmapToFileFromGallery(data.getData())) {
                            setPhotoProfile();
                        } else {
                            showMessageAndSetPhotoIfError();
                        }
                    }
                    break;
                case REQUEST_PHOTO:
                    picasso.load(R.drawable.image_man).into(photoProfile);
                    break;
            }
        }
    }

    private void setPhotoProfile() {
        picasso.load("file://" + getContext().getFilesDir() + "/"
                + resources.getString(R.string.file_name_for_profile))
                .placeholder(R.drawable.image_man)
                .into(photoProfile);
    }

    private void showMessageAndSetPhotoIfError() {
        Snackbar.make(view,
                R.string.error_write_to_file, Snackbar.LENGTH_LONG).show();
        setPhotoProfile();
    }
}