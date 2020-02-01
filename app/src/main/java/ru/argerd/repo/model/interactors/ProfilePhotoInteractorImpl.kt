package ru.argerd.repo.model.interactors

import android.net.Uri
import ru.argerd.repo.model.repository.ProfilePhotoRepository

class ProfilePhotoInteractorImpl(
        private val repository: ProfilePhotoRepository
) : ProfilePhotoInteractor {

    override fun saveProfilePhotoToFile(uri: Uri): Boolean {
        return repository.saveProfilePhoto(uri)
    }
}