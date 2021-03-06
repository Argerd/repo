package ru.argerd.repo.screens.filter

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.argerd.repo.data.model.Category

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilterView : MvpView {
    fun showList(t: List<Category>?, settings: List<String>)
}