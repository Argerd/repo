package ru.argerd.repo;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.DialogFragment;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PhotoOfProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DialogFragment";
    public static final int REQUEST_PHOTO_CAMERA = 1;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_photo_of_profile, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);

        TextView photoChoose = view.findViewById(R.id.photo_choose_text);
        photoChoose.setOnClickListener(this);

        TextView createPhoto = view.findViewById(R.id.create_photo_text);
        createPhoto.setOnClickListener(this);

        TextView deletePhoto = view.findViewById(R.id.delete_text);
        deletePhoto.setOnClickListener(this);

        return builder.create();
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, "Something was clicked");
        switch (v.getId()) {
            case R.id.photo_choose_text:
                Log.d(TAG, "PhotoChooseClicked");
                break;
            case R.id.create_photo_text:
                Log.d(TAG, "CreatePhotoClicked");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(
                        Objects.requireNonNull(getActivity()).getPackageManager()) != null) {
                    try {
                        File tempFile = File.createTempFile("profile", ".jpg",
                                getActivity().getFilesDir());
                        Uri photo = FileProvider.getUriForFile(getActivity(),
                                "ru.argerd.repo", tempFile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, photo);
                        startActivityForResult(intent, REQUEST_PHOTO_CAMERA);
                    } catch (IOException e) {
                        Log.d(TAG, "Error", e);
                    }
                } else {
                    Log.d(TAG, "Activity not founded");
                }
                break;
            case R.id.delete_text:
                Log.d(TAG, "DeleteClicked");
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Objects.requireNonNull(getTargetFragment()).onActivityResult(requestCode, resultCode, data);
    }
}
