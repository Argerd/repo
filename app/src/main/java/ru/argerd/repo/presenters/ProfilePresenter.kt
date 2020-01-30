package ru.argerd.repo.presenters

import android.net.Uri
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.model.interactors.ProfilePhotoInteractorImpl
import ru.argerd.repo.views.ProfileView

@InjectViewState
class ProfilePresenter : MvpPresenter<ProfileView>() {
    private val interactor = ProfilePhotoInteractorImpl()

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