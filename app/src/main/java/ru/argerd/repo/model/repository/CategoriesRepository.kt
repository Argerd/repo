package ru.argerd.repo.model.repository

import io.reactivex.Flowable
import ru.argerd.repo.model.pojo.Category

interface CategoriesRepository {
    fun getCategoriesFromNetwork(): Flowable<List<Category>>

    fun getCategoriesFromDatabase(): Flowable<List<Category>>

    fun getCategoriesFromFile(): Flowable<List<Category>>

    fun insertListOfCategory(list: List<Category>)

    fun deleteCategories()
}