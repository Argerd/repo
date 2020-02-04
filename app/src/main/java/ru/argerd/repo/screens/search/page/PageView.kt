package ru.argerd.repo.screens.search.page

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface PageView : MvpView {
    fun skipListOfResults()

    fun showListOfResults(list: List<String?>)
}