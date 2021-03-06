package ru.argerd.repo.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.argerd.repo.data.model.Category
import ru.argerd.repo.data.model.Event

class Parser(private val context: Context) {
    private val categoriesType = object : TypeToken<List<Category>>() {}.type
    private val eventsType = object : TypeToken<List<Event>>() {}.type

    fun getCategories(): List<Category> {
        val json: String
        try {
            val inputStream = context.assets.open("2")
            json = inputStream.bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            return arrayListOf()
        }
        return Gson().fromJson<ArrayList<Category>>(json, categoriesType)
    }

    fun getEvents(): List<Event> {
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