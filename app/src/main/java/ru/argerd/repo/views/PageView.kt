package ru.argerd.repo.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface PageView : MvpView {
    fun skipListOfResults()

    fun showListOfResults(list: List<String?>)
}