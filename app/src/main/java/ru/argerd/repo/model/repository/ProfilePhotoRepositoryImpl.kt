package ru.argerd.repo.model.repository

import android.net.Uri
import ru.argerd.repo.utils.BitmapWorking

class ProfilePhotoRepositoryImpl : ProfilePhotoRepository {
    private val bitmapWorking = BitmapWorking()

    override fun saveProfilePhoto(uri: Uri): Boolean {
        return bitmapWorking.saveBitmapToFileFromGallery(uri)
    }
}