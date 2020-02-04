package ru.argerd.repo.screens.filter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App
import ru.argerd.repo.data.model.Category

@InjectViewState
class FilterPresenter : MvpPresenter<FilterView>() {
    private val sharedPreferencesManager = App.component.getSharedPreferences()
    private val interactor = App.component.getCategoriesInteractor()

    fun showList() {
        interactor.getCategoriesFromDatabase()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : DisposableSubscriber<List<Category>>() {
                    override fun onComplete() {
                    }

                    override fun onNext(t: List<Category>?) {
                        viewState.showList(t, sharedPreferencesManager.settings)
                    }

                    override fun onError(t: Throwable?) {
                    }
                })
    }

    fun applyChanges() {
        sharedPreferencesManager.editor.apply()
    }

    fun onSwitchCategory(isChecked: Boolean, nameOfCategories: String) {
        if (isChecked) {
            sharedPreferencesManager.editor.putString(nameOfCategories, "")

        } else {
            sharedPreferencesManager.editor.remove(nameOfCategories)
        }
    }
}