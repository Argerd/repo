package ru.argerd.repo.presenters

import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.views.ProfileView

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    fun moveToDialogFragmentForChoose() {
        viewState.moveToChoose()
    }
}