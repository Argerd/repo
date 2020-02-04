package ru.argerd.repo.screens.profile

import android.net.Uri
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.App

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    private val interactor = App.component.getProfilePhotoInteractor()

    fun moveToDialogFragmentForChoose() {
        viewState.moveToChoose()
    }

    fun onRequestPhotoOfGallery(uri: Uri) {
        if (interactor.saveProfilePhotoToFile(uri))
            viewState.setPhotoProfile()
        else
            viewState.showMessageAndSetPhotoIfError()
    }
}