package ru.argerd.repo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        var id: Int? = null,
        var title: String? = null,
        var date: String? = null,
        var nameOfOrganization: String? = null,
        var address: String? = null,
        var phones: List<String?>? = null,
        var photos: List<String?>? = null,
        var content: String? = null,
        var categories: List<Category?>? = null
) : Parcelable