package ru.argerd.repo.di

import dagger.Component
import ru.argerd.repo.model.interactors.CategoriesScreenInteractor
import ru.argerd.repo.model.interactors.EventsInteractor
import ru.argerd.repo.model.interactors.ProfilePhotoInteractor
import ru.argerd.repo.utils.Parser
import ru.argerd.repo.utils.SharedPreferencesManager
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, MemoryModule::class, ContextModule::class,
    InteractorsModule::class, RepositoriesModule::class])
interface AppComponent {

    fun getSharedPreferences(): SharedPreferencesManager

    fun getParser(): Parser

    fun getCategoriesInteractor(): CategoriesScreenInteractor

    fun getEventsInteractor(): EventsInteractor

    fun getProfilePhotoInteractor(): ProfilePhotoInteractor

}