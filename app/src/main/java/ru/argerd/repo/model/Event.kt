package ru.argerd.repo.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.android.parcel.Parcelize
import ru.argerd.repo.model.database.EventConverter

@Parcelize
@Entity
@TypeConverters(EventConverter::class)
data class Event(
        @PrimaryKey(autoGenerate = true)
        var superId: Int? = null,
        var id: Int? = null,
        var name: String? = null,
        var endDate: String? = null,
        var createAt: String? = null,
        var nameOfOrganization: String? = null,
        var address: String? = null,
        var phone: String? = null,
        var photos: List<String?>? = null,
        var description: String? = null,
        var category: String? = "0",
        var startDate: String? = null
) : Parcelable