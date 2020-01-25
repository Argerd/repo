package ru.argerd.repo

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.argerd.repo.model.Category
import ru.argerd.repo.model.CategoryDao
import ru.argerd.repo.model.Event
import ru.argerd.repo.model.EventsDao

@Database(entities = [Category::class, Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val eventDao: EventsDao
}