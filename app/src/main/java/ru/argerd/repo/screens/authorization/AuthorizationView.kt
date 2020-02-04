package ru.argerd.repo.screens.authorization

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface AuthorizationView : MvpView {
    fun moveToMainActivity()

    fun showWarning()
}