package ru.argerd.repo.domain.interactors.impl

import android.net.Uri
import ru.argerd.repo.domain.interactors.ProfilePhotoInteractor
import ru.argerd.repo.domain.repositories.ProfilePhotoRepository

class ProfilePhotoInteractorImpl(
        private val repository: ProfilePhotoRepository
) : ProfilePhotoInteractor {

    override fun saveProfilePhotoToFile(uri: Uri): Boolean {
        return repository.saveProfilePhoto(uri)
    }
}