package ru.argerd.repo.profileScreen;

import android.app.Activity;
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

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.util.Objects;

import ru.argerd.repo.R;

public class PhotoOfProfileDialogFragment extends DialogFragment implements View.OnClickListener {
    private static final String TAG = "DialogFragment";
    static final int REQUEST_PHOTO_GALLERY = 2;
    static final int REQUEST_PHOTO_CAMERA = 1;
    private static final String TAG_DELETE_PHOTO = "Delete photo";

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
            case R.id.photo_choose_text: {
                Log.d(TAG, "PhotoChooseClicked");
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Objects.requireNonNull(getContext()).getFilesDir() + "/" +
                                getResources().getString(R.string.file_name_for_profile));
                Objects.requireNonNull(getTargetFragment())
                        .startActivityForResult(intent, REQUEST_PHOTO_GALLERY);
                this.dismiss();
                break;
            }
            case R.id.create_photo_text: {
                Log.d(TAG, "CreatePhotoClicked");
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(
                        Objects.requireNonNull(getContext()).getPackageManager()) != null) {
                    File file = new File(Objects.requireNonNull(getContext()).getFilesDir(),
                            getResources().getString(R.string.file_name_for_profile));
                    Uri photo = FileProvider.getUriForFile(getContext(),
                            getResources().getString(R.string.authority), file);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photo);
                    Objects.requireNonNull(getTargetFragment())
                            .startActivityForResult(intent, REQUEST_PHOTO_CAMERA);
                    this.dismiss();
                } else {
                    Log.d(TAG, "Activity not founded");
                    Snackbar.make(v, R.string.not_founded_needed_application,
                            Snackbar.LENGTH_LONG).show();
                }
                break;
            }
            case R.id.delete_text:
                Log.d(TAG, "DeleteClicked");
                File file = new File(Objects.requireNonNull(getContext()).getFilesDir()
                        + "/" + getResources().getString(R.string.file_name_for_profile));
                if (file.delete()) {
                    Objects.requireNonNull(getTargetFragment()).onActivityResult(
                            getTargetRequestCode(), Activity.RESULT_OK,
                            new Intent().putExtra(TAG_DELETE_PHOTO, true));
                    this.dismiss();
                } else {
                    Log.d(TAG, "" + R.string.error_delete_file);
                    Snackbar.make(v, R.string.error_delete_file, Snackbar.LENGTH_LONG).show();
                }
                break;
        }
    }
}
