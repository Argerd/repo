package ru.argerd.repo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.argerd.repo.data.database.dao.CategoryDao
import ru.argerd.repo.data.database.dao.EventsDao
import ru.argerd.repo.data.model.Category
import ru.argerd.repo.data.model.Event

@Database(entities = [Category::class, Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val eventDao: EventsDao
}