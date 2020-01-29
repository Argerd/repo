package ru.argerd.repo.presenters

import android.util.Log
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App
import ru.argerd.repo.model.Category
import ru.argerd.repo.utils.Parser
import ru.argerd.repo.ui.categoriesOfHelpScreen.CategoriesOfHelpFragment
import ru.argerd.repo.views.CategoriesView

@InjectViewState
class CategoriesPresenter : MvpPresenter<CategoriesView>() {
    private val parser = Parser()

    companion object {
        private val TAG = CategoriesOfHelpFragment::class.java.name
    }

    fun setListOfCategory() {
        if (App.firstOpenCategory) {
            Log.d(TAG, "запрос к сети")
            App.api.getCategories()
                    .map {
                        Log.d(TAG, "запрос к сети")
                        App.database.categoryDao.deleteAllCategories()
                        App.database.categoryDao.insertListCategories(it)
                        return@map it
                    }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenCategory = false
        } else {
            Log.d(TAG, "чтение из бд")
            App.database.categoryDao.getCategories()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }
    }

    private fun flowableFileParse(): Flowable<List<Category?>> {
        return Flowable.create({ emitter -> emitter.onNext(parser.getCategories()) },
                BackpressureStrategy.LATEST)
    }

    private fun dataSubscription(): DisposableSubscriber<List<Category?>> {
        return object : DisposableSubscriber<List<Category?>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Category?>?) {
                Log.d(TAG, "размер листа ${t?.size}")
                viewState.showListOfCategories(t)
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, t.toString())
                viewState.showSnackbarWithError(t)
                flowableFileParse()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(dataSubscription())
            }
        }
    }

    fun hideProgressBar() {
        viewState.hideProgressBar()
    }
}