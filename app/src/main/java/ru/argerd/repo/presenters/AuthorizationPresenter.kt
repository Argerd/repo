package ru.argerd.repo.presenters

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.argerd.repo.views.AuthorizationView

@InjectViewState
class AuthorizationPresenter : MvpPresenter<AuthorizationView>() {
    private val compositeDisposable = CompositeDisposable()
    private var passwordLength = 0
    private var emailLength = 0

    fun onPasswordInput(password: Observable<String>) {
        compositeDisposable.add(password.subscribe {
            passwordLength = it.length
        })
    }

    fun onEmailInput(email: Observable<String>) {
        compositeDisposable.add(email.subscribe {
            emailLength = it.length
        })
    }

    fun onButtonClicked() {
        if (passwordLength > 5 && emailLength > 5) {
            viewState.moveToMainActivity()
        } else {
            viewState.showWarning()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}