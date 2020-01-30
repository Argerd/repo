package ru.argerd.repo.model.repository

import android.net.Uri

interface ProfilePhotoRepository {
    fun saveProfilePhoto(uri: Uri): Boolean
}