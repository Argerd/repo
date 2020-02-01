package ru.argerd.repo.utils

import android.content.Context
import ru.argerd.repo.App

class SharedPreferencesManager(context: Context) {

    private companion object {
        private const val FILTER_SETTINGS = "filterSettings"
    }

    private val sharedPreferences = context.getSharedPreferences(FILTER_SETTINGS,
            Context.MODE_PRIVATE)

    internal val settings = sharedPreferences.all.keys.distinct()
    internal val editor = sharedPreferences.edit()
}