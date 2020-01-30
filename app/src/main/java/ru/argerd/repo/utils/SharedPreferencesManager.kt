package ru.argerd.repo.utils

import android.content.Context
import ru.argerd.repo.App
import javax.inject.Inject

class SharedPreferencesManager
@Inject constructor() {

    private companion object {
        private const val FILTER_SETTINGS = "filterSettings"
    }

    private val sharedPreferences = App.context.getSharedPreferences(FILTER_SETTINGS,
            Context.MODE_PRIVATE)

    internal val settings = sharedPreferences.all.keys.distinct()
    internal val editor = sharedPreferences.edit()
}