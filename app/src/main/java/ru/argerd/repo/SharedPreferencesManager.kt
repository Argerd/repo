package ru.argerd.repo

import android.content.Context

class SharedPreferencesManager {

    private companion object {
        private const val FILTER_SETTINGS = "filterSettings"
    }

    private val sharedPreferences = App.context.getSharedPreferences(FILTER_SETTINGS,
            Context.MODE_PRIVATE)

    internal val settings = sharedPreferences.all.keys.distinct()
    internal val editor = sharedPreferences.edit()
}