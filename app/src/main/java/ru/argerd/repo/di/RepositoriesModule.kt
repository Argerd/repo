package ru.argerd.repo.di

import dagger.Module
import dagger.Provides
import ru.argerd.repo.model.database.CategoryDao
import ru.argerd.repo.model.database.EventsDao
import ru.argerd.repo.model.repository.*
import ru.argerd.repo.model.retrofit.NetworkApi
import ru.argerd.repo.utils.BitmapWorking
import ru.argerd.repo.utils.Parser

@Module
class RepositoriesModule {

    @Provides
    fun getCategoriesRepository(parser: Parser, database: CategoryDao,
                                api: NetworkApi): CategoriesRepository {
        return CategoriesRepositoryImpl(parser, database, api)
    }

    @Provides
    fun getEventsRepository(parser: Parser, database: EventsDao): EventsRepository {
        return EventsRepositoryImpl(parser, database)
    }

    @Provides
    fun getProfilePhotoRepository(bitmapWorking: BitmapWorking): ProfilePhotoRepository {
        return ProfilePhotoRepositoryImpl(bitmapWorking)
    }
}