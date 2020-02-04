package ru.argerd.repo.data.network

import io.reactivex.Flowable
import retrofit2.http.GET
import ru.argerd.repo.data.model.Category

interface NetworkApi {

    @GET("events.json")
    fun getEvents()

    @GET("categories.json")
    fun getCategories(): Flowable<List<Category>>
}