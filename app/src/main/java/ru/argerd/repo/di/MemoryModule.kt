package ru.argerd.repo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.argerd.repo.model.database.AppDatabase
import ru.argerd.repo.model.database.CategoryDao
import ru.argerd.repo.model.database.EventsDao
import ru.argerd.repo.utils.BitmapWorking
import ru.argerd.repo.utils.Parser
import ru.argerd.repo.utils.SharedPreferencesManager
import javax.inject.Singleton

@Module
class MemoryModule {

    @Singleton
    @Provides
    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database")
                .build()
    }

    @Provides
    fun getCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao
    }

    @Provides
    fun getEventsDao(database: AppDatabase): EventsDao {
        return database.eventDao
    }

    @Provides
    fun getParser(context: Context): Parser {
        return Parser(context)
    }

    @Provides
    fun getBitmapWorking(context: Context): BitmapWorking {
        return BitmapWorking(context)
    }

    @Provides
    fun getSharedPreferences(context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }
}