package ru.argerd.repo.model

import io.reactivex.Flowable
import retrofit2.http.GET

interface NetworkApi {

    @GET("events.json")
    fun getEvents()

    @GET("categories.json")
    fun getCategories(): Flowable<ArrayList<Category?>>
}