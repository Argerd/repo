package ru.argerd.repo.di

import dagger.Component
import ru.argerd.repo.utils.Parser
import ru.argerd.repo.utils.SharedPreferencesManager

@Component
interface AppComponent {
    fun getParser(): Parser
    fun getSharedPreferences(): SharedPreferencesManager
}