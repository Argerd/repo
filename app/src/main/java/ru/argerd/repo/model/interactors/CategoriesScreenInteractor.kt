package ru.argerd.repo.model.interactors

import io.reactivex.Flowable
import ru.argerd.repo.model.pojo.Category

interface CategoriesScreenInteractor {
    fun getCategoriesFromNetwork(): Flowable<List<Category>>

    fun getCategoriesFromDatabase(): Flowable<List<Category>>

    fun getCategoriesFromFile(): Flowable<List<Category>>
}