package ru.argerd.repo.model.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import ru.argerd.repo.model.database.CategoryDao
import ru.argerd.repo.model.pojo.Category
import ru.argerd.repo.model.retrofit.NetworkApi
import ru.argerd.repo.utils.Parser

class CategoriesRepositoryImpl(
        private val parser: Parser,
        private val database: CategoryDao,
        private val api: NetworkApi
) : CategoriesRepository {

    override fun getCategoriesFromNetwork(): Flowable<List<Category>> {
        return api.getCategories()
    }

    override fun getCategoriesFromDatabase(): Flowable<List<Category>> {
        return database.getCategories()
    }

    override fun getCategoriesFromFile(): Flowable<List<Category>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getCategories()) },
                BackpressureStrategy.LATEST)
    }

    override fun insertListOfCategory(list: List<Category>) {
        database.insertListCategories(list)
    }

    override fun deleteCategories() {
        database.deleteAllCategories()
    }
}