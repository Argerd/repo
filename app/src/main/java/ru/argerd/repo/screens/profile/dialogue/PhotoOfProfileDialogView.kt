package ru.argerd.repo.screens.profile.dialogue

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(value = AddToEndSingleStrategy::class)
interface PhotoOfProfileDialogView : MvpView {
}