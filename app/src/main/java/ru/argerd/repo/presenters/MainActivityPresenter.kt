package ru.argerd.repo.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.views.MainActivityView

@InjectViewState
class MainActivityPresenter : MvpPresenter<MainActivityView>() {
    fun fubOnClick() {
        viewState.moveToCategories()
    }

    fun updateUI() {
        viewState.updateUI()
    }

    fun navigateToProfile() {
        viewState.navigateToProfile()
    }

    fun navigateToSearch() {
        viewState.navigateToSearch()
    }

    fun navigateToNews() {
        viewState.navigateToNews()
    }
}