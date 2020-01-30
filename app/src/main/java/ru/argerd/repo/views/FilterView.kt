package ru.argerd.repo.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.argerd.repo.model.pojo.Category

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface FilterView : MvpView {
    fun showList(t: List<Category>?, settings: List<String>)
}