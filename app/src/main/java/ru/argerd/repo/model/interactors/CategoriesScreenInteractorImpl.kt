package ru.argerd.repo.model.interactors

import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers
import ru.argerd.repo.model.pojo.Category
import ru.argerd.repo.model.repository.CategoriesRepository

class CategoriesScreenInteractorImpl(
        private val repository: CategoriesRepository
) : CategoriesScreenInteractor {

    override fun getCategoriesFromNetwork(): Flowable<List<Category>> {
        return repository.getCategoriesFromNetwork()
                .map {
                    repository.deleteCategories()
                    repository.insertListOfCategory(it)
                    return@map it
                }.subscribeOn(Schedulers.io())
    }

    override fun getCategoriesFromDatabase(): Flowable<List<Category>> {
        return repository.getCategoriesFromDatabase().subscribeOn(Schedulers.io())
    }

    override fun getCategoriesFromFile(): Flowable<List<Category>> {
        return repository.getCategoriesFromFile().subscribeOn(Schedulers.io())
    }
}