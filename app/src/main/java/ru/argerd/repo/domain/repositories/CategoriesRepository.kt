package ru.argerd.repo.domain.repositories

import io.reactivex.Flowable
import ru.argerd.repo.data.model.Category

interface CategoriesRepository {
    fun getCategoriesFromNetwork(): Flowable<List<Category>>

    fun getCategoriesFromDatabase(): Flowable<List<Category>>

    fun getCategoriesFromFile(): Flowable<List<Category>>

    fun insertListOfCategory(list: List<Category>)

    fun deleteCategories()
}