package ru.argerd.repo.di

import dagger.Module
import dagger.Provides
import ru.argerd.repo.model.interactors.*
import ru.argerd.repo.model.repository.CategoriesRepository
import ru.argerd.repo.model.repository.EventsRepository
import ru.argerd.repo.model.repository.ProfilePhotoRepository

@Module
class InteractorsModule {

    @Provides
    fun getCategoriesScreenInteractor(repository: CategoriesRepository)
            : CategoriesScreenInteractor {
        return CategoriesScreenInteractorImpl(repository)
    }

    @Provides
    fun getEventsInteractor(repository: EventsRepository): EventsInteractor {
        return EventsInteractorImpl(repository)
    }

    @Provides
    fun getProfilePhotoInteractor(repository: ProfilePhotoRepository): ProfilePhotoInteractor {
        return ProfilePhotoInteractorImpl(repository)
    }
}