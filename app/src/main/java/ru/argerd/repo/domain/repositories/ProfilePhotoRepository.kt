package ru.argerd.repo.domain.repositories

import android.net.Uri

interface ProfilePhotoRepository {
    fun saveProfilePhoto(uri: Uri): Boolean
}