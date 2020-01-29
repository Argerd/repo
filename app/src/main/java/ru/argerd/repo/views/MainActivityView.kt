package ru.argerd.repo.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndStrategy::class)
interface MainActivityView : MvpView {
    fun moveToCategories()

    fun navigateToProfile()

    fun navigateToSearch()

    fun navigateToNews()

    fun updateUI()
}