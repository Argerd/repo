package ru.argerd.repo.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.argerd.repo.model.pojo.Category
import ru.argerd.repo.model.pojo.Event

@Database(entities = [Category::class, Event::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val categoryDao: CategoryDao
    abstract val eventDao: EventsDao
}