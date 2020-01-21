package ru.argerd.repo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Category(var id: Int?,
                    var name: String?,
                    var image: String,
                    var name_en: String?) : Parcelable