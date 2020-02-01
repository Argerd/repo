package ru.argerd.repo.presenters

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subscribers.DisposableSubscriber
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App
import ru.argerd.repo.model.pojo.Category
import ru.argerd.repo.ui.CategoriesOfHelpFragment
import ru.argerd.repo.views.CategoriesView

@InjectViewState
class CategoriesPresenter : MvpPresenter<CategoriesView>() {
    private val interactor = App.component.getCategoriesInteractor()

    private companion object {
        private val TAG = CategoriesOfHelpFragment::class.java.name
    }

    fun setListOfCategory() {
        if (App.firstOpenCategory) {
            Log.d(TAG, "запрос к сети")
            interactor.getCategoriesFromNetwork()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
            App.firstOpenCategory = false
        } else {
            Log.d(TAG, "чтение из бд")
            interactor.getCategoriesFromDatabase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(dataSubscription())
        }
    }

    private fun dataSubscription(): DisposableSubscriber<List<Category>> {
        return object : DisposableSubscriber<List<Category>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Category>?) {
                Log.d(TAG, "размер листа ${t?.size}")
                viewState.showListOfCategories(t)
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, t.toString())
                viewState.showSnackbarWithError(t)
                interactor.getCategoriesFromFile()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(fileSubscription())
            }
        }
    }

    private fun fileSubscription(): DisposableSubscriber<List<Category>> {
        return object : DisposableSubscriber<List<Category>>() {
            override fun onComplete() {
            }

            override fun onNext(t: List<Category>?) {
                viewState.showListOfCategories(t)
            }

            override fun onError(t: Throwable?) {
                Log.d(TAG, t.toString())
                viewState.showSnackbarWithError(t)
            }
        }
    }

    fun hideProgressBar() {
        viewState.hideProgressBar()
    }
}