package ru.argerd.repo.di.modules

import dagger.Module
import dagger.Provides
import ru.argerd.repo.data.database.dao.CategoryDao
import ru.argerd.repo.data.database.dao.EventsDao
import ru.argerd.repo.data.network.NetworkApi
import ru.argerd.repo.data.repositories.*
import ru.argerd.repo.domain.repositories.CategoriesRepository
import ru.argerd.repo.domain.repositories.EventsRepository
import ru.argerd.repo.domain.repositories.ProfilePhotoRepository
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