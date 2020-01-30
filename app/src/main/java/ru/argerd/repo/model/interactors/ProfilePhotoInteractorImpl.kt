package ru.argerd.repo.model.interactors

import android.net.Uri
import ru.argerd.repo.model.repository.ProfilePhotoRepositoryImpl

class ProfilePhotoInteractorImpl : ProfilePhotoInteractor {
    private val repository = ProfilePhotoRepositoryImpl()

    override fun saveProfilePhotoToFile(uri: Uri): Boolean {
        return repository.saveProfilePhoto(uri)
    }
}