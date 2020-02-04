package ru.argerd.repo.data.repositories

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import ru.argerd.repo.data.database.dao.CategoryDao
import ru.argerd.repo.data.model.Category
import ru.argerd.repo.data.network.NetworkApi
import ru.argerd.repo.domain.repositories.CategoriesRepository
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