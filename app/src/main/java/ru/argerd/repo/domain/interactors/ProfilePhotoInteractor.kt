package ru.argerd.repo.domain.interactors

import android.net.Uri

interface ProfilePhotoInteractor {
    fun saveProfilePhotoToFile(uri: Uri): Boolean
}