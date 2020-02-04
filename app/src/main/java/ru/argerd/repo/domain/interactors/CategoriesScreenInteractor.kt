package ru.argerd.repo.domain.interactors

import io.reactivex.Flowable
import ru.argerd.repo.data.model.Category

interface CategoriesScreenInteractor {
    fun getCategoriesFromNetwork(): Flowable<List<Category>>

    fun getCategoriesFromDatabase(): Flowable<List<Category>>

    fun getCategoriesFromFile(): Flowable<List<Category>>
}