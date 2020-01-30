package ru.argerd.repo.model.repository

import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import ru.argerd.repo.App
import ru.argerd.repo.di.DaggerAppComponent
import ru.argerd.repo.model.pojo.Category

class CategoriesRepositoryImpl : CategoriesRepository {
    private val parser = DaggerAppComponent.create().getParser()

    override fun getCategoriesFromNetwork(): Flowable<List<Category>> {
        return App.api.getCategories()
    }

    override fun getCategoriesFromDatabase(): Flowable<List<Category>> {
        return App.database.categoryDao.getCategories()
    }

    override fun getCategoriesFromFile(): Flowable<List<Category>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getCategories()) },
                BackpressureStrategy.LATEST)
    }

    override fun insertListOfCategory(list: List<Category>) {
        App.database.categoryDao.insertListCategories(list)
    }

    override fun deleteCategories() {
        App.database.categoryDao.deleteAllCategories()
    }
}