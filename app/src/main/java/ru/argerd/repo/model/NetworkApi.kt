package ru.argerd.repo.model

import io.reactivex.Flowable
import retrofit2.http.GET
import ru.argerd.repo.model.pojo.Category

interface NetworkApi {

    @GET("events.json")
    fun getEvents()

    @GET("categories.json")
    fun getCategories(): Flowable<List<Category>>
}