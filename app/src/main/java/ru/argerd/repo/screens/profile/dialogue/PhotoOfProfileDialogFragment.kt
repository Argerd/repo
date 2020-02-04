package ru.argerd.repo.screens.profile.dialogue

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatDialogFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import java.io.File

class PhotoOfProfileDialogFragment : MvpAppCompatDialogFragment(), View.OnClickListener, PhotoOfProfileDialogView {

    @InjectPresenter
    lateinit var presenter: PhotoOfDialogFragmentPresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.dialog_fragment_photo_of_profile, null)
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(view)
        val photoChoose = view.findViewById<TextView>(R.id.photo_choose_text)
        photoChoose.setOnClickListener(this)
        val createPhoto = view.findViewById<TextView>(R.id.create_photo_text)
        createPhoto.setOnClickListener(this)
        val deletePhoto = view.findViewById<TextView>(R.id.delete_text)
        deletePhoto.setOnClickListener(this)
        return builder.create()
    }

    override fun onClick(v: View) {
        Log.d(TAG, "Something was clicked")
        when (v.id) {
            R.id.photo_choose_text -> {
                Log.d(TAG, "PhotoChooseClicked")
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                intent.putExtra(MediaStore.EXTRA_OUTPUT, context!!.filesDir.toString() + "/" +
                        resources.getString(R.string.file_name_for_profile))
                targetFragment!!.startActivityForResult(intent, REQUEST_PHOTO_GALLERY)
                dismiss()
            }
            R.id.create_photo_text -> {
                Log.d(TAG, "CreatePhotoClicked")
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (intent.resolveActivity(context!!.packageManager) != null) {
                    val file = File(context!!.filesDir,
                            resources.getString(R.string.file_name_for_profile))
                    val photo = FileProvider.getUriForFile(context!!,
                            resources.getString(R.string.authority), file)
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, photo)
                    targetFragment!!.startActivityForResult(intent, REQUEST_PHOTO_CAMERA)
                    dismiss()
                } else {
                    Log.d(TAG, "Activity not founded")
                    Snackbar.make(v, R.string.not_founded_needed_application,
                            Snackbar.LENGTH_LONG).show()
                }
            }
            R.id.delete_text -> {
                Log.d(TAG, "DeleteClicked")
                val file = File(context!!.filesDir
                        .toString() + "/" + resources.getString(R.string.file_name_for_profile))
                if (file.delete()) {
                    targetFragment!!.onActivityResult(
                            targetRequestCode, Activity.RESULT_OK,
                            Intent().putExtra(TAG_DELETE_PHOTO, true))
                    dismiss()
                } else {
                    Log.d(TAG, "" + R.string.error_delete_file)
                    Snackbar.make(v, R.string.error_delete_file, Snackbar.LENGTH_LONG).show()
                }
            }
        }
    }

    companion object {
        private const val TAG = "DialogFragment"
        const val REQUEST_PHOTO_GALLERY = 2
        const val REQUEST_PHOTO_CAMERA = 1
        private const val TAG_DELETE_PHOTO = "Delete photo"
    }
}