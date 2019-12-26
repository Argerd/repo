package ru.argerd.repo.parsing

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

    fun getCategories(context: Context): ArrayList<Category> {
        val json: String
        try {
            val inputStream = context.assets.open("2")
            json = inputStream.bufferedReader().use { it.readText() }
            Log.d(TAG, json)
        } catch (e: Exception) {
            Log.d(TAG, "Ошибка при парсинге категорий", e)
            return arrayListOf()
        }

        return Gson().fromJson<ArrayList<Category>>(json, categoriesType)
    }

    fun getEvents(context: Context): ArrayList<Event> {
        val json: String
        try {
            val inputStream = context.assets.open("1")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            return arrayListOf()
        }
        return Gson().fromJson<ArrayList<Event>>(json, eventsType)
    }
}