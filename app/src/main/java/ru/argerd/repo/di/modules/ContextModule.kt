package ru.argerd.repo.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.argerd.repo.App
import javax.inject.Singleton

@Module
class ContextModule {

    @Singleton
    @Provides
    fun getContext(): Context {
        return App.context
    }
}