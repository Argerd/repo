package ru.argerd.repo

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.argerd.repo.model.Category
import ru.argerd.repo.model.Event

private const val TAG = "MyParser"

class Parser {
    private val categoriesType = object : TypeToken<List<Category>>() {}.type
    private val eventsType = object : TypeToken<List<Event>>() {}.type

    fun getCategories(context: Context): List<Category> {
        val json: String
        try {
            val inputStream = context.assets.open("2")
            json = inputStream.bufferedReader().use { it.readText() }
            Log.d(TAG, json)
        } catch (e: Exception) {
            Log.d(TAG, "Ошибка при парсинге категорий", e)
            return listOf()
        }

        val list = Gson().fromJson<List<Category>>(json, categoriesType)

        Log.d(TAG, "list size ${list.size}")

        return list.toList()
    }

    fun getEvents(context: Context): List<Event> {
        val json: String
        try {
            val inputStream = context.assets.open("1")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            return listOf()
        }
        return Gson().fromJson<List<Event>>(json, eventsType)
    }
}