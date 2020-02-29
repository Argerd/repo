package ru.argerd.repo.screens.profile

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.argerd.repo.R
import ru.argerd.repo.adapters.AdapterFriends
import ru.argerd.repo.screens.profile.dialogue.PhotoOfProfileDialogFragment
import java.io.File

class ProfileFragment : MvpAppCompatFragment(), View.OnClickListener, ProfileView {
    private lateinit var photoProfile: ImageView

    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        setHasOptionsMenu(true)

        val photos = intArrayOf(R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3)
        val names = arrayOf("Виктор Кузнецов", "Евгений Александров", "Дмитрий Валерьевич")

        val toolbar: Toolbar = activity!!.findViewById(R.id.toolbar)
        toolbar.menu.clear()
        toolbar.inflateMenu(R.menu.profile_toolbar_menu)
        val toolbarText = activity!!.findViewById<TextView>(R.id.toolbar_text)
        toolbarText.setText(R.string.profile)
        toolbar.setOnMenuItemClickListener { true }

        val adapter = AdapterFriends(photos, names, activity!!)
        val recyclerFriends: RecyclerView = view.findViewById(R.id.recycler_friends)
        recyclerFriends.layoutManager = LinearLayoutManager(activity)
        recyclerFriends.adapter = adapter

        photoProfile = view.findViewById(R.id.photo_profile)
        photoProfile.setOnClickListener(this)

        val file = File(context!!.filesDir.toString() + "/" +
                resources.getString(R.string.file_name_for_profile))
        if (file.exists()) {
            setPhotoProfile()
        } else {
            photoProfile.setImageResource(R.drawable.image_man)
        }

        return view
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.photo_profile -> presenter.moveToDialogFragmentForChoose()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PhotoOfProfileDialogFragment.REQUEST_PHOTO_CAMERA -> {
                    Log.d(TAG, "after camera")
                    setPhotoProfile()
                }
                PhotoOfProfileDialogFragment.REQUEST_PHOTO_GALLERY -> if (data!!.data != null) {
                    Log.d(TAG, "DataPath " + data.data!!.path)
                    presenter.onRequestPhotoOfGallery(data.data!!)
                    setPhotoProfile()
                }
                REQUEST_PHOTO -> photoProfile.setImageResource(R.drawable.image_man)
            }
        }
    }

    override fun setPhotoProfile() {
        photoProfile.setImageBitmap(BitmapFactory.decodeFile(
                context!!.filesDir.toString() + "/" +
                        resources.getString(R.string.file_name_for_profile)))
    }

    override fun showMessageAndSetPhotoIfError() {
        Snackbar.make(view!!,
                R.string.error_write_to_file, Snackbar.LENGTH_LONG).show()
        setPhotoProfile()
    }

    override fun moveToChoose() {
        Log.d(TAG, "photoProfileClicked")
        val dialogFragment: DialogFragment = PhotoOfProfileDialogFragment()
        dialogFragment.setTargetFragment(this, REQUEST_PHOTO)
        dialogFragment.show(parentFragmentManager,
                dialogFragment.javaClass.name)
    }

    companion object {
        private val TAG = ProfileFragment::class.java.toString()
        private const val REQUEST_PHOTO = 11
    }
}