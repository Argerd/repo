package ru.argerd.repo.screens.main

import moxy.InjectViewState
import moxy.MvpPresenter

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