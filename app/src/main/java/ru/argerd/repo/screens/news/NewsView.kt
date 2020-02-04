package ru.argerd.repo.screens.news

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.argerd.repo.data.model.Event

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface NewsView : MvpView {
    fun showList(list: List<Event>?)
}