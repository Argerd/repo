package ru.argerd.repo.model.interactors

import android.net.Uri

interface ProfilePhotoInteractor {
    fun saveProfilePhotoToFile(uri: Uri): Boolean
}