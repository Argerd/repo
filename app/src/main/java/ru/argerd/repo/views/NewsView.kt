package ru.argerd.repo.views

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.argerd.repo.model.pojo.Event

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NewsView : MvpView {
    fun showList(list: List<Event>?)
}