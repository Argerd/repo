package ru.argerd.repo.screens.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface ProfileView : MvpView {
    fun moveToChoose()

    fun setPhotoProfile()

    fun showMessageAndSetPhotoIfError()
}