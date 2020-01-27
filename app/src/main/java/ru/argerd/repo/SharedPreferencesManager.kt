package ru.argerd.repo

import android.content.Context

private const val FILTER_SETTINGS = "filterSettings"

class SharedPreferencesManager {
    private val sharedPreferences = App.context.getSharedPreferences(FILTER_SETTINGS,
            Context.MODE_PRIVATE)
    internal val settings = sharedPreferences.all.keys.distinct()
    internal val editor = sharedPreferences.edit()
}