package ru.argerd.repo.model

import androidx.room.TypeConverter

class EventConverter {

    @TypeConverter
    fun fromPhotos(photos: List<String>): String {
        return photos.joinToString()
    }

    @TypeConverter
    fun toPhotos(data: String): List<String> {
        return (data.split(",").map { it.trim() }).toList()
    }
}