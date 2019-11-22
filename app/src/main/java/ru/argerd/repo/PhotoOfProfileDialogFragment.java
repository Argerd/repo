package ru.argerd.repo;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

public class PhotoOfProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = PhotoOfProfileDialogFragment.class.toString();
    private TextView photoChoose;
    private TextView createPhoto;
    private TextView deletePhoto;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_photo_of_profile, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        photoChoose = view.findViewById(R.id.photo_choose_text);
        photoChoose.setOnClickListener(this);

        createPhoto = view.findViewById(R.id.create_photo_text);
        createPhoto.setOnClickListener(this);

        deletePhoto = view.findViewById(R.id.delete_text);
        deletePhoto.setOnClickListener(this);

        return builder.create();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo_choose_text:
                Log.d(TAG, "PhotoChooseClicked");
                break;
            case R.id.create_photo_text:
                Log.d(TAG, "CreatePhotoClicked");
                break;
            case R.id.delete_text:
                Log.d(TAG, "DeleteClicked");
                break;
        }
    }
}
