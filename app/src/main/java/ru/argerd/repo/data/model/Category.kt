package ru.argerd.repo.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Category(
        @PrimaryKey
        var id: Int?,
        var name: String?,
        var image: String?,
        var name_en: String?) : Parcelable