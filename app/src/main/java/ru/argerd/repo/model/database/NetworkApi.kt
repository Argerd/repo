package ru.argerd.repo.model.database

import io.reactivex.Flowable
import retrofit2.http.GET
import ru.argerd.repo.model.Category

interface NetworkApi {

    @GET("events.json")
    fun getEvents()

    @GET("categories.json")
    fun getCategories(): Flowable<ArrayList<Category>>
}