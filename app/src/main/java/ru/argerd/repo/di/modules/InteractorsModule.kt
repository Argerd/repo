package ru.argerd.repo.di.modules

import dagger.Module
import dagger.Provides
import ru.argerd.repo.domain.repositories.CategoriesRepository
import ru.argerd.repo.domain.repositories.EventsRepository
import ru.argerd.repo.domain.repositories.ProfilePhotoRepository
import ru.argerd.repo.domain.interactors.CategoriesScreenInteractor
import ru.argerd.repo.domain.interactors.*
import ru.argerd.repo.domain.interactors.impl.CategoriesScreenInteractorImpl
import ru.argerd.repo.domain.interactors.impl.EventsInteractorImpl
import ru.argerd.repo.domain.interactors.impl.ProfilePhotoInteractorImpl

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