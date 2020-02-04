package ru.argerd.repo.data.repositories

import android.net.Uri
import ru.argerd.repo.domain.repositories.ProfilePhotoRepository
import ru.argerd.repo.utils.BitmapWorking

class ProfilePhotoRepositoryImpl(
        private val bitmapWorking: BitmapWorking
) : ProfilePhotoRepository {

    override fun saveProfilePhoto(uri: Uri): Boolean {
        return bitmapWorking.saveBitmapToFileFromGallery(uri)
    }
}