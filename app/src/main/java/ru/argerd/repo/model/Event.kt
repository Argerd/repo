package ru.argerd.repo.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        var id: Int? = null,
        var name: String? = null,
        var endDate: String? = null,
        var createAt: String? = null,
        var nameOfOrganization: String? = null,
        var address: String? = null,
        var phone: String? = null,
        var photos: ArrayList<String?>? = null,
        var description: String? = null,
        var category: String? = "0",
        var startDate: String? = null
) : Parcelable